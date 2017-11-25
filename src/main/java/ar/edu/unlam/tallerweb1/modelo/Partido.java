/*HashCode()/Equals() implementando para distinguir entre id, local y visitante a la vez. 
 * Hay una doble relacion entre Equipo y Partido...
 * 		Equipo(1) 	-(Hace de local en)- 		(N)Partido
 * 		Equipo(1) 	-(Hace de visitante en)- 	(N)Partido
 * Tambien tiene una relacion 1-N con Evento (Modelada en esa clase)
 * */
package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Partido {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_equipo_local")
	private Equipo local;
	
	@ManyToOne
	@JoinColumn(name = "id_equipo_visitante")
	private Equipo visitante;	
		
	private Integer golesLocal = 0;	
	private Integer golesVisitante = 0;
	
	@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm")
	private Date fecha;
	
	/*Esto se setea automaticamente a TRUE cuando pasan dos horas de la fecha del 
	 * partido. En el ABM se podra terminar abruptamente. La utilidad es que al 
	 * finalizar eventos, vendran los partidos 'terminados' para colocar y setear el 
	 * resultado final*/
	private Boolean isTerminado = false;
	
	/*Lo veo util para saber si los valores de 'golesLocal'/'golesVisitante' son los 
	 * otorgados cuando se escribe el resultado. Sin esto, se asumiria que el resultado 
	 * de todos los partidos es 0-0. En el AMB de partidos, el admin pondra el resultado  
	 * y al pulsar confirmar, se seteen 'golesLocal', 'golesVisitante' y este 
	 * booleano en TRUE. Entonces despues se puede traer una lista ya filtrada de partidos 
	 * con el resultado definido usando este atributo*/
	private Boolean isResultadoFinal = false; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Equipo getLocal() {
		return local;
	}

	public void setLocal(Equipo local) {
		this.local = local;
	}

	public Equipo getVisitante() {
		return visitante;
	}

	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}
	
	public Boolean getIsTerminado() {
		return isTerminado;
	}

	public void setIsTerminado(Boolean isTerminado) {
		this.isTerminado = isTerminado;
	}

	public String mostrarResultado(){
		return local.getNombre() + " " + golesLocal + " - " + golesVisitante + 
				" " + visitante.getNombre();
	}

	public Boolean getIsResultadoFinal() {
		return isResultadoFinal;
	}

	public void setIsResultadoFinal(Boolean isResultadoFinal) {
		this.isResultadoFinal = isResultadoFinal;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (visitante == null) {
			if (other.visitante != null)
				return false;
		} else if (!visitante.equals(other.visitante))
			return false;
		return true;
	}	
}