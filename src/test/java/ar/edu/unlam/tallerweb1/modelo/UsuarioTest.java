package ar.edu.unlam.tallerweb1.modelo;

import static org.junit.Assert.*;


import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import static org.assertj.core.api.Assertions.*;

public class UsuarioTest extends SpringTest {

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	@Transactional
	@Rollback(true)
	public void testQueAlCrearUnNuevoUsuarioYaExistaYDevuelvaUnError() throws Exception {
		Usuario usuario1 = new Usuario();
		usuario1.setNombreYApellido("Juan Perez");
		usuario1.setEmail("juanp@hotmail.com");
		usuario1.setPassword("123456");
		
		Usuario usuario2 = new Usuario();
		usuario2.setNombreYApellido("Raul Gomez");
		usuario2.setEmail("juanp@hotmail.com");
		usuario2.setPassword("123456");
		
		
		if(usuario1.getEmail().equals(usuario2)) {
			throw new Exception("Ya existe un usuario con ese email , por favor elija otro");
		} else {
			System.out.println("Usuario valido");
		}
		
		getSession().save(usuario1);
		getSession().save(usuario2);
		
		Session sesion = getSession();
		
		List<Usuario> listaDeUsuarios;
		
		listaDeUsuarios = sesion.createCriteria(Usuario.class)
						  .add(Restrictions.and(Restrictions.eq("email", "email") , Restrictions.eq("password", "password"))).list();
		
		assertThat(listaDeUsuarios.get(0).getEmail()).isEqualTo(usuario1.getEmail()).isNotNull();
		assertThat(listaDeUsuarios).hasSize(1);
	}

}
