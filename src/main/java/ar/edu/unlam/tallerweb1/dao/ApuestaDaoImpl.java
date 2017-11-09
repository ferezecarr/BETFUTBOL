package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;
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
	 public void save(Apuesta apu)
	 {	
		sessionFactory.getCurrentSession().save(apu);
	 }
	
	@Override
	public List <Apuesta> findByApuesta(Usuario apostador){
		
		
		List<Apuesta> misApuestas = sessionFactory.getCurrentSession().createCriteria(Apuesta.class).add(Restrictions.eq("apostador", apostador)).list();
		
		return misApuestas;
		
	}
}