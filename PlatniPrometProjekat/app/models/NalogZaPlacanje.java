package models;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class NalogZaPlacanje extends Model {
	
	@Column(nullable=false, unique=true, length=50) 
	public String idNaloga;
	
	@Column(nullable=false, length=255) 
	public String duznik;

	@Column(nullable=false, length=255) 
	public String svrhaPlacanja;
	
	@Column(nullable=false, length=255) 
	public String poverilac;
	
	@Column(nullable=false) 
	public Date datumNaloga;
	
	@Column(nullable=false) 
	public Date datumValute;
	
	@Column(nullable=false, length=18) 
	public String racunDuznika;
	
	@Column(nullable=false, length=2) 
	public int modelZaduzenja;
	
	@Column(nullable=false, length=20) 
	public String pozivNaBrojZaduzenja;
	
	@Column(nullable=false, length=18) 
	public String racunPoverioca;
	
	@Column(nullable=false, length=2) 
	public int modelOdobrenja;
	
	@Column(nullable=false, length=20) 
	public String pozivNaBrojOdobrenja;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float iznos;
	
	@Column(nullable=false, length=3) 
	public String oznakaValute;
	
	@Column(nullable=false) 
	public Boolean hitno;

	public NalogZaPlacanje(String idNaloga, String duznik, String svrhaPlacanja, String poverilac, Date datumNaloga,
			Date datumValute, String racunDuznika, int modelZaduzenja, String pozivNaBrojZaduzenja,
			String racunPoverioca, int modelOdobrenja, String pozivNaBrojOdobrenja, float iznos, String oznakaValute,
			Boolean hitno) {
		super();
		this.idNaloga = idNaloga;
		this.duznik = duznik;
		this.svrhaPlacanja = svrhaPlacanja;
		this.poverilac = poverilac;
		this.datumNaloga = datumNaloga;
		this.datumValute = datumValute;
		this.racunDuznika = racunDuznika;
		this.modelZaduzenja = modelZaduzenja;
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
		this.racunPoverioca = racunPoverioca;
		this.modelOdobrenja = modelOdobrenja;
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
		this.iznos = iznos;
		this.oznakaValute = oznakaValute;
		this.hitno = hitno;
	}
	
	
	
	
}
