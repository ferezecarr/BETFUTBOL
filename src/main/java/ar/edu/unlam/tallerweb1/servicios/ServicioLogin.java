package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import java.util.*;

public interface ServicioLogin {

	Usuario consultarUsuario(Usuario usuario);
	void guardar(Usuario usuario);
	List<Usuario> listarTodos();
	Usuario buscarPorId(Long id);
}
