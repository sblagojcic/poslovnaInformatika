package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Mesto extends Model{

	@Column(nullable=false, unique=true, length=50) 
	public String idMesta;
	
	@Column(nullable=false, length=20) 
	public String nazivMesta;
	
	
	@OneToMany(mappedBy="Mesto")
	public List<PoslovniPartner>poslovniPartneri;
	
	@OneToMany(mappedBy="Mesto")
	public List<Preduzece>preduzeca;

	public Mesto(String idMesta, String nazivMesta, List<PoslovniPartner> poslovniPartneri, List<Preduzece> preduzeca) {
		super();
		this.idMesta = idMesta;
		this.nazivMesta = nazivMesta;
		this.poslovniPartneri = poslovniPartneri;
		this.preduzeca = preduzeca;
	}

	public Mesto() {
		super();
	}
	
	
	
	
}
