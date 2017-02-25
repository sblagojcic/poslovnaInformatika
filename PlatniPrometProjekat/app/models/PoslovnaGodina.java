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


	public PoslovnaGodina(int godina, boolean zakljucena, List<Faktura> fakture) {
		super();
		this.godina = godina;
		this.zakljucena = zakljucena;
		this.fakture = fakture;
	}


	public PoslovnaGodina() {
		super();
	}
	
	
	
}
