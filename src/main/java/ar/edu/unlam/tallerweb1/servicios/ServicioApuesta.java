package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Apuesta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioApuesta { 
	 void guardar(Apuesta apuesta);
	 Apuesta buscarPorApuesta (Usuario apostador);
}
