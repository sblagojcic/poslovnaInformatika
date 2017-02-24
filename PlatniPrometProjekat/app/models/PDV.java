package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class PDV extends Model{
	
	@Column(nullable=false, unique=true, length=3) 
	public int idPDV;
	
	@Column(nullable=false, length=20) 
	public String nazivPDV;
	
	
	@OneToMany(mappedBy="PDV")
	public List<GrupaRobe>grupeRoba;
	
	@OneToMany(mappedBy="PDV")
	public List<StopaPDV>stopePDV;

	public PDV(int idPDV, String nazivPDV, List<GrupaRobe> grupeRoba, List<StopaPDV> stopePDV) {
		super();
		this.idPDV = idPDV;
		this.nazivPDV = nazivPDV;
		this.grupeRoba = grupeRoba;
		this.stopePDV = stopePDV;
	}

	public PDV() {
		super();
	}
	
	
}
