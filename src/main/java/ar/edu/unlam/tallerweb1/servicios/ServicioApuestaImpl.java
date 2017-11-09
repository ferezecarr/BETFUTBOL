package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.dao.ApuestaDao;
import ar.edu.unlam.tallerweb1.modelo.Apuesta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ServicioApuestaImpl implements ServicioApuesta {
	
	@Inject
	private ApuestaDao apuestaServicioDao;		
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void guardar(Apuesta apuesta)
	{
	
		apuestaServicioDao.save(apuesta);
		
	}
	
	@Override
	public List<Apuesta> buscarPorApuesta (Usuario apostador){
		
		return apuestaServicioDao.findByApuesta(apostador);
	}
}
