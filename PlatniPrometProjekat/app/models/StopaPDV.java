package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class StopaPDV extends Model {
	
	@Column(nullable=false, unique=true, length=3) 
	public int idStope;
	
	@Column(nullable=false, length=3) 
	public int procenatPDV;
	
	@Column(nullable=false) 
	public Date datumVazenja;
	
	@ManyToOne
	public PDV PDV;

	public StopaPDV(int idStope, int procenatPDV, Date datumVazenja, models.PDV pDV) {
		super();
		this.idStope = idStope;
		this.procenatPDV = procenatPDV;
		this.datumVazenja = datumVazenja;
		PDV = pDV;
	}

	public StopaPDV() {
		super();
	}	
	
	
}
