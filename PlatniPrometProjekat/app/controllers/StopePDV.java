package controllers;

import java.util.Date;
import java.util.List;

import models.StopaPDV;
import models.PDV;
import models.Preduzece;
import play.data.validation.*;
import play.data.validation.Error;
import play.mvc.Controller;

public class StopePDV extends Controller {
	public static void show(String mode){
		List<StopaPDV>stopePDV=StopaPDV.findAll();
		List<PDV>pDVovi=PDV.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(stopePDV,pDVovi,mode);
	}
	public static void add(@Required Date datumVazenja,int procenatPDV,long pDV) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			StopaPDV stopaPDV=new StopaPDV();
			stopaPDV.datumVazenja=datumVazenja;
			stopaPDV.procenatPDV=procenatPDV;
			stopaPDV.PDV=PDV.findById(pDV);
			stopaPDV.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(String nazivStopaPDV){
		List<StopaPDV> stopePDV = StopaPDV.find("byNazivStopaPDV", nazivStopaPDV).fetch();
		String mode = "edit";
		renderTemplate("StopaStopePDV/show.html", stopePDV, mode);
	}
	public static void edit(Date datumVazenja,int procenatPDV,long pDV, long id){
		StopaPDV stopaPDV = StopaPDV.findById(id);
		stopaPDV.datumVazenja=datumVazenja;
		stopaPDV.procenatPDV=procenatPDV;
		stopaPDV.PDV=PDV.findById(pDV);
		stopaPDV.save();
		show("");
	}	
	public static void delete(long id){
		StopaPDV stopaPDV = StopaPDV.findById(id);
		stopaPDV.delete();
		show("");
	}

}
