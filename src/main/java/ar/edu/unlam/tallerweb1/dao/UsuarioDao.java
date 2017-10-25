package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import java.util.*;

public interface UsuarioDao {
	
	Usuario consultarUsuario (Usuario usuario);
	void save(Usuario usuario);
	List<Usuario> findAll();
	Usuario findById(Long id);
}
