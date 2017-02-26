package controllers;

import java.util.Date;
import java.util.List;

import models.Cenovnik;
import models.Preduzece;
import models.RobaIliUsluga;
import models.StavkaCenovnika;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;

public class StavkeCenovnika extends Controller {
	public static void show(String mode){
		List<StavkaCenovnika>stavkeCenovnika=StavkaCenovnika.findAll();
		List<RobaIliUsluga>robaIliUsluge=RobaIliUsluga.findAll();
		List<Cenovnik>cenovnici=Cenovnik.findAll();

		if (mode == null || mode.equals(""))
			mode = "edit";
		render(stavkeCenovnika,robaIliUsluge,cenovnici, mode);
	}
	public static void add(@Required float cena, @Required long robaIliUsluga, long cenovnik) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			StavkaCenovnika stavkaCenovnika=new StavkaCenovnika();
			stavkaCenovnika.cena=cena;
			stavkaCenovnika.robaIliUsluga=RobaIliUsluga.findById(robaIliUsluga);
			stavkaCenovnika.cenovnik=Cenovnik.findById(cenovnik);
			stavkaCenovnika.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(long robaIliUsluga, long cenovnik, float cena){
		List<StavkaCenovnika> stavkeCenovnika = StavkaCenovnika.find("byRobaIliUsluga_idAndCenovnik_idAndCena", robaIliUsluga,cenovnik,cena).fetch();
		String mode = "edit";
		renderTemplate("StavkeCenovnika/show.html", stavkeCenovnika, mode);
	}
	public static void edit(long robaIliUsluga, long cenovnik, float cena, long id){
		StavkaCenovnika stavkaCenovnika = StavkaCenovnika.findById(id);
		stavkaCenovnika.cena = cena;
		stavkaCenovnika.cenovnik = Cenovnik.findById(cenovnik);
		stavkaCenovnika.robaIliUsluga = RobaIliUsluga.findById(robaIliUsluga);

		stavkaCenovnika.save();
		show("");
	}	
	public static void delete(long id){
		StavkaCenovnika stavkaCenovnika = StavkaCenovnika.findById(id);
		stavkaCenovnika.delete();
		show("");
	}

}
