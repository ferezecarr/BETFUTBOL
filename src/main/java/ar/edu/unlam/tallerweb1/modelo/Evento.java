/*Esta clase modela un evento por el que se puede realizar una apuesta.
 * (Ganador, Un jugador que hace un gol, un expulsado, etc...)
 * Existen las siguientes relaciones...
 * 		Partido(1) 	-(Corresponde a)- (N)Evento
 * 		Evento(1)	-(Tiene)-		  (N)Cuota 	
 * */
package ar.edu.unlam.tallerweb1.modelo;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
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
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
	private List<Apuesta> apuestas = new LinkedList<Apuesta>();	
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
	private List<Cuota> cuotas = new LinkedList<Cuota>();
	
	private String nombre;			//Resultado, Resultado Especifico, Jugador hace gol, etc..
	private String descripcion;		//"Boca Vs River - Domingo - 18.00hs"
	
	/*Indica si el evento esta terminado. Esto se setea automaticamente por el evento 
	 * puesto en el import.sql. Util para filtrar, si se quiere traer eventos sin finalizar, 
	 * eventos finalizados...*/
	private Boolean isTerminado = false;

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

	public List<Apuesta> getApuestas() {
		return apuestas;
	}

	public void setApuestas(List<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}

	public List<Cuota> getCuotas() {
		return cuotas;
	}

	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getIsTerminado() {
		return isTerminado;
	}

	public void setIsTerminado(Boolean isTerminado) {
		this.isTerminado = isTerminado;
	}
	
	public void addApuesta(Apuesta apuesta){
		apuestas.add(apuesta);
	}
	
	public void addCuota(Cuota cuota){
		cuotas.add(cuota);
	}
}