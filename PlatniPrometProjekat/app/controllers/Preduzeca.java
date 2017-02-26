package controllers;

import java.util.List;

import models.Mesto;
import models.PoslovniPartner;
import models.Preduzece;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;

public class Preduzeca extends Controller {
	public static void show(String mode){
		List<Mesto>mesta=Mesto.findAll();
		List<Preduzece>preduzeca=Preduzece.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(mesta,preduzeca, mode);
	}
	public static void add(@Required String nazivPreduzeca,long mesto, String ulicaIBroj,
			int pIB, String email, String telefon ) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			Preduzece preduzece=new Preduzece();
			preduzece.nazivPreduzeca=nazivPreduzeca;
			preduzece.ulicaIBroj=ulicaIBroj;
			preduzece.email=email;
			preduzece.telefon=telefon;
			preduzece.PIB=pIB;
			preduzece.mesto=Mesto.findById(mesto);
			preduzece.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(@Required String nazivPreduzeca,long mesto, String ulicaIBroj,
			int pIB, String email, String telefon){
		List<Preduzece> preduzeca = Mesto.find("byNazivPreduzecaLikeAndUlicaIBrojLikeAndEmailLikeAndTelefonLikeAndPIBLikeAndMesto_id", "%"+ nazivPreduzeca +"%","%"+ ulicaIBroj +"%","%"+ email +"%","%"+ telefon +"%","%"+ pIB +"%", mesto).fetch();
		String mode = "edit";
		renderTemplate("Preduzeca/show.html", preduzeca, mode);
	}
	public static void edit(@Required String nazivPreduzeca,long mesto, String ulicaIBroj,
			int pIB, String email, String telefon, long id){
		Preduzece preduzece = Preduzece.findById(id);
		preduzece.nazivPreduzeca=nazivPreduzeca;
		preduzece.ulicaIBroj=ulicaIBroj;
		preduzece.email=email;
		preduzece.telefon=telefon;
		preduzece.PIB=pIB;
		preduzece.mesto=Mesto.findById(mesto);
		preduzece.save();
		show("");
	}	
	public static void delete(long id){
		Preduzece preduzece = Preduzece.findById(id);
		preduzece.delete();
		show("");
	}

}
