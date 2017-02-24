package models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Faktura extends Model{

	@Column(nullable=false, unique=true, length=50) 
	public String idFakture;
	
	@Column(nullable=false, length=6) 
	public int brojFaktura;
	
	@Column(nullable=false) 
	public Date datumFakture;
	
	@Column(nullable=false) 
	public Date datumValute;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float osnovica;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float ukupanPDV;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float iznosZaPlacanje;
	
	@Column(nullable=false, length=2) 
	public String statusFakture;
	
	
	@OneToMany(mappedBy="faktura")
	public List<StavkaFakture>stavkeFakture;
	
	@ManyToOne
	public PoslovnaGodina poslovnaGodina;
	
	@ManyToOne
	public Preduzece preduzece;
	
	@ManyToOne
	public PoslovniPartner poslovniPartner;


	public Faktura(String idFakture, int brojFaktura, Date datumFakture, Date datumValute, float osnovica,
			float ukupanPDV, float iznosZaPlacanje, String statusFakture, List<StavkaFakture> stavkeFakture,
			PoslovnaGodina poslovnaGodina, Preduzece preduzece, PoslovniPartner poslovniPartner) {
		super();
		this.idFakture = idFakture;
		this.brojFaktura = brojFaktura;
		this.datumFakture = datumFakture;
		this.datumValute = datumValute;
		this.osnovica = osnovica;
		this.ukupanPDV = ukupanPDV;
		this.iznosZaPlacanje = iznosZaPlacanje;
		this.statusFakture = statusFakture;
		this.stavkeFakture = stavkeFakture;
		this.poslovnaGodina = poslovnaGodina;
		this.preduzece = preduzece;
		this.poslovniPartner = poslovniPartner;
	}








	public Faktura() {
		super();
	}

	
	
}
