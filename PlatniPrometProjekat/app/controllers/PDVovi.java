package controllers;

import java.util.Date;
import java.util.List;

import models.PDV;
import models.Preduzece;
import play.data.validation.*;
import play.data.validation.Error;
import play.mvc.Controller;

public class PDVovi extends Controller {
	public static void show(String mode){
		List<PDV>pDVovi=PDV.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(pDVovi,mode);
	}
	public static void add(@Required String nazivPDV) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			PDV pDV=new PDV();
			pDV.nazivPDV=nazivPDV;
			pDV.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(String nazivPDV){
		List<PDV> pDVovi = PDV.find("byNazivPDV", nazivPDV).fetch();
		String mode = "edit";
		renderTemplate("PDVovi/show.html", pDVovi, mode);
	}
	public static void edit(String nazivPDV, long id){
		PDV pDV = PDV.findById(id);
		pDV.nazivPDV=nazivPDV;
		pDV.save();
		show("");
	}	
	public static void delete(long id){
		PDV pDV = PDV.findById(id);
		pDV.delete();
		show("");
	}

}
