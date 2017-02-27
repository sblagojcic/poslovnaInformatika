package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class StavkaOtpremnice extends Model {
	

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
	public Otpremnica otpremnica;
	
	@ManyToOne
	public RobaIliUsluga robaIliUsluga;

	public StavkaOtpremnice(float kolicina, float jedinicnaCena, float rabat, float osnovica, float procenatPDV,
			float iznosPDV, float iznosStavke, Otpremnica otpremnica, RobaIliUsluga robaIliUsluga) {
		super();
		this.kolicina = kolicina;
		this.jedinicnaCena = jedinicnaCena;
		this.rabat = rabat;
		this.osnovica = osnovica;
		this.procenatPDV = procenatPDV;
		this.iznosPDV = iznosPDV;
		this.iznosStavke = iznosStavke;
		this.otpremnica = otpremnica;
		this.robaIliUsluga = robaIliUsluga;
	}

	public StavkaOtpremnice() {
		super();
	}

	
}
