package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class StavkaFakture extends Model {
	

	@Column(nullable=false, precision=10, scale=2) 
	public float kolicina;
	
	@Column(nullable=false, precision=10, scale=2) 
	public float jedinicnaCena;
	
	@Column(nullable=false, precision=12, scale=2) 
	public float rabat;
	
	@Column(nullable=false, precision=12, scale=2) 
	public float osnovica;
	
	@Column(nullable=false, precision=5, scale=2) 
	public float procenatPDV;
	
	@Column(nullable=false, precision=12, scale=2) 
	public float iznosPDV;
	
	@Column(nullable=false, precision=12, scale=2) 
	public float iznosStavke;
	
	@ManyToOne
	public Faktura faktura;
	
	@ManyToOne
	public RobaIliUsluga robaIliUsluga;

	public StavkaFakture(float kolicina, float jedinicnaCena, float rabat, float osnovica,
			float procenatPDV, float iznosPDV, float iznosStavke, Faktura faktura, RobaIliUsluga robaIliUsluga) {
		super();
		this.kolicina = kolicina;
		this.jedinicnaCena = jedinicnaCena;
		this.rabat = rabat;
		this.osnovica = osnovica;
		this.procenatPDV = procenatPDV;
		this.iznosPDV = iznosPDV;
		this.iznosStavke = iznosStavke;
		this.faktura = faktura;
		this.robaIliUsluga = robaIliUsluga;
	}

	public StavkaFakture() {
		super();
	}
}