package controllers;

import java.util.List;

import models.Otpremnica;
import models.RobaIliUsluga;
import models.StavkaCenovnika;
import models.StavkaOtpremnice;
import play.data.validation.Error;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.mvc.Controller;

public class StavkeOtpremnice extends Controller {
	public static void show(String mode){
		List<StavkaOtpremnice>stavkeOtpremnice=StavkaOtpremnice.findAll();
		List<RobaIliUsluga>robaIliUsluge=RobaIliUsluga.findAll();
		List<Otpremnica>otpremnice=Otpremnica.findAll();

		if (mode == null || mode.equals(""))
			mode = "edit";
		render(stavkeOtpremnice,robaIliUsluge,otpremnice, mode);
	}
	public static void add(@Required float kolicina,float jedinicnaCena,float rabat,float osnovica, float procenatPDV, float iznosPDV, float iznosStavke, @Required long robaIliUsluga, long otpremnica) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			StavkaOtpremnice stavkaOtpremnice=new StavkaOtpremnice();
			Otpremnica otpremnicaa = Otpremnica.findById(otpremnica);
			stavkaOtpremnice.kolicina=kolicina;
			stavkaOtpremnice.jedinicnaCena=jedinicnaCena;
			stavkaOtpremnice.rabat=rabat;
			stavkaOtpremnice.osnovica=osnovica;
			stavkaOtpremnice.procenatPDV=procenatPDV;
			stavkaOtpremnice.iznosPDV=iznosPDV;
			stavkaOtpremnice.iznosStavke=iznosStavke;
			stavkaOtpremnice.robaIliUsluga=RobaIliUsluga.findById(robaIliUsluga);
			stavkaOtpremnice.otpremnica=otpremnicaa;
			otpremnicaa.iznosZaPlacanje+=stavkaOtpremnice.iznosStavke;
			otpremnicaa.osnovica+=stavkaOtpremnice.osnovica;
			otpremnicaa.ukupanPDV+=stavkaOtpremnice.iznosPDV;
			stavkaOtpremnice.save();
			otpremnicaa.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(long robaIliUsluga, long otpremnica, float kolicina,float jedinicnaCena,float rabat,float osnovica, float procenatPDV, float iznosPDV, float iznosStavke){
		List<StavkaOtpremnice> stavkeOtpremnice = StavkaOtpremnice.find("byRobaIliUsluga_idAndKolicinaAndJedinicnaCenaAndRabatAndOsnovicaAndProcenatPDVAndIznosPDVAndIznosStavkeAndOtpremnica", robaIliUsluga,kolicina, jedinicnaCena, rabat, osnovica, procenatPDV, iznosPDV, iznosStavke,otpremnica).fetch();
		String mode = "edit";
		renderTemplate("StavkeOtpremnice/show.html", stavkeOtpremnice, mode);
	}
	public static void edit(long robaIliUsluga, long otpremnica, float kolicina,float jedinicnaCena,float rabat,float osnovica, float procenatPDV, float iznosPDV, float iznosStavke, long id){
		StavkaOtpremnice stavkaOtpremnice = StavkaOtpremnice.findById(id);
		Otpremnica otpremnicaa = Otpremnica.findById(otpremnica);
		stavkaOtpremnice.kolicina = kolicina;
		stavkaOtpremnice.jedinicnaCena = jedinicnaCena;
		stavkaOtpremnice.rabat = rabat;
		stavkaOtpremnice.osnovica = osnovica;
		stavkaOtpremnice.procenatPDV = procenatPDV;
		stavkaOtpremnice.iznosPDV = iznosPDV;
		stavkaOtpremnice.iznosStavke = iznosStavke;
		stavkaOtpremnice.otpremnica = otpremnicaa;
		otpremnicaa.iznosZaPlacanje+=stavkaOtpremnice.iznosStavke;
		otpremnicaa.osnovica+=stavkaOtpremnice.osnovica;
		otpremnicaa.ukupanPDV+=stavkaOtpremnice.iznosPDV;
		stavkaOtpremnice.robaIliUsluga = RobaIliUsluga.findById(robaIliUsluga);

		stavkaOtpremnice.save();
		otpremnicaa.save();
		show("");
	}	
	public static void delete(long id){
		StavkaOtpremnice stavkaOtpremnice = StavkaOtpremnice.findById(id);
		stavkaOtpremnice.delete();
		show("");
	}

}
