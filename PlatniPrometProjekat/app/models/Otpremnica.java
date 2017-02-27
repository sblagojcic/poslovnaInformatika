package models;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



import play.db.jpa.Model;

@Entity
public class Otpremnica extends Model{

	
	@Column(nullable=false, length=6) 
	public int brojOtpremnice;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float osnovica;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float ukupanPDV;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float iznosZaPlacanje;
	
	
	@OneToMany(mappedBy="otpremnica")
	public List<StavkaOtpremnice>stavkeOtpremnice;

	@ManyToOne
	public PoslovnaGodina poslovnaGodina;
	
	@ManyToOne 
	public PoslovniPartner poslovniPartner;


	public Otpremnica(int brojOtpremnice, float osnovica, float ukupanPDV, float iznosZaPlacanje,
			List<StavkaOtpremnice> stavkeOtpremnice, PoslovnaGodina poslovnaGodina, PoslovniPartner poslovniPartner) {
		super();
		this.brojOtpremnice = brojOtpremnice;
		this.osnovica = osnovica;
		this.ukupanPDV = ukupanPDV;
		this.iznosZaPlacanje = iznosZaPlacanje;
		this.stavkeOtpremnice = stavkeOtpremnice;
		this.poslovnaGodina = poslovnaGodina;
		this.poslovniPartner = poslovniPartner;
	}


	public Otpremnica() {
		super();
	}

	

	
	
}

