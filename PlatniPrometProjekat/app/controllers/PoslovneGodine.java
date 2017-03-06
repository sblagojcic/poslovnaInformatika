package controllers;

import java.util.List;

import javafx.geometry.Pos;
import models.Mesto;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;

public class PoslovneGodine extends Controller {
	public static void show(String mode){
		List<PoslovnaGodina>poslovneGodine=PoslovnaGodina.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(poslovneGodine, mode);
	}
	public static void add(@Required int godina, boolean zakljucena) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			PoslovnaGodina poslovnaGodina=new PoslovnaGodina();
			System.out.println(zakljucena);
			poslovnaGodina.godina=godina;
			poslovnaGodina.zakljucena=zakljucena;
			poslovnaGodina.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(@Required int godina){
		List<PoslovnaGodina> poslovneGodine =PoslovnaGodina.find("byGodina", godina).fetch();
		String mode = "edit";
		renderTemplate("PoslovneGodine/show.html", poslovneGodine, mode);
	}
	public static void edit(@Required int godina, boolean zakljucena,long id){
		PoslovnaGodina poslovnaGodina = PoslovnaGodina.findById(id);
		poslovnaGodina.godina=godina;
		poslovnaGodina.zakljucena=zakljucena;
		poslovnaGodina.save();
		show("");
	}	
	public static void delete(long id){
		PoslovnaGodina poslovnaGodina = PoslovnaGodina.findById(id);
		System.out.println(id);
		poslovnaGodina.delete();
		show("");
	}

}

