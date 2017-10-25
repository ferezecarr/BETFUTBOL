package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	@Inject
	private UsuarioDao servicioLoginDao;

	@Override
	public Usuario consultarUsuario (Usuario usuario) {
		return servicioLoginDao.consultarUsuario(usuario);
	}

	@Override
	public void guardar(Usuario usuario) {
		servicioLoginDao.save(usuario);
		
	}

	@Override
	public List<Usuario> listarTodos() {
		return servicioLoginDao.findAll();
	}

	@Override
	public Usuario buscarPorId(Long id) {
		return servicioLoginDao.findById(id);
	}

}
