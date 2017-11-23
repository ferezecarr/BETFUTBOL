package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Equipo;

@Service("EquipoDao")
@Transactional
public class EquipoDaoImpl implements EquipoDao{
	
	@Inject
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> findAll() {
		
		List<Equipo> equipo =  sessionFactory.getCurrentSession().createCriteria(Equipo.class).list();
		return equipo;
	}

	@Override
	public void save(Equipo equipo) {
		sessionFactory.getCurrentSession().save(equipo);		
	}

	@Override
	public Equipo findById(Long id) {
		
		return (Equipo) sessionFactory.getCurrentSession().createCriteria(Equipo.class)
				.add(Restrictions.eq("id",id)).uniqueResult();
		
	
	}

	@Override
	public Equipo findByMatch(Equipo equipo) {
		return (Equipo) sessionFactory.getCurrentSession().createCriteria(Equipo.class)
				.add(Restrictions.eq("nombre", equipo.getNombre()))
				.uniqueResult();
	}

	@Override
	public void delete(Equipo equipo) {
		Equipo e = (Equipo)sessionFactory.getCurrentSession().createCriteria(Equipo.class)
				.add(Restrictions.eq("nombre", equipo.getNombre()))
				.uniqueResult();
		sessionFactory.getCurrentSession().delete(e);		
	}

	@Override
	public void update(Equipo equipo) {
		
		
		sessionFactory.getCurrentSession().update(equipo);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> add(Equipo equipo) {
		List<Equipo> equipos = sessionFactory.getCurrentSession().createCriteria(Equipo.class)
				.add(Restrictions.eq("id", equipo.getId()))
				.add(Restrictions.eq("nombre", equipo.getNombre()))
				.list();
		return equipos;
	}


}
