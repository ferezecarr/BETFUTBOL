package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EquipoDao;
import ar.edu.unlam.tallerweb1.modelo.Equipo;


@Service("servicioEquipo")
//Solo necesita leer , no escribir , y no tiene prioridad de generacion
@Transactional(readOnly = true , propagation = Propagation.SUPPORTS)
public class ServicioEquipoImpl implements ServicioEquipo{

	
	@Inject
	private EquipoDao equipoDao;
	
	@Override
	public List<Equipo> listarTodosLosEquipos() {
		return equipoDao.findAll();
	}

	@Transactional(readOnly = false , propagation = Propagation.REQUIRED , rollbackFor = {Exception.class})
	@Override
	public void guardarEquipo(Equipo equipo) {
		equipoDao.save(equipo);
		
	}

	@Override
	public Equipo buscarPorId(Long id) {
		return equipoDao.findById(id);
	}

	@Override
	public Equipo consultarEquipo(Equipo equipo) {
		return null;
	}

	@Transactional(readOnly = false , propagation = Propagation.REQUIRED , rollbackFor = {Exception.class})	
	@Override
	public void eliminarEquipo(Equipo equipo) {
		equipoDao.delete(equipo);	
	}

	@Transactional(readOnly = false , propagation = Propagation.REQUIRED , rollbackFor = {Exception.class})
	@Override
	public void actualizarEquipo(Equipo equipo) {
		equipoDao.update(equipo);
		
	}

}
