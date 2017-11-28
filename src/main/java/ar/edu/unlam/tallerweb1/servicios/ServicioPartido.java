package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Partido;

public interface ServicioPartido {

	List<Partido> listarTodosLosPartidos();
	void guardarPartido(Partido partido);
	void actualizarPartido(Partido partido);
	void eliminarPartido(Partido partido);
	Partido buscarPorId(Long id);
	Partido consultarPartido(Partido partido);
	List<Partido> listarTodosLosPartidosSinTerminar();
}
