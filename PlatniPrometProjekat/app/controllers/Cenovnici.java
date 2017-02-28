package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Cenovnik;
import models.Preduzece;
import models.StavkaCenovnika;
import play.data.validation.*;
import play.data.validation.Error;
import play.mvc.Controller;

public class Cenovnici extends Controller {
	public static void show(String mode){
		List<Cenovnik>cenovnici=Cenovnik.findAll();
		List<Preduzece>preduzeca=Preduzece.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(cenovnici,preduzeca, mode);
	}
	public static void add(@Required Date datumVazenja, @Required long preduzece) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			Cenovnik cenovnik=new Cenovnik();
			cenovnik.datumVazenja=datumVazenja;
			cenovnik.preduzece=Preduzece.findById(preduzece);
			cenovnik.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(long preduzece){
		List<Cenovnik> cenovnici = Cenovnik.find("byPreduzece_id", preduzece).fetch();
		String mode = "edit";
		renderTemplate("Cenovnici/show.html", cenovnici, mode);
	}
	public static void edit(Date datumVazenja,long preduzece, long id){
		Cenovnik cenovnik = Cenovnik.findById(id);
		cenovnik.datumVazenja = datumVazenja;
		cenovnik.preduzece = Preduzece.findById(preduzece);
		cenovnik.save();
		show("");
	}	
	public static void delete(long id){
		Cenovnik cenovnik = Cenovnik.findById(id);
		cenovnik.delete();
		show("");
	}
	public static void copy(long id,float procenat){
		Cenovnik cenovnik = Cenovnik.findById(id);
		Cenovnik cenovnik2=new Cenovnik();
		cenovnik2.datumVazenja=cenovnik.datumVazenja;
		cenovnik2.preduzece=cenovnik.preduzece;
		cenovnik2.stavkeCenovnika=new ArrayList<StavkaCenovnika>();
		System.out.println(id);
		System.out.println(procenat);
		System.out.println(cenovnik2);
		System.out.println(cenovnik.stavkeCenovnika.size());
		cenovnik2.save();
		for (StavkaCenovnika  stavka : cenovnik.stavkeCenovnika) {
			StavkaCenovnika stavka2=new StavkaCenovnika();
			stavka2.robaIliUsluga=stavka.robaIliUsluga;

			System.out.println("=========");
			System.out.println(stavka);

			System.out.println(stavka2);
			if (procenat>0) {
				stavka2.cena=stavka.cena+(stavka.cena*procenat/100);
				
			}
			else{
				procenat=-procenat;
				stavka2.cena=stavka.cena-(stavka.cena*procenat/100);				
			}
			stavka2.cenovnik=cenovnik2;
			stavka2.save();
			cenovnik2.stavkeCenovnika.add(stavka2);
		}

		cenovnik2.save();
		show("");
	}

}
