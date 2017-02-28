package controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import models.Faktura;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;
import models.StavkaFakture;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;

public class Fakture extends Controller {
	public static void show(String mode){
		List<Faktura>fakture=Faktura.findAll();
		List<Preduzece>preduzeca=Preduzece.findAll();
		List<PoslovnaGodina> poslovneGodine=PoslovnaGodina.findAll();
		List<PoslovniPartner> poslovniPartneri=PoslovniPartner.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(fakture,preduzeca,poslovneGodine,poslovniPartneri, mode);
	}
	public static void add(@Required Date datumFakture,@Required int brojFaktura,
			@Required Date datumValute,@Required long osnovica,@Required long ukupanPDV,
			@Required long iznosZaPlacanje,@Required String statusFakture,
			@Required long preduzece, @Required long poslovniPartner, @Required long poslovnaGodina) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			Faktura faktura=new Faktura();
			faktura.brojFaktura=brojFaktura;
			faktura.datumFakture=datumFakture;
			faktura.datumValute=datumValute;
			faktura.osnovica=osnovica;
			faktura.ukupanPDV=ukupanPDV;
			faktura.iznosZaPlacanje=iznosZaPlacanje;
			faktura.statusFakture=statusFakture;
			faktura.preduzece=Preduzece.findById(preduzece);
			faktura.poslovniPartner=PoslovniPartner.findById(poslovniPartner);
			faktura.poslovnaGodina=PoslovnaGodina.findById(poslovnaGodina);
			faktura.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(Date datum, long preduzece){
		List<Faktura> fakture = Faktura.find("byPreduzece_id", preduzece).fetch();
		String mode = "edit";
		renderTemplate("Fakture/show.html", fakture, mode);
	}
	public static void edit(@Required Date datumFakture,@Required int brojFaktura,
			@Required Date datumValute,@Required long osnovica,@Required long ukupanPDV,
			@Required long iznosZaPlacanje,@Required String statusFakture,
			@Required long preduzece, @Required long poslovniPartner,
			@Required long poslovnaGodina,long id){
		Faktura faktura = Faktura.findById(id);
		faktura.brojFaktura=brojFaktura;
		faktura.datumFakture=datumFakture;
		faktura.datumValute=datumValute;
		faktura.osnovica=osnovica;
		faktura.ukupanPDV=ukupanPDV;
		faktura.iznosZaPlacanje=iznosZaPlacanje;
		faktura.statusFakture=statusFakture;
		faktura.preduzece=Preduzece.findById(preduzece);
		faktura.poslovniPartner=PoslovniPartner.findById(poslovniPartner);
		faktura.poslovnaGodina=PoslovnaGodina.findById(poslovnaGodina);
		faktura.save();
		show("");
	}	
	public static void delete(long id){
		Faktura faktura = Faktura.findById(id);
		faktura.delete();
		show("");
	}
	public static void export(long id) throws IOException{
		String FILENAME = "D:\\filename.xml";

		Faktura faktura = Faktura.findById(id);
		String xmlString="<?xml version='1.0' encoding='UTF-8'?>\n"+"<Faktura> \n"
				+"\t<brojFaktura>"+faktura.brojFaktura+"</brojFaktura>\n"
				+"\t<datumFakture>"+faktura.datumFakture+"</datumFakture>\n"
				+"\t<datumValute>"+faktura.datumValute+"</datumValute>\n"
				+"\t<osnovica>"+faktura.osnovica+"</osnovica>\n"
				+"\t<ukupanPDV>"+faktura.ukupanPDV+"</ukupanPDV>\n"
				+"\t<iznosZaPlacanje>"+faktura.iznosZaPlacanje+"</iznosZaPlacanje>\n"
				+"\t<statusFakture>"+faktura.statusFakture+"</statusFakture>\n"
				+"\t<poslovniPartner>"+faktura.poslovniPartner.nazivPartnera+"</poslovniPartner>\n"
				+"\t<poslovnaGodina>"+faktura.poslovnaGodina.godina+"</poslovnaGodina>\n"
				+"\t<stavkeFakture>\n";
		for (StavkaFakture stavka : faktura.stavkeFakture) {
			xmlString=xmlString+"\t\t<stavkaFakture>\n"
					+"\t\t\t<kolicina>"+stavka.kolicina+"</kolicina>\n"
					+"\t\t\t<jedinicnaCena>"+stavka.jedinicnaCena+"</jedinicnaCena>\n"
					+"\t\t\t<rabat>"+stavka.rabat+"</rabat>\n"
					+"\t\t\t<osnovica>"+stavka.osnovica+"</osnovica>\n"
					+"\t\t\t<procenatPDV>"+stavka.procenatPDV+"</procenatPDV>\n"
					+"\t\t\t<iznosPDV>"+stavka.iznosPDV+"</iznosPDV>\n"
					+"\t\t\t<iznosStavke>"+stavka.iznosStavke+"</iznosStavke>\n"
					+"\t\t\t<robaIliUsluga>"+stavka.robaIliUsluga.nazivRIU+"</robaIliUsluga>\n"
					+"\t\t</stavkaFakture>\n";
		}
		xmlString=xmlString+"\t</stavkeFakture>\n"
				+"</Faktura> \n";
		System.out.println(xmlString);
		FileWriter fw = new FileWriter(FILENAME);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(xmlString);
		bw.close();
		show("");
	}	
}
