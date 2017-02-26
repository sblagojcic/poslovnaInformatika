package controllers;

import java.util.Date;
import java.util.List;

import models.GrupaRobe;
import models.PDV;
import models.Preduzece;
import play.data.validation.*;
import play.data.validation.Error;
import play.mvc.Controller;

public class GrupeRobe extends Controller {
	public static void show(String mode){
		List<GrupaRobe>grupeRobe=GrupaRobe.findAll();
		List<Preduzece>preduzeca=Preduzece.findAll();
		List<PDV>PDVovi=PDV.findAll();

		if (mode == null || mode.equals(""))
			mode = "edit";
		render(grupeRobe,preduzeca,PDVovi, mode);
	}
	public static void add(@Required String nazivGrupe, @Required long preduzece, long pDV) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			System.out.println(nazivGrupe);
			System.out.println(preduzece);
			System.out.println(pDV);
			GrupaRobe grupaRobe=new GrupaRobe();
			grupaRobe.nazivGrupe=nazivGrupe;
			grupaRobe.preduzece=Preduzece.findById(preduzece);
			System.out.println(grupaRobe.preduzece);
			System.out.println(grupaRobe.PDV);
			grupaRobe.PDV=PDV.findById(pDV);

			grupaRobe.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(long preduzece, long pDV, String nazivGrupe){
		List<GrupaRobe> grupeRobe = GrupaRobe.find("byPreduzece_idAndPDV_idAndNazivGrupeLike", preduzece, pDV, "%"+ nazivGrupe +"%").fetch();
		String mode = "edit";
		renderTemplate("GrupeRobe/show.html", grupeRobe, mode);
	}
	public static void edit(String nazivGrupe,long preduzece,long pDV,  long id){
		GrupaRobe grupaRobe = GrupaRobe.findById(id);
		grupaRobe.nazivGrupe = nazivGrupe;
		grupaRobe.preduzece = Preduzece.findById(preduzece);
		grupaRobe.PDV = PDV.findById(pDV);

		grupaRobe.save();
		show("");
	}	
	public static void delete(long id){
		GrupaRobe grupaRobe = GrupaRobe.findById(id);
		grupaRobe.delete();
		show("");
	}

}
