package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Evento;

public interface ServicioEvento {
	Evento consultarEvento (Evento evento);
	void guardar (Evento evento);
	List<Evento> listarEventos();

}
