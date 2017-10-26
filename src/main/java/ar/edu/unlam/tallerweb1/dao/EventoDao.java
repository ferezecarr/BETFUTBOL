package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Evento;

public interface EventoDao {
	List<Evento> findAll();
	void guardar(Evento evento);
	Void actualizar(Evento evento);
	Evento findById(Long id);
}
