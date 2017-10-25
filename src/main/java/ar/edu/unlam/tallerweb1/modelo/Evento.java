/*Esta clase modela un evento por el que se puede realizar una apuesta.
 * (Ganador, Un jugador que hace un gol, un expulsado, etc...)
 * Existen las siguientes relaciones...
 * 		Partido(1) 	-(Corresponde a)- (N)Evento
 * 		Evento(1)	-(Tiene)-		  (N)Cuota 	
 * */
package ar.edu.unlam.tallerweb1.modelo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_partido")	
	private Partido partido;
	
	@OneToMany(mappedBy = "evento")
	private Set<Apuesta> apuestas = new HashSet<Apuesta>();	
	
	@OneToMany(mappedBy = "evento")
	private Set<Cuota> cuotas = new HashSet<Cuota>();
	
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Set<Apuesta> getApuestas() {
		return apuestas;
	}

	public void setApuestas(Set<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}

	public Set<Cuota> getCuotas() {
		return cuotas;
	}

	public void setCuotas(Set<Cuota> cuotas) {
		this.cuotas = cuotas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
}