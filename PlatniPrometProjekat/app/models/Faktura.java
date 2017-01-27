package models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Faktura extends Model{

	@Column(nullable=false, unique=true, length=50) 
	public String idFakture;
	
	@Column(nullable=false, length=255) 
	public String nazivDobavljaca;
	
	@Column(nullable=false, length=255) 
	public String adresaDobavljaca;
	
	@Column(nullable=false, length=11) 
	public String PIBDobavljaca;
	
	@Column(nullable=false, length=55) 
	public String nazivKupca;
	
	@Column(nullable=false, length=55) 
	public String adresaKupca;
	
	@Column(nullable=false, length=11) 
	public String PIBKupca;
	
	@Column(nullable=false, length=6) 
	public int brojRacuna;
	
	@Column(nullable=false) 
	public Date datumRacuna;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float vrednostRoba;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float vrednostUsluga;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float ukupnoRobaIUsluga;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float ukupnoRabat;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float ukupnoPorez;
	
	@Column(nullable=false, length=3) 
	public String oznakaValute;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float iznosZaUplatu;
	
	@Column(nullable=false, length=18) 
	public String uplataNaRacun;
	
	@Column(nullable=false) 
	public Date datumValute;
	
	@OneToMany(mappedBy="Faktura")
	public List<StavkaFakture>stavkeFakture;

	public Faktura(String idFakture, String nazivDobavljaca, String adresaDobavljaca, String pIBDobavljaca,
			String nazivKupca, String adresaKupca, String pIBKupca, int brojRacuna, Date datumRacuna,
			float vrednostRoba, float vrednostUsluga, float ukupnoRobaIUsluga, float ukupnoRabat, float ukupnoPorez,
			String oznakaValute, float iznosZaUplatu, String uplataNaRacun, Date datumValute,
			List<StavkaFakture> stavkeFakture) {
		super();
		this.idFakture = idFakture;
		this.nazivDobavljaca = nazivDobavljaca;
		this.adresaDobavljaca = adresaDobavljaca;
		PIBDobavljaca = pIBDobavljaca;
		this.nazivKupca = nazivKupca;
		this.adresaKupca = adresaKupca;
		PIBKupca = pIBKupca;
		this.brojRacuna = brojRacuna;
		this.datumRacuna = datumRacuna;
		this.vrednostRoba = vrednostRoba;
		this.vrednostUsluga = vrednostUsluga;
		this.ukupnoRobaIUsluga = ukupnoRobaIUsluga;
		this.ukupnoRabat = ukupnoRabat;
		this.ukupnoPorez = ukupnoPorez;
		this.oznakaValute = oznakaValute;
		this.iznosZaUplatu = iznosZaUplatu;
		this.uplataNaRacun = uplataNaRacun;
		this.datumValute = datumValute;
		this.stavkeFakture = stavkeFakture;
	}
	
	
}
