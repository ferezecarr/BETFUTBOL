package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Partido;

public interface PartidoDao {

	List<Partido> findAll();
	void save(Partido partido);
	void update(Partido partido);
	Partido findById(Long id);
	Partido findByMatch(Partido partido);
	List<Partido> findUnfinishedMatch();
}
