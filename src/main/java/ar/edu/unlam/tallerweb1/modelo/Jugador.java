/*Tiene una relacion N-1 con Equipo (1 jugador pertenece a un equipo y a un equipo, 
 * pertenecen N jugadores)*/
package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Jugador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name = "id_equipo")
	private Equipo equipo;
	
	private String nombre;
	private String posicion;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Equipo getEquipo() {
		return equipo;
	}
	
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPosicion() {
		return posicion;
	}
	
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}	
}