package controllers;

import java.util.List;

import models.Cenovnik;
import models.Faktura;
import models.RobaIliUsluga;
import models.StavkaCenovnika;
import models.StavkaFakture;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;

public class StavkeFakture extends Controller {
	public static void show(String mode){
		List<StavkaFakture>stavkeFakture=StavkaFakture.findAll();
		List<RobaIliUsluga>robaIliUsluge=RobaIliUsluga.findAll();
		List<Faktura>fakture=Faktura.findAll();

		if (mode == null || mode.equals(""))
			mode = "edit";
		render(stavkeFakture,robaIliUsluge,fakture, mode);
	}
	public static void add(@Required float kolicina,float jedinicnaCena,float rabat,float osnovica, float procenatPDV, float iznosPDV, float iznosStavke, @Required long robaIliUsluga, long faktura) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			StavkaFakture stavkaFakture=new StavkaFakture();
			stavkaFakture.kolicina=kolicina;
			stavkaFakture.jedinicnaCena=jedinicnaCena;
			stavkaFakture.rabat=rabat;
			stavkaFakture.osnovica=osnovica;
			stavkaFakture.procenatPDV=procenatPDV;
			stavkaFakture.iznosPDV=iznosPDV;
			stavkaFakture.iznosStavke=iznosStavke;
			stavkaFakture.robaIliUsluga=RobaIliUsluga.findById(robaIliUsluga);
			stavkaFakture.faktura=Faktura.findById(faktura);
			stavkaFakture.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(long robaIliUsluga, long faktura, float kolicina,float jedinicnaCena,float rabat,float osnovica, float procenatPDV, float iznosPDV, float iznosStavke){
		List<StavkaFakture> stavkeFakture = StavkaCenovnika.find("byRobaIliUsluga_idAndCenovnik_idAndKolicinaAndJedinicnaCenaAndRabatAndOsnovicaAndProcenatPDVAndIznosPDVAndIznosStavke", robaIliUsluga,faktura,kolicina, jedinicnaCena, rabat, osnovica, procenatPDV, iznosPDV, iznosStavke).fetch();
		String mode = "edit";
		renderTemplate("StavkeFakture/show.html", stavkeFakture, mode);
	}
	public static void edit(long robaIliUsluga, long faktura, float kolicina,float jedinicnaCena,float rabat,float osnovica, float procenatPDV, float iznosPDV, float iznosStavke, long id){
		StavkaFakture stavkaFakture = StavkaFakture.findById(id);
		stavkaFakture.kolicina = kolicina;
		stavkaFakture.jedinicnaCena = jedinicnaCena;
		stavkaFakture.rabat = rabat;
		stavkaFakture.osnovica = osnovica;
		stavkaFakture.procenatPDV = procenatPDV;
		stavkaFakture.iznosPDV = iznosPDV;
		stavkaFakture.iznosStavke = iznosStavke;
		stavkaFakture.faktura = Faktura.findById(faktura);
		stavkaFakture.robaIliUsluga = RobaIliUsluga.findById(robaIliUsluga);

		stavkaFakture.save();
		show("");
	}	
	public static void delete(long id){
		StavkaFakture stavkaFakture = StavkaFakture.findById(id);
		stavkaFakture.delete();
		show("");
	}

}

