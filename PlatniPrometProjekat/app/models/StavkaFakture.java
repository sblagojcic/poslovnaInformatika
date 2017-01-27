package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class StavkaFakture extends Model {
	
	@Column(nullable=false, unique=true, length=3) 
	public int redniBroj;
	
	@Column(nullable=false, length=120) 
	public String nazivStavke;
	
	@Column(nullable=false, precision=10, scale=2) 
	public float kolicina;
	
	@Column(nullable=false, length=6) 
	public String jedinicaMere;
	
	@Column(nullable=false, precision=10, scale=2) 
	public float jedinicnaCena;
	
	@Column(nullable=false, precision=12, scale=2) 
	public float vrednost;
	
	@Column(nullable=false, precision=5, scale=2) 
	public float procenatRabata;
	
	@Column(nullable=false, precision=12, scale=2) 
	public float iznosRabata;
	
	@Column(nullable=false, precision=12, scale=2) 
	public float umanjenoZaRabat;
	
	@Column(nullable=false, precision=12, scale=2) 
	public float ukupanPorez;
	
	@ManyToOne
	public Faktura faktura;

	public StavkaFakture(int redniBroj, String nazivStavke, float kolicina, String jedinicaMere, float jedinicnaCena,
			float vrednost, float procenatRabata, float iznosRabata, float umanjenoZaRabat, float ukupanPorez,
			Faktura faktura) {
		super();
		this.redniBroj = redniBroj;
		this.nazivStavke = nazivStavke;
		this.kolicina = kolicina;
		this.jedinicaMere = jedinicaMere;
		this.jedinicnaCena = jedinicnaCena;
		this.vrednost = vrednost;
		this.procenatRabata = procenatRabata;
		this.iznosRabata = iznosRabata;
		this.umanjenoZaRabat = umanjenoZaRabat;
		this.ukupanPorez = ukupanPorez;
		this.faktura = faktura;
	}
	
	
}
