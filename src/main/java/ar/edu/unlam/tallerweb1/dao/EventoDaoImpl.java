package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Partido;

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
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void save(Evento evento) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Void update(Evento evento) {
		// TODO Auto-generated method stub
		return null;
	}

}
