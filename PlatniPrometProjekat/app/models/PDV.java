package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class PDV extends Model{
	

	
	@Column(nullable=false, length=20) 
	public String nazivPDV;
	
	@OneToMany(mappedBy="PDV")
	public List<GrupaRobe>grupeRobe;
	
	@OneToMany(mappedBy="PDV")
	public List<StopaPDV>stopePDV;

	public PDV(String nazivPDV, List<GrupaRobe> grupeRobe, List<StopaPDV> stopePDV) {
		super();
		this.nazivPDV = nazivPDV;
		this.grupeRobe = grupeRobe;
		this.stopePDV = stopePDV;
	}

	public PDV() {
		super();
	}
	
	
}
