package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Equipo findByMatch(Equipo equipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Equipo equipo) {
		sessionFactory.getCurrentSession().delete(equipo);		
	}

	@Override
	public void update(Equipo equipo) {
		sessionFactory.getCurrentSession().update(equipo);
		
	}

}
