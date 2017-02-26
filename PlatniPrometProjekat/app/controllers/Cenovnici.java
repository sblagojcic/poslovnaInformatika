package controllers;

import java.util.Date;
import java.util.List;

import models.Cenovnik;
import models.Preduzece;
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
	public static void filter(Date datum, long preduzece){
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

}
