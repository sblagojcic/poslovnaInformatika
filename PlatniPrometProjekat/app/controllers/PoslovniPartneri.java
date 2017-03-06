package controllers;

import java.util.List;

import models.Mesto;
import models.PoslovniPartner;
import models.Preduzece;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;

public class PoslovniPartneri extends Controller {
	public static void show(String mode){
		List<PoslovniPartner>poslovniPartneri=PoslovniPartner.findAll();
		List<Mesto>mesta=Mesto.findAll();
		List<Preduzece>preduzeca=Preduzece.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(mesta,poslovniPartneri,preduzeca, mode);
	}
	public static void add(@Required String nazivPartnera,long mesto, String ulicaIBroj,
			 String vrstaPartnera, long preduzece ) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			PoslovniPartner poslovniPartner=new PoslovniPartner();
			poslovniPartner.nazivPartnera=nazivPartnera;
			poslovniPartner.ulicaIBroj=ulicaIBroj;
			poslovniPartner.vrstaPartnera=vrstaPartnera;
			poslovniPartner.preduzece=Preduzece.findById(preduzece);
			poslovniPartner.mesto=Mesto.findById(mesto);
			poslovniPartner.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(@Required String nazivPartnera,long mesto, String ulicaIBroj,
			 String vrstaPartnera, long preduzece){
		List<PoslovniPartner> poslovniPartneri = PoslovniPartner.find("byNazivPartneraLikeAndUlicaIBrojLikeAndVrstaPartneraLikeAndMesto_idAndPreduzece_id", "%"+ nazivPartnera +"%","%"+ ulicaIBroj +"%","%"+ vrstaPartnera +"%", mesto, preduzece).fetch();
		String mode = "edit";
		renderTemplate("PoslovniPartneri/show.html", poslovniPartneri, mode);
	}
	public static void edit(@Required String nazivPartnera,long mesto, String ulicaIBroj,
			 String vrstaPartnera, long preduzece, long id){
		PoslovniPartner poslovniPartner = PoslovniPartner.findById(id);
		poslovniPartner.nazivPartnera=nazivPartnera;
		poslovniPartner.ulicaIBroj=ulicaIBroj;
		poslovniPartner.vrstaPartnera=vrstaPartnera;
		poslovniPartner.mesto=Mesto.findById(mesto);
		poslovniPartner.preduzece=Preduzece.findById(preduzece);
		poslovniPartner.save();
		show("");
	}	
	public static void delete(long id){
		PoslovniPartner poslovniPartner = PoslovniPartner.findById(id);
		poslovniPartner.delete();
		show("");
	}

}
