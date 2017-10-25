package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Partido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rival;
	private Date fecha;
	private Integer resultado;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRival() {
		return rival;
	}
	
	public void setRival(String rival) {
		this.rival = rival;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Integer getResultado() {
		return resultado;
	}
	
	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}
	
	
	
	
	
}
