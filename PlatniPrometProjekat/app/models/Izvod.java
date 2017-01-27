package models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

public class Izvod extends Model{

	@Column(nullable=false,unique=true, length=18) 
	public String brojRacuna;
	
	@Column(nullable=false) 
	public Date datumNaloga;
	
	@Column(nullable=false, length=2) 
	public int brojPreseka;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float prethodnoStanje;
	
	@Column(nullable=false, length=6) 
	public int brojPromenaUKorist;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float ukupnoUKorist;
	
	@Column(nullable=false, length=6) 
	public int brojPromenaNaTeret;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float ukupnoNaTeret;
	
	@Column(nullable=false, precision=15, scale=2) 
	public float novoStanje;
	
	@OneToMany(mappedBy="Izvod")
	public List<StavkaIzvoda>stavkeIzvoda;

	public Izvod(String brojRacuna, Date datumNaloga, int brojPreseka, float prethodnoStanje, int brojPromenaUKorist,
			float ukupnoUKorist, int brojPromenaNaTeret, float ukupnoNaTeret, float novoStanje,
			List<StavkaIzvoda> stavkeIzvoda) {
		super();
		this.brojRacuna = brojRacuna;
		this.datumNaloga = datumNaloga;
		this.brojPreseka = brojPreseka;
		this.prethodnoStanje = prethodnoStanje;
		this.brojPromenaUKorist = brojPromenaUKorist;
		this.ukupnoUKorist = ukupnoUKorist;
		this.brojPromenaNaTeret = brojPromenaNaTeret;
		this.ukupnoNaTeret = ukupnoNaTeret;
		this.novoStanje = novoStanje;
		this.stavkeIzvoda = stavkeIzvoda;
	}
	
	
}
