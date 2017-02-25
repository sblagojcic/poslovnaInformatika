package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Preduzece extends Model {
	

	@Column(nullable=false, length=20) 
	public String nazivPreduzeca;
	
	@Column(nullable=false, length=20) 
	public String ulicaIBroj;
	
	@Column(nullable=false, length=9) 
	public int PIB;
	
	@Column(nullable=false, length=20) 
	public String telefon;
	
	@Column(nullable=false, length=20) 
	public String email;
	
	
	@OneToMany(mappedBy="preduzece")
	public List<Faktura>fakture;
	
	@OneToMany(mappedBy="preduzece")
	public List<PoslovniPartner>poslovniPartneri;
	
	@OneToMany(mappedBy="preduzece")
	public List<GrupaRobe>grupeRobe;
	
	@OneToMany(mappedBy="preduzece")
	public List<Cenovnik>cenovnici;
	
	@ManyToOne
	public Mesto mesto;

	public Preduzece(String nazivPreduzeca, String ulicaIBroj, int pIB, String telefon,
			String email, List<Faktura> fakture, List<PoslovniPartner> poslovniPartneri, List<GrupaRobe> grupeRobe,
			List<Cenovnik> cenovnici, Mesto mesto) {
		super();
		this.nazivPreduzeca = nazivPreduzeca;
		this.ulicaIBroj = ulicaIBroj;
		this.PIB = pIB;
		this.telefon = telefon;
		this.email = email;
		this.fakture = fakture;
		this.poslovniPartneri = poslovniPartneri;
		this.grupeRobe = grupeRobe;
		this.cenovnici = cenovnici;
		this.mesto = mesto;
	}

	public Preduzece() {
		super();
	}
	
	
}
