package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.dao.EventoDao;
import ar.edu.unlam.tallerweb1.modelo.Evento;

//Para que Spring entienda que se está trabajando con servicio, tanto al serviceImpl como al DAOImpl hay que indicarle que 
//que son servicios, en caso contrario, las implementaciones de las interfaces son tomadas por JAVA como una clase
//normal, por lo que no va a funciona, por lo tanto no va a traer datos y va a dar nullPointerException
//Además, siempre que se vaya a invocar un servicio, hay que injectarlo con @Inject, sea donde sea que se lo invoque.
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ServicioEventoImpl implements ServicioEvento{

	@Inject
	private EventoDao eventoServicioDao;
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})	
	public Evento consultarEvento(Long id) {
		return eventoServicioDao.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void guardar(Evento evento) {
		eventoServicioDao.save(evento);
		
	}

	@Override
	public List<Evento> listarEventos() {
		List<Evento> evento = eventoServicioDao.findAll();
		return evento;
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public List<Evento> listarEventosPorNombre(String nombreDado){
		return eventoServicioDao.findByNombre(nombreDado);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void actualizar(Evento evento) {
		eventoServicioDao.update(evento);		
	}
}