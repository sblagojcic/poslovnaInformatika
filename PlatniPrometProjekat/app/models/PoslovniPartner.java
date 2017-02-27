package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class PoslovniPartner extends Model{

	@Column(nullable=false, length=30) 
	public String nazivPartnera;
	
	@Column(nullable=false, length=2) 
	public String vrstaPartnera;
	
	@Column(nullable=false, length=30) 
	public String ulicaIBroj;
	
	@ManyToOne
	public Mesto mesto;
	
	@ManyToOne
	public Preduzece preduzece;
	
	@OneToMany(mappedBy="poslovniPartner")
	public List<Faktura>faktura;
	
	@OneToMany(mappedBy="poslovniPartner")
	public List<Otpremnica>otpremnice;
	
	@OneToMany(mappedBy="poslovniPartner")
	public List<Narudzbenica>narudzbenice;

	public PoslovniPartner(String nazivPartnera, String vrstaPartnera, String ulicaIBroj, Mesto mesto,
			Preduzece preduzece, List<Faktura> faktura, List<Otpremnica> otpremnice, List<Narudzbenica> narudzbenice) {
		super();
		this.nazivPartnera = nazivPartnera;
		this.vrstaPartnera = vrstaPartnera;
		this.ulicaIBroj = ulicaIBroj;
		this.mesto = mesto;
		this.preduzece = preduzece;
		this.faktura = faktura;
		this.otpremnice = otpremnice;
		this.narudzbenice = narudzbenice;
	}

	public PoslovniPartner() {
		super();
	}



	
	
	
	
}
