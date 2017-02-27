package controllers;

import java.util.Date;
import java.util.List;

import models.Faktura;
import models.Narudzbenica;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;
import models.StavkaFakture;
import models.StavkaNarudzbenice;
import play.data.validation.Error;
import play.data.validation.Required;
import play.libs.F;
import play.mvc.Controller;

public class Narudzbenice extends Controller {
	public static void show(String mode){
		List<Narudzbenica>narudzbenice=Narudzbenica.findAll();
		List<PoslovnaGodina> poslovneGodine=PoslovnaGodina.findAll();
		List<PoslovniPartner> poslovniPartneri=PoslovniPartner.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(narudzbenice,poslovneGodine,poslovniPartneri, mode);
	}
	public static void add(@Required int brojNarudzbenice, @Required long poslovniPartner, @Required long poslovnaGodina) {
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			show("add");
		}else {
			Narudzbenica narudzbenica=new Narudzbenica();
			narudzbenica.brojNarudzbenice=brojNarudzbenice;
			narudzbenica.poslovniPartner=PoslovniPartner.findById(poslovniPartner);
			narudzbenica.poslovnaGodina=PoslovnaGodina.findById(poslovnaGodina);
			narudzbenica.save();
			validation.keep();
			show("add");
	}
	}
	public static void filter(long poslovniPartner, long poslovnaGodina, int brojNarudzbenice){
		List<Narudzbenica> narudzbenice = Narudzbenica.find("byPoslovniPartner_idAndPoslovnaGodina_idAndBrojNarudzbenice", poslovnaGodina, poslovnaGodina, brojNarudzbenice).fetch();
		String mode = "edit";
		renderTemplate("Narudzbenice/show.html", narudzbenice, mode);
	}
	public static void edit(@Required int brojNarudzbenice, @Required long poslovniPartner,
			@Required long poslovnaGodina,long id){
		Narudzbenica narudzbenica = Narudzbenica.findById(id);
		narudzbenica.brojNarudzbenice=brojNarudzbenice;
		narudzbenica.poslovniPartner=PoslovniPartner.findById(poslovniPartner);
		narudzbenica.poslovnaGodina=PoslovnaGodina.findById(poslovnaGodina);
		narudzbenica.save();
		show("");
	}	
	public static void delete(long id){
		Narudzbenica narudzbenica = Narudzbenica.findById(id);
		narudzbenica.delete();
		show("");
	}

	public static void generate(long id){
		Narudzbenica narudzbenica=Narudzbenica.findById(id);
		List<Faktura>fakture=Faktura.findAll();
		int p=fakture.size();
        Faktura faktura=new Faktura();
		
		Faktura zaBroj=fakture.get(p-1);
		faktura.brojFaktura=zaBroj.brojFaktura+1;
		faktura.datumFakture=new Date();
		faktura.datumValute=new Date();
		faktura.osnovica=0;
		faktura.poslovniPartner=narudzbenica.poslovniPartner;
		faktura.poslovnaGodina=narudzbenica.poslovnaGodina;
		faktura.iznosZaPlacanje=faktura.osnovica+faktura.ukupanPDV;
		faktura.preduzece=narudzbenica.poslovniPartner.preduzece;
		faktura.statusFakture="da";
		faktura.ukupanPDV=0;
		faktura.save();
		float PDV=0;
		long fId=0;
		float cena=0;
		float osnovicaa=0;
		for (StavkaNarudzbenice stavkaN : narudzbenica.stavkeNarudzbenice) {
			StavkaFakture stavkaFakture=new StavkaFakture();
			stavkaFakture.kolicina=stavkaN.kolicina;
			stavkaFakture.jedinicnaCena=stavkaN.robaIliUsluga.stavkeCenovnika.get(0).cena;
			stavkaFakture.osnovica=stavkaFakture.kolicina*stavkaFakture.jedinicnaCena;
			stavkaFakture.procenatPDV=stavkaN.robaIliUsluga.grupaRobe.PDV.stopePDV.get(0).procenatPDV;
			stavkaFakture.iznosPDV=stavkaFakture.osnovica*stavkaFakture.procenatPDV;
			stavkaFakture.iznosStavke=stavkaFakture.osnovica+stavkaFakture.iznosPDV;
			stavkaFakture.robaIliUsluga=stavkaN.robaIliUsluga;
			stavkaFakture.faktura=faktura;
			stavkaFakture.rabat=0;
			stavkaFakture.save();
			PDV=PDV+stavkaFakture.iznosPDV;
			cena=cena+stavkaFakture.iznosStavke;
			osnovicaa=osnovicaa+stavkaFakture.osnovica;
			fId=stavkaFakture.faktura.getId();
		}
		Faktura faktura2=Faktura.findById(fId);
		faktura2.ukupanPDV=PDV;
		faktura2.iznosZaPlacanje=cena;
		faktura2.osnovica=osnovicaa;
		faktura2.save();
		Fakture.show("");
	}
}

