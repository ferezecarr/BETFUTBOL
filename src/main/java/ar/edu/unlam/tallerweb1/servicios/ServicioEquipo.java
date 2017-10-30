package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Equipo;


public interface ServicioEquipo {
	
	List<Equipo> listarTodosLosEquipos();
	void guardarEquipo(Equipo equipo);
	Equipo buscarPorId(Long id);
	Equipo consultarEquipo(Equipo equipo);

}
