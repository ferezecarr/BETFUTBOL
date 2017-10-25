package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Partido;

@Service("partidoDao")
@Transactional
public class PartidoDaoImpl implements PartidoDao {
	
	@Inject
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Partido> findAll() {
		return (List<Partido>) sessionFactory.getCurrentSession().createCriteria(Partido.class).list();
	}

	@Override
	public void save(Partido partido) {
		sessionFactory.getCurrentSession().save(partido);
		
	}

	@Override
	public Partido findById(Long id) {
		return sessionFactory.getCurrentSession().get(Partido.class, id);
	}

}
