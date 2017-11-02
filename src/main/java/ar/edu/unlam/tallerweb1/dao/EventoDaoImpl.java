package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.Evento;


@Service("EventoDao")
@Transactional
@SuppressWarnings("unchecked")
public class EventoDaoImpl implements EventoDao{
	
	@Inject
	private SessionFactory sessionFactory;	

	@Override
	public List<Evento> findAll() {
		List<Evento> evento =  sessionFactory.getCurrentSession().createCriteria(Evento.class).list();
		return evento;
	}

	@Override
	public List<Evento> findByNombre(String nombreDado){
		List<Evento> evento = sessionFactory.getCurrentSession().createCriteria(Evento.class)
				.add(Restrictions.eq("nombre", nombreDado))
				.list();
		return evento;
	}

	@Override
	public Evento findById(Long id) {
		Evento evento = (Evento)sessionFactory.getCurrentSession().createCriteria(Evento.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		return evento;
	}



	@Override
	public void save(Evento evento) {
		sessionFactory.getCurrentSession().save(evento);
		
	}



	@Override
	public void update(Evento evento) {
		sessionFactory.getCurrentSession().update(evento);
	}

}
