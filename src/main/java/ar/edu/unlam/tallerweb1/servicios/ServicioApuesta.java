package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Apuesta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioApuesta {

	 Usuario traerUsuarioDeId1(Long i);
	 
	 void guardar(Apuesta apuesta);
}
