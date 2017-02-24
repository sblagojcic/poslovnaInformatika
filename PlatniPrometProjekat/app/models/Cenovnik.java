package models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Cenovnik extends Model {
	
	@Column(nullable=false, unique=true, length=50) 
	public String idCenovnika;
	
	@Column(nullable=false) 
	public Date datumVazenja;
	
	@OneToMany(mappedBy="cenovnik")
	public List<StavkaCenovnika>stavkeCenovnika;
	
	@ManyToOne
	public Preduzece preduzece;



	public Cenovnik(String idCenovnika, Date datumVazenja, List<StavkaCenovnika> stavkeCenovnika, Preduzece preduzece) {
		super();
		this.idCenovnika = idCenovnika;
		this.datumVazenja = datumVazenja;
		this.stavkeCenovnika = stavkeCenovnika;
		this.preduzece = preduzece;
	}



	public Cenovnik() {
		super();
	}
	
	
}
