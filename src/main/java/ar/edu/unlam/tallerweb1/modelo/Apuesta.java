/*Esta clase modela una apuesta. Si bien tiene relaciones N-1, actua como la tabla 
 * surgida de una relacion N-N
 * Se crea para modelar la siguientes relacion...  
 * 		Usuario(N) 	-(Apuesta)- (N)Evento
 * */
package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Apuesta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "evento_id")	
    private Evento evento;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "apostador_id")	
	private Usuario apostador;
    
	private Double cantidadApostada = 0.00d;
	private Double cuotaValor = 0.00d;
	private String cuotaNombre;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Evento getEvento() {
		return evento;
	}
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public Usuario getApostador() {
		return apostador;
	}
	
	public void setApostador(Usuario apostador) {
		this.apostador = apostador;
	}
	
	public Double getCantidadApostada() {
		return cantidadApostada;
	}
	
	public void setCantidadApostada(Double cantidadApostada) {
		this.cantidadApostada = cantidadApostada;
	}
	
	public Double getCuotaValor() {
		return cuotaValor;
	}
	
	public void setCuotaValor(Double cuotaValor) {
		this.cuotaValor = cuotaValor;
	}

	public String getCuotaNombre() {
		return cuotaNombre;
	}

	public void setCuotaNombre(String cuotaNombre) {
		this.cuotaNombre = cuotaNombre;
	}
}