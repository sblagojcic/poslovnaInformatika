package controllers;

import java.util.Date;
import java.util.List;

import models.Faktura;
import models.Narudzbenica;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;

public class Narudzbenice extends Controller {
	public static void show(String mode){
		List<Narudzbenica>narudzbenice=Narudzbenica.findAll();
		List<PoslovnaGodina> poslovneGodine=PoslovnaGodina.findAll();
		List<PoslovniPartner> poslovniPartneri=PoslovniPartner.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(narudzbenice,poslovneGodine,poslovniPartneri, mode);
	}
	public static void add(@Required int brojNarudzbenice, @Required long poslovniPartner, @Required long poslovnaGodina) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			Narudzbenica narudzbenica=new Narudzbenica();
			narudzbenica.brojNarudzbenice=brojNarudzbenice;
			narudzbenica.poslovniPartner=PoslovniPartner.findById(poslovniPartner);
			narudzbenica.poslovnaGodina=PoslovnaGodina.findById(poslovnaGodina);
			narudzbenica.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(long poslovniPartner, long poslovnaGodina, int brojNarudzbenice){
		List<Narudzbenica> narudzbenice = Narudzbenica.find("byPoslovniPartner_idAndPoslovnaGodina_idAndBrojNarudzbenice", poslovnaGodina, poslovnaGodina, brojNarudzbenice).fetch();
		String mode = "edit";
		renderTemplate("Narudzbenice/show.html", narudzbenice, mode);
	}
	public static void edit(@Required int brojNarudzbenice, @Required long poslovniPartner,
			@Required long poslovnaGodina,long id){
		Narudzbenica narudzbenica = Narudzbenica.findById(id);
		narudzbenica.brojNarudzbenice=brojNarudzbenice;
		narudzbenica.poslovniPartner=PoslovniPartner.findById(poslovniPartner);
		narudzbenica.poslovnaGodina=PoslovnaGodina.findById(poslovnaGodina);
		narudzbenica.save();
		show("");
	}	
	public static void delete(long id){
		Narudzbenica narudzbenica = Narudzbenica.findById(id);
		narudzbenica.delete();
		show("");
	}

}

