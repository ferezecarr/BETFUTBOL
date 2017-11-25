package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service("usuarioDao")
public class UsuarioDaoImpl implements UsuarioDao {

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public Usuario consultarUsuario(Usuario usuario) {

		
		return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword()))
				.uniqueResult();
	}
	@Override
	public Usuario consultarUsuarioPorMail(Usuario usuario) {

		
		return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.uniqueResult();
	}

	@Override
	public void save(Usuario usuario) {
		usuario.setRol("USER");	//Intente usar @PrePersist pero nunca llamaba al metodo
		sessionFactory.getCurrentSession().save(usuario);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) sessionFactory.getCurrentSession().createCriteria(Usuario.class).list();
	}

	@Override
	public Usuario findById(Long id) {
		return sessionFactory.getCurrentSession().get(Usuario.class, id);
	}
	@Override
	public void update(Usuario usuario) {
		usuario.setRol("USER");
		sessionFactory.getCurrentSession().update(usuario);
		
	}



}
