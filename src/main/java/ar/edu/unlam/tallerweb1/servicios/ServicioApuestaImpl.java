package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.ApuestaDao;
import ar.edu.unlam.tallerweb1.dao.EventoDao;
import ar.edu.unlam.tallerweb1.modelo.Apuesta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ServicioApuestaImpl implements ServicioApuesta {
	@Inject
	private ApuestaDao apuestaServicioDao;
		
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public Usuario traerUsuarioDeId1(Long i)
	{
		Long numero = i;
		Usuario usuario = apuestaServicioDao.findById(numero);
		return usuario;
	}

	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void guardar(Apuesta apuesta)
	{
	
		apuestaServicioDao.save(apuesta);
		
	}
}
