package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class GrupaRobe extends Model{
	@Column(nullable=false, unique=true, length=3) 
	public int idRobe;
	
	@Column(nullable=false, length=20) 
	public String nazivGrupe;
	
	@ManyToOne
	public PDV PDV;
	
	@ManyToOne
	public Preduzece preduzece;
	
	@OneToMany(mappedBy="GrupaRobe")
	public List<RobaIliUsluga>robeIliUsluge;


	public GrupaRobe(int idRobe, String nazivGrupe, models.PDV pDV, Preduzece preduzece,
			List<RobaIliUsluga> robeIliUsluge) {
		super();
		this.idRobe = idRobe;
		this.nazivGrupe = nazivGrupe;
		PDV = pDV;
		this.preduzece = preduzece;
		this.robeIliUsluge = robeIliUsluge;
	}







	public GrupaRobe() {
		super();
	}
	
	

	
}
