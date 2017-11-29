package ar.edu.unlam.tallerweb1.dao;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Partido;

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
	public Equipo findByMatch(Equipo equipo) {
		return (Equipo) sessionFactory.getCurrentSession().createCriteria(Equipo.class)
				.add(Restrictions.eq("nombre", equipo.getNombre()))
				.uniqueResult();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> findTeamsWithoutMatches(List<Equipo> equipos){
		//Declaro un Set para evitar repetidos y traigo la lista de partidos
		List<Equipo> equiposSinPartidos = new LinkedList<Equipo>();
		equiposSinPartidos.addAll(equipos);
		Set<Equipo> equiposConPartidos = new HashSet<Equipo>();
		List<Partido> partidos = sessionFactory.getCurrentSession().createCriteria(Partido.class)
				.list();
		
		//Agrego los equipos a la coleccion
		for (Partido partido : partidos) {
			equiposConPartidos.add(partido.getLocal());
			equiposConPartidos.add(partido.getVisitante());
		}
		
		//Quito los equipos que juegan algun partido y devuelvo la lista	
		equiposSinPartidos.removeAll(equiposConPartidos);
	
		return equiposSinPartidos;
	}	
}