package ar.edu.unlam.tallerweb1.modelo;

import java.util.LinkedList;
import java.util.List;

public class NuevoEventoDTO {
	private Evento evento;
	private List<Cuota> cuotas = new LinkedList<Cuota>();
	
	public Evento getEvento() {
		return evento;
	}
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Cuota> getCuotas() {
		return cuotas;
	}

	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}
	
	public void addCuota(Cuota cuota){
		cuotas.add(cuota);
	}
}