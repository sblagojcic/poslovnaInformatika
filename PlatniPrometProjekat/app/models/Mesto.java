package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Mesto extends Model{


	
	@Column(nullable=false, length=20) 
	public String nazivMesta;
	
	
	@OneToMany(mappedBy="mesto")
	public List<PoslovniPartner>poslovniPartneri;
	
	@OneToMany(mappedBy="mesto")
	public List<Preduzece>preduzeca;

	public Mesto(String nazivMesta, List<PoslovniPartner> poslovniPartneri, List<Preduzece> preduzeca) {
		super();

		this.nazivMesta = nazivMesta;
		this.poslovniPartneri = poslovniPartneri;
		this.preduzeca = preduzeca;
	}

	public Mesto() {
		super();
	}
	
	
	
	
}
