package controllers;

import java.util.Date;
import java.util.List;

import models.Faktura;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;
import models.StavkaFakture;
import play.data.validation.*;
import play.data.validation.Error;
import play.mvc.Controller;

public class Fakture extends Controller {
	public static void show(String mode){
		List<Faktura>fakture=Faktura.findAll();
		List<Preduzece>preduzeca=Preduzece.findAll();
		List<PoslovnaGodina> poslovneGodine=PoslovnaGodina.findAll();
		List<PoslovniPartner> poslovniPartneri=PoslovniPartner.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(fakture,preduzeca,poslovneGodine,poslovniPartneri, mode);
	}
	public static void add(@Required Date datumFakture,@Required int brojFaktura,
			@Required Date datumValute,@Required long osnovica,@Required long ukupanPDV,
			@Required long iznosZaPlacanje,@Required String statusFakture,
			@Required long preduzece, @Required long poslovniPartner, @Required long poslovnaGodina) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			Faktura faktura=new Faktura();
			faktura.brojFaktura=brojFaktura;
			faktura.datumFakture=datumFakture;
			faktura.datumValute=datumValute;
			faktura.osnovica=osnovica;
			faktura.ukupanPDV=ukupanPDV;
			faktura.iznosZaPlacanje=iznosZaPlacanje;
			faktura.statusFakture=statusFakture;
			faktura.preduzece=Preduzece.findById(preduzece);
			faktura.poslovniPartner=PoslovniPartner.findById(poslovniPartner);
			faktura.poslovnaGodina=PoslovnaGodina.findById(poslovnaGodina);
			faktura.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(Date datum, long preduzece){
		List<Faktura> fakture = Faktura.find("byPreduzece_id", preduzece).fetch();
		String mode = "edit";
		renderTemplate("Fakture/show.html", fakture, mode);
	}
	public static void edit(@Required Date datumFakture,@Required int brojFaktura,
			@Required Date datumValute,@Required long osnovica,@Required long ukupanPDV,
			@Required long iznosZaPlacanje,@Required String statusFakture,
			@Required long preduzece, @Required long poslovniPartner,
			@Required long poslovnaGodina,long id){
		Faktura faktura = Faktura.findById(id);
		faktura.brojFaktura=brojFaktura;
		faktura.datumFakture=datumFakture;
		faktura.datumValute=datumValute;
		faktura.osnovica=osnovica;
		faktura.ukupanPDV=ukupanPDV;
		faktura.iznosZaPlacanje=iznosZaPlacanje;
		faktura.statusFakture=statusFakture;
		faktura.preduzece=Preduzece.findById(preduzece);
		faktura.poslovniPartner=PoslovniPartner.findById(poslovniPartner);
		faktura.poslovnaGodina=PoslovnaGodina.findById(poslovnaGodina);
		faktura.save();
		show("");
	}	
	public static void delete(long id){
		Faktura faktura = Faktura.findById(id);
		faktura.delete();
		show("");
	}

}
