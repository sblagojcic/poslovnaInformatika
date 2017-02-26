package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class StopaPDV extends Model {
	

	@Column(nullable=false, length=3) 
	public int procenatPDV;
	
	@Column(nullable=false) 
	public Date datumVazenja;
	
	@ManyToOne
	public PDV PDV;

	public StopaPDV(int procenatPDV, Date datumVazenja, PDV pDV) {
		super();
		this.procenatPDV = procenatPDV;
		this.datumVazenja = datumVazenja;
		this.PDV = pDV;
	}

	public StopaPDV() {
		super();
	}	
	
	
}
