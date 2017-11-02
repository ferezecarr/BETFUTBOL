package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.dao.CuotaDao;
import ar.edu.unlam.tallerweb1.modelo.Cuota;
import ar.edu.unlam.tallerweb1.modelo.Evento;


@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ServicioCuotaImpl implements ServicioCuota {
	@Inject
	private CuotaDao cuotaServicioDao;		
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Cuota> recalcularCuotas(List<Cuota> cuotas, String cuotaNombreVotada)
	{
	
		return cuotaServicioDao.adjust(cuotas, cuotaNombreVotada);
		
	}

	@Override
	@Transactional(readOnly = true , propagation = Propagation.REQUIRED , rollbackFor = {Exception.class})
	public List<Cuota> traerCuotasSegunEvento(Evento evento) {
		return cuotaServicioDao.findByEvent(evento);
	}
}
