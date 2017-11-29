package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
		return (Partido) sessionFactory.getCurrentSession().createCriteria(Partido.class)
				.add(Restrictions.eq("id",id)).uniqueResult();
	}

	
	
	@Override
	public Partido findByMatch(Partido partido) {
		final Session session = sessionFactory.openSession();
		return (Partido) session.createCriteria(Partido.class)
				.add(Restrictions.eq("partido", partido.getId()));
				
	}

	@Override
	public void update(Partido partido) {
		sessionFactory.getCurrentSession().update(partido);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Partido> findUnfinishedMatch()
	{
		return (List<Partido>) sessionFactory.getCurrentSession().createCriteria(Partido.class).add(Restrictions.eq("isTerminado", false)).list();
	}
}
