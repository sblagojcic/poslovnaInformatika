package controllers;

import java.util.List;

import models.Mesto;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;
import play.data.validation.*;
import play.data.validation.Error;
import play.mvc.Controller;

public class Mesta extends Controller {
	public static void show(String mode){
		List<Mesto>mesta=Mesto.findAll();
		List<PoslovniPartner>poslovniPartneri=PoslovniPartner.findAll();
		List<Preduzece>preduzeca=Preduzece.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(mesta,preduzeca,poslovniPartneri, mode);
	}
	public static void add(@Required String nazivMesta,Long preduzeca,Long poslovniPartner) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			Mesto mesto=new Mesto();
			mesto.nazivMesta=nazivMesta;
			mesto.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(@Required String nazivMesta){
		List<Mesto> mesta = Mesto.find("byNazivMestaLike", "%"+ nazivMesta +"%").fetch();
		String mode = "edit";
		renderTemplate("Mesta/show.html", mesta, mode);
	}
	public static void edit(@Required String nazivMesta,Long preduzeca,Long poslovniPartner, long id){
		Mesto mesto = Mesto.findById(id);
		mesto.nazivMesta=nazivMesta;
		mesto.save();
		show("");
	}	
	public static void delete(long id){
		Mesto mesto = Mesto.findById(id);
		System.out.println(id);
		mesto.delete();
		show("");
	}

}
