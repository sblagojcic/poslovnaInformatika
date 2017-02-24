package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class PoslovnaGodina extends Model {

	@Column(nullable=false, unique=true, length=50) 
	public String idGodine;
	
	@Column(nullable=false, length=4) 
	public int godina;
	
	@Column(nullable=false)
	public boolean zakljucena;
	
	
	@OneToMany(mappedBy="PoslovnaGodina")
	public List<Faktura>fakture;


	public PoslovnaGodina(String idGodine, int godina, boolean zakljucena, List<Faktura> fakture) {
		super();
		this.idGodine = idGodine;
		this.godina = godina;
		this.zakljucena = zakljucena;
		this.fakture = fakture;
	}


	public PoslovnaGodina() {
		super();
	}
	
	
	
}
