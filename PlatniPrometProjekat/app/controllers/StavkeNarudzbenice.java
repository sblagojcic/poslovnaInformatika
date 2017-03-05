package controllers;

import java.util.List;

import models.Cenovnik;
import models.Faktura;
import models.Narudzbenica;
import models.RobaIliUsluga;
import models.StavkaCenovnika;
import models.StavkaFakture;
import models.StavkaNarudzbenice;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;

public class StavkeNarudzbenice extends Controller {
	public static void show(String mode){
		List<StavkaNarudzbenice>stavkeNarudzbenice=StavkaNarudzbenice.findAll();
		List<RobaIliUsluga>robaIliUsluge=RobaIliUsluga.findAll();
		List<Narudzbenica>narudzbenice=Narudzbenica.findAll();

		if (mode == null || mode.equals(""))
			mode = "edit";
		render(stavkeNarudzbenice,robaIliUsluge,narudzbenice, mode);
	}
	public static void add(@Required String jedinicaMere,float kolicina,long narudzbenica,@Required long robaIliUsluga, long faktura) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			
			StavkaNarudzbenice stavkaNarudzbenice=new StavkaNarudzbenice();
			Narudzbenica narudzbenicaa=Narudzbenica.findById(narudzbenica);
			stavkaNarudzbenice.kolicina=kolicina;
			stavkaNarudzbenice.jedinicaMere=jedinicaMere;
			stavkaNarudzbenice.robaIliUsluga=RobaIliUsluga.findById(robaIliUsluga);
			stavkaNarudzbenice.narudzbenica=narudzbenicaa;
			stavkaNarudzbenice.save();
			validation.keep();
			narudzbenicaa.save();

			show("add");
	}
	}
	public static void filter(long robaIliUsluga, long narudzbenica, float kolicina,String jedinicaMere){
		List<StavkaNarudzbenice> stavkeNarudzbenice = StavkaNarudzbenice.find("byRobaIliUsluga_idAndNarudzbenica_idAndKolicinaAndJedinicaMereLike", robaIliUsluga,narudzbenica,kolicina, jedinicaMere).fetch();
		String mode = "edit";
		renderTemplate("StavkeNarudzbenice/show.html", stavkeNarudzbenice, mode);
	}
	public static void edit(long robaIliUsluga, long narudzbenica, float kolicina,String jedinicaMere,long id){
		StavkaNarudzbenice stavkaNarudzbenice = StavkaNarudzbenice.findById(id);
		Narudzbenica narudzbenicaa=Narudzbenica.findById(narudzbenica);
		stavkaNarudzbenice.kolicina = kolicina;
		stavkaNarudzbenice.jedinicaMere = jedinicaMere;
		stavkaNarudzbenice.narudzbenica = narudzbenicaa;
		stavkaNarudzbenice.robaIliUsluga = RobaIliUsluga.findById(robaIliUsluga);

		stavkaNarudzbenice.save();
		show("");
	}	
	public static void delete(long id){
		StavkaNarudzbenice stavkaNarudzbenice = StavkaNarudzbenice.findById(id);
		stavkaNarudzbenice.delete();
		show("");
	}

}


