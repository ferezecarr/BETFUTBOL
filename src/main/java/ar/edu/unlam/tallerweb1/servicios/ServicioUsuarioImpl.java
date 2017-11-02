package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ServicioUsuarioImpl implements ServicioUsuario {
	@Inject
	private UsuarioDao usuarioServicioDao;		
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public Usuario traerUsuarioDeId1()
	{
		Long numero = 1L;
		Usuario usuario = usuarioServicioDao.findById(numero);
		return usuario;
	}
}
