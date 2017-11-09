package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Apuesta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ApuestaDao {

	 Usuario findById(Long id);
	 void  save(Apuesta apu);
	 List<Apuesta> findByApuesta (Usuario apostador);	
}
