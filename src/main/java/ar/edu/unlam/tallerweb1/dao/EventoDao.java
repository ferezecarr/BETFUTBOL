package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Evento;

public interface EventoDao {
	List<Evento> findAll();
	List<Evento> findByNombre(String nombreDado);
	List<Evento> findModificables();
	List<Evento> findFinalizables();
	List<Evento> findFinalizados();
	void save(Evento evento);
	void update(Evento evento);
	Evento findById(Long id);
}