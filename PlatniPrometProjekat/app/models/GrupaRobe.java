package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class GrupaRobe extends Model{

	
	@Column(nullable=false, length=20) 
	public String nazivGrupe;
	
	@ManyToOne
	public PDV PDV;
	
	@ManyToOne
	public Preduzece preduzece;
	
	@OneToMany(mappedBy="grupaRobe")
	public List<RobaIliUsluga>robeIliUsluge;


	public GrupaRobe(int idRobe, String nazivGrupe, PDV PDV, Preduzece preduzece,
			List<RobaIliUsluga> robeIliUsluge) {
		super();
		this.nazivGrupe = nazivGrupe;
		this.PDV = PDV;
		this.preduzece = preduzece;
		this.robeIliUsluge = robeIliUsluge;
	}







	public GrupaRobe() {
		super();
	}
	
	

	
}
