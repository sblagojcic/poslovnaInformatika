package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class StavkaCenovnika extends Model{
	
	@Column(nullable=false, precision=15, scale=2) 
	public float cena;
	
	@ManyToOne
	public RobaIliUsluga robaIliUsluga;
	
	@ManyToOne
	public Cenovnik cenovnik;

	public StavkaCenovnika(float cena, RobaIliUsluga robaIliUsluga, Cenovnik cenovnik) {
		super();
		this.cena = cena;
		this.robaIliUsluga = robaIliUsluga;
		this.cenovnik = cenovnik;
	}

	public StavkaCenovnika() {
		super();
	}
	
	
	
}
