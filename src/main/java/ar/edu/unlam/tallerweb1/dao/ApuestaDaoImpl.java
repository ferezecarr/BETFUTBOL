package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.Apuesta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ApuestaDaoImpl implements ApuestaDao{

	
	@Inject
	private SessionFactory sessionFactory;	
	
	
	@Override
	public Usuario findById(Long i)
	{
		Usuario user = (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class).add(Restrictions.eq("id", i)).uniqueResult();
		
		return user;
	}
	
	@Override 
	 public void save(Apuesta apuesta)
	 {	
		sessionFactory.getCurrentSession().save(apuesta);
	 }
	
	@Override
	@SuppressWarnings("unchecked")
	public List <Apuesta> findByApuesta(Usuario apostador){
		
		
		List<Apuesta> misApuestas = sessionFactory.getCurrentSession().createCriteria(Apuesta.class)
				.add(Restrictions.eq("apostador", apostador))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		
		return misApuestas;
		
	}
}