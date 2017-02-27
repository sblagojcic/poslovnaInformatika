package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Narudzbenica extends Model{

	
	@Column(nullable=false, length=6) 
	public int brojNarudzbenice;
	
	@OneToMany(mappedBy="narudzbenica")
	public List<StavkaNarudzbenice>stavkeNarudzbenice;
	
	@ManyToOne
	public PoslovniPartner poslovniPartner;
	
	@ManyToOne
	public PoslovnaGodina poslovnaGodina;
	




	public Narudzbenica(int brojNarudzbenice, List<StavkaNarudzbenice> stavkeNarudzbenice,
			PoslovniPartner poslovniPartner, PoslovnaGodina poslovnaGodina) {
		super();
		this.brojNarudzbenice = brojNarudzbenice;
		this.stavkeNarudzbenice = stavkeNarudzbenice;
		this.poslovniPartner = poslovniPartner;
		this.poslovnaGodina = poslovnaGodina;
	}





	public Narudzbenica() {
		super();
	}

	
	
}
