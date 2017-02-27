package models;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

public class StavkaNarudzbenice extends Model {

	@Column(nullable=false, precision=10, scale=2) 
	public float kolicina;
	
	@Column(nullable=false, length=2) 
	public String jedinicaMere;
	

	@ManyToOne
	public RobaIliUsluga robaIliUsluga;
	
	@ManyToOne
	public Narudzbenica narudzbenica;

	public StavkaNarudzbenice(float kolicina, String jedinicaMere, RobaIliUsluga robaIliUsluga,
			Narudzbenica narudzbenica) {
		super();
		this.kolicina = kolicina;
		this.jedinicaMere = jedinicaMere;
		this.robaIliUsluga = robaIliUsluga;
		this.narudzbenica = narudzbenica;
	}

	public StavkaNarudzbenice() {
		super();
	}
	
	
	
}