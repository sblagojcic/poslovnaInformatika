package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class PoslovnaGodina extends Model {

	
	@Column(nullable=false, length=4) 
	public int godina;
	
	@Column(nullable=false)
	public boolean zakljucena;
	
	
	@OneToMany(mappedBy="poslovnaGodina")
	public List<Faktura>fakture;
	
	@OneToMany(mappedBy="poslovnaGodina")
	public List<Otpremnica>otpremnice;
	
	@OneToMany(mappedBy="poslovnaGodina")
	public List<Narudzbenica>narudzbenice;

	public PoslovnaGodina(int godina, boolean zakljucena, List<Faktura> fakture, List<Otpremnica> otpremnice,
			List<Narudzbenica> narudzbenice) {
		super();
		this.godina = godina;
		this.zakljucena = zakljucena;
		this.fakture = fakture;
		this.otpremnice = otpremnice;
		this.narudzbenice = narudzbenice;
	}

	public PoslovnaGodina() {
		super();
	}



	
	
	
}
