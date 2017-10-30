package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Equipo;

public interface EquipoDao {
	
	List<Equipo> findAll();
	void save(Equipo equipo);
	Equipo findById(Long id);
	Equipo findByMatch(Equipo equipo);

}
