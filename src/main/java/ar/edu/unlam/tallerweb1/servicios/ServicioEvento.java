package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Partido;

public interface ServicioEvento {
	Evento consultarEvento (Long id);
	void guardar (Evento evento);
	void actualizar (Evento evento);
	List<Evento> listarEventos();
	List<Evento> listarEventosPorNombre(String nombreDado);
	List<Evento> listarEventosModificables();		//Para cambiar cuotas
	List<Evento> listarEventosFinalizables();		//Para finalizar eventos
	List<Evento> listarEventosFinalizados();		//Para ver los eventos ya finalizados
	void actualizarDescripcionesDeEventos(Partido partido);
}