package controllers;

import java.util.Date;
import java.util.List;

import models.GrupaRobe;
import models.PDV;
import models.Preduzece;
import models.RobaIliUsluga;
import play.data.validation.*;
import play.data.validation.Error;
import play.mvc.Controller;

public class RobeIliUsluge extends Controller {
	public static void show(String mode){
		List<RobaIliUsluga>robeIliUsluge=RobaIliUsluga.findAll();
		List<GrupaRobe>grupeRobe=GrupaRobe.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(robeIliUsluge,grupeRobe,mode);
	}
	public static void add(@Required String nazivRIU,String jedinicaMere,long grupaRobe) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			RobaIliUsluga robaIliUsluga=new RobaIliUsluga();
			robaIliUsluga.grupaRobe=GrupaRobe.findById(grupaRobe);
			robaIliUsluga.jedinicaMere=jedinicaMere;
			robaIliUsluga.nazivRIU=nazivRIU;
			robaIliUsluga.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(String nazivRIU,String jedinicaMere,long grupaRobe){
		List<RobaIliUsluga> robaIliUsluga = RobaIliUsluga.find("byNazivPDV", nazivRIU).fetch();
		String mode = "edit";
		renderTemplate("RobeIliUsluge/show.html", robaIliUsluga, mode);
	}
	public static void edit(String nazivRIU,String jedinicaMere,long grupaRobe, long id){
		RobaIliUsluga robaIliUsluga=RobaIliUsluga.findById(id);
		robaIliUsluga.grupaRobe=GrupaRobe.findById(grupaRobe);
		robaIliUsluga.jedinicaMere=jedinicaMere;
		robaIliUsluga.nazivRIU=nazivRIU;
		robaIliUsluga.save();
		show("");
	}	
	public static void delete(long id){
		PDV pDV = PDV.findById(id);
		pDV.delete();
		show("");
	}

}
