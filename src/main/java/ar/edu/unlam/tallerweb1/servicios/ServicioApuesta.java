package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Apuesta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioApuesta { 
	 void guardar(Apuesta apuesta);
	 List<Apuesta> buscarPorApuesta (Usuario apostador);
}
