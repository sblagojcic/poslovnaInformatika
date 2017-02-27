package controllers;

import java.util.Date;
import java.util.List;

import models.Otpremnica;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;

public class Otpremnice extends Controller {
	public static void show(String mode){
		List<Otpremnica>otpremnice=Otpremnica.findAll();
		System.out.println(otpremnice.size());
		List<PoslovnaGodina> poslovneGodine=PoslovnaGodina.findAll();
		List<PoslovniPartner> poslovniPartneri=PoslovniPartner.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(otpremnice,poslovneGodine,poslovniPartneri, mode);
	}
	public static void add(@Required int brojOtpremnice,@Required long osnovica,@Required long ukupanPDV,
			@Required long iznosZaPlacanje, @Required long poslovniPartner, @Required long poslovnaGodina) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			Otpremnica otpremnica=new Otpremnica();
			otpremnica.brojOtpremnice=brojOtpremnice;
			otpremnica.osnovica=osnovica;
			otpremnica.ukupanPDV=ukupanPDV;
			otpremnica.iznosZaPlacanje=iznosZaPlacanje;
			otpremnica.poslovniPartner=PoslovniPartner.findById(poslovniPartner);
			otpremnica.poslovnaGodina=PoslovnaGodina.findById(poslovnaGodina);
			otpremnica.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(Date datum, long preduzece){
		List<Otpremnica> otpremnice = Otpremnica.find("byPreduzece_id", preduzece).fetch();
		String mode = "edit";
		renderTemplate("Otpremnice/show.html", otpremnice, mode);
	}
	public static void edit(@Required int brojOtpremnice,@Required long osnovica,@Required long ukupanPDV,
			@Required long iznosZaPlacanje, @Required long poslovniPartner, @Required long poslovnaGodina,long id){
		Otpremnica otpremnica = Otpremnica.findById(id);
		otpremnica.brojOtpremnice=brojOtpremnice;
		otpremnica.osnovica=osnovica;
		otpremnica.ukupanPDV=ukupanPDV;
		otpremnica.iznosZaPlacanje=iznosZaPlacanje;
		otpremnica.poslovniPartner=PoslovniPartner.findById(poslovniPartner);
		otpremnica.poslovnaGodina=PoslovnaGodina.findById(poslovnaGodina);
		otpremnica.save();
		show("");
	}	
	public static void delete(long id){
		Otpremnica otpremnica = Otpremnica.findById(id);
		otpremnica.delete();
		show("");
	}

}
