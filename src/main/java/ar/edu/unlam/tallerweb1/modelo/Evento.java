/*Esta clase modela un evento por el que se puede realizar una apuesta.
 * (Ganador, Un jugador que hace un gol, un expulsado, etc...)
 * Existen las siguientes relaciones...
 * 		Partido(1) 	-(Corresponde a)- (N)Evento
 * 		Evento(1)	-(Tiene)-		  (N)Cuota 	
 * */
package ar.edu.unlam.tallerweb1.modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_partido")	
	private Partido partido;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "evento", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Cuota> cuotas = new LinkedList<Cuota>();
	
	private String nombre;			//Resultado, Resultado Especifico, Jugador hace gol, etc..
	private String descripcion;		//"Boca Vs River - Domingo - 18.00hs"
	
	/*Indica si el evento esta terminado. Esto se setea automaticamente por el evento 
	 * puesto en el import.sql. Util para filtrar, si se quiere traer eventos sin finalizar, 
	 * eventos finalizados...*/
	private Boolean isTerminado = false;
	
	/*En el ABM, el admin elige entre el listado de cuotas correspondientes al evento cual es 
	 * la que tiene premio. Al confirmar, se setea este atributo con el nombre de esa cuota*/
	private String cuotaGanadora;

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
	
	public void addCuota(Cuota cuota){
		cuotas.add(cuota);
	}

	public String getCuotaGanadora() {
		return cuotaGanadora;
	}

	public void setCuotaGanadora(String cuotaGanadora) {
		this.cuotaGanadora = cuotaGanadora;
	}
	
	/*INTENTE USAR @PrePersist, PERO NUNCA LLAMABA AL METODO (Aca vienen las negradas)*/
	public void generarDescripcionDinamica(){
		//Edito la descripcion usando el nombre de los equipos y la fecha de partido
		DateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm");
		String fechaSimple = dateFormat.format(this.getPartido().getFecha());
		String descripcionSimple = this.getDescripcion();
		
		if(this.getNombre().equals("Resultado") || 
				this.getNombre().equals("Cantidad de goles en un partido") ||
				this.getNombre().equals("Cantidad de goles par o impar")){
			String descripcion = "[" + this.getNombre() + "] (L) " + 
					this.getPartido().getLocal().getNombre() + " Vs " + 
					this.getPartido().getVisitante().getNombre() + " (V) |" + 
					descripcionSimple + "| - " + fechaSimple + "Hs";
			this.setDescripcion(descripcion);
		}else if(this.getNombre().equals("Cuantos goles hace un equipo")){
			String descripcion = "[" + this.getNombre() + "] " +
					"Goles de " + this.getPartido().getLocal().getNombre() +
					" ante " + this.getPartido().getVisitante() + "|" + 
					descripcionSimple + "| - " + fechaSimple + "Hs";
			this.setDescripcion(descripcion);
		}		
	}
	
	/*LO MISMO DEL METODO ANTERIOR*/
	public void generarNombresDeCuotasDinamicos(){
		//Si alguien tiene una mejor idea que avise. (Genero nombres de cuotas dinamicos)
		if(this.getNombre().equals("Resultado")){			
			String local = this.getPartido().getLocal().getNombre();
			String visitante = this.getPartido().getVisitante().getNombre();
			this.getCuotas().get(0).setNombre("Gana " + local);
			this.getCuotas().get(2).setNombre("Gana " + visitante);
		}
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}