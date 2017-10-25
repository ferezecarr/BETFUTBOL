package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Jugador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	private Date fechaIngreso;
	private Date fechaNacimiento;
	private Integer dorsal;
	private String posicion;
	
}
