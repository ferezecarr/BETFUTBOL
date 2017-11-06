package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.CalculadorDeCuotas;
import ar.edu.unlam.tallerweb1.modelo.Cuota;
import ar.edu.unlam.tallerweb1.modelo.Evento;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CuotaDaoImpl implements CuotaDao{

	
	@Inject
	private SessionFactory sessionFactory;

	@Override
	public List<Cuota> adjust(List<Cuota> cuotas, String cuotaNombreVotada) {
		return CalculadorDeCuotas.calcular(cuotas, cuotaNombreVotada);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Cuota> findByEvent(Evento evento) {
		return sessionFactory.getCurrentSession().createCriteria(Cuota.class)
				.add(Restrictions.eq("evento", evento))
				.list();
	}

	@Override
	public void addVote(Long eventoId, String cuotaVotada) {
		Cuota cuota = (Cuota)sessionFactory.getCurrentSession().createCriteria(Cuota.class)
				.createAlias("evento", "e")
				.add(Restrictions.eq("e.id", eventoId))
				.add(Restrictions.eq("nombre", cuotaVotada))
				.uniqueResult();
		cuota.setCantidadVotos(cuota.getCantidadVotos() + 1L);
		sessionFactory.getCurrentSession().update(cuota);		
	}
}