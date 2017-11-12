package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.RankingDTO;


public interface RankingDao {
	List<RankingDTO> findAll();
	List<RankingDTO> findByEventName(String filtro);	
}