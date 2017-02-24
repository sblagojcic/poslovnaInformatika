package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class RobaIliUsluga extends Model{
	
	@Column(nullable=false, unique=true, length=3) 
	public int idRIU;
	
	@Column(nullable=false, length=20) 
	public String nazivRIU;
	
	@Column(nullable=false, length=12) 
	public String jedinicaMere;
	
	@ManyToOne
	public GrupaRobe grupaRobe;
	
	@OneToMany(mappedBy="robaIliUsluga")
	public List<StavkaFakture>stavkeFakture;
	
	@OneToMany(mappedBy="robaIliUsluga")
	public List<StavkaCenovnika>stavkeCenovnika;



	public RobaIliUsluga(int idRIU, String nazivRIU, String jedinicaMere, GrupaRobe grupaRobe,
			List<StavkaFakture> stavkeFakture, List<StavkaCenovnika> stavkeCenovnika) {
		super();
		this.idRIU = idRIU;
		this.nazivRIU = nazivRIU;
		this.jedinicaMere = jedinicaMere;
		this.grupaRobe = grupaRobe;
		this.stavkeFakture = stavkeFakture;
		this.stavkeCenovnika = stavkeCenovnika;
	}



	public RobaIliUsluga() {
		super();
	}
	
	

}
