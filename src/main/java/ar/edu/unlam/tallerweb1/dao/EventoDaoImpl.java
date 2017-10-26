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
public class EventoDaoImpl implements EventoDao{
	
	@Inject
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Evento> findAll() {
		List<Evento> evento =  sessionFactory.getCurrentSession().createCriteria(Evento.class).list();
		return evento;
	}

	@Override
	public void guardar(Evento evento) {
	}

	@Override
	public Void actualizar(Evento evento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evento findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
