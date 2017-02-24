package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class PoslovniPartner<Preduzece> extends Model{

	@Column(nullable=false, unique=true, length=50) 
	public String idPartnera;
	
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
	
	@OneToMany(mappedBy="PoslovniPartner")
	public List<Faktura>fakture;

	public PoslovniPartner(String idPartnera, String nazivPartnera, String vrstaPartnera, String ulicaIBroj,
			Mesto mesto, Preduzece preduzece, List<Faktura> fakture) {
		super();
		this.idPartnera = idPartnera;
		this.nazivPartnera = nazivPartnera;
		this.vrstaPartnera = vrstaPartnera;
		this.ulicaIBroj = ulicaIBroj;
		this.mesto = mesto;
		this.preduzece = preduzece;
		this.fakture = fakture;
	}

	public PoslovniPartner() {
		super();
	}
	
	
	
	
}
