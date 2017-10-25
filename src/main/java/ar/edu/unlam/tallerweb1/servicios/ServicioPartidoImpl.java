package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PartidoDao;
import ar.edu.unlam.tallerweb1.modelo.Partido;

@Service("servicioPartido")
@Transactional(readOnly = true , propagation = Propagation.SUPPORTS)
public class ServicioPartidoImpl implements ServicioPartido {
	
	@Inject
	private PartidoDao partidoDao;

	@Transactional(readOnly = false , propagation = Propagation.REQUIRED , rollbackFor = {Exception.class})
	@Override
	public void guardarPartido(Partido partido) {
		partidoDao.save(partido);
		
	}

	@Override
	public List<Partido> listarTodosLosPartidos(Partido partido) {
		return partidoDao.findAll();
	}

	@Override
	public Partido buscarPorId(Long id) {
		return partidoDao.findById(id);
	}
	


}
