package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Evento;

public interface ServicioEvento {
	Evento consultarEvento (Long id);
	void guardar (Evento evento);
	void actualizar (Evento evento);
	List<Evento> listarEventos();
	List<Evento> listarEventosPorNombre(String nombreDado);
	List<Evento> listarEventosModificables();
	List<Evento> listarEventosFinalizables();
}