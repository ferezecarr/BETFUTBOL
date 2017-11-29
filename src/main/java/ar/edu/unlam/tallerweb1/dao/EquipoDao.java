package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Equipo;

public interface EquipoDao {	
	List<Equipo> findAll();
	List<Equipo> add(Equipo equipo);
	void save(Equipo equipo);
	void delete(Equipo equipo);
	void update(Equipo equipo);
	Equipo findById(Long id);
	Equipo findByMatch(Equipo equipo);
	List<Equipo> findTeamsWithoutMatches(List<Equipo> equipos);
}