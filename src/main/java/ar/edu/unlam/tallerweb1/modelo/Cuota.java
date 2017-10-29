/*Esta clase modela las cuotas que forman parte de un evento por el cual se puede realizar 
 * una apuesta. En nombre irian cosas como "Gana local", "Gana visitante", el nombre de 
 * un jugador por el que se apueste (hace un gol/es expulsado), etc...*/
package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cuota {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "evento_id")
	private Evento evento;
	
	@Column(columnDefinition = "DECIMAL(5, 2) DEFAULT 1.00")
	private Double valor = 1.00d;
	
	@Column(columnDefinition = "BIGINT DEFAULT 0")
	private Long cantidadVotos = 0L;
	
	private String nombre;
	
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
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getCantidadVotos() {
		return cantidadVotos;
	}

	public void setCantidadVotos(Long cantidadVotos) {
		this.cantidadVotos = cantidadVotos;
	}	
}