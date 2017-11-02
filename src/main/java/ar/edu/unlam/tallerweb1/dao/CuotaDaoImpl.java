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
	public void adjust(List<Cuota> cuotas, String cuotaNombreVotada) {
		cuotas = CalculadorDeCuotas.calcular(cuotas, cuotaNombreVotada);		
		sessionFactory.getCurrentSession().save(cuotas);	
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Cuota> findByEvent(Evento evento) {
		return sessionFactory.getCurrentSession().createCriteria(Cuota.class)
				.add(Restrictions.eq("evento", evento))
				.list();
	}
}