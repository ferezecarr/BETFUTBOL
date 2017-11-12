package ar.edu.unlam.tallerweb1.modelo;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioTest extends SpringTest {
	
	
	
	private Usuario usuario1 , usuario2;
	private List<Usuario> listaDeUsuarios;
	private Session sesion;
	
	@Before
	public void inicializacion() {
		usuario1 = new Usuario();
		usuario2 = new Usuario();
		sesion = getSession();
		listaDeUsuarios = new ArrayList<Usuario>();
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQueAlPasarUsuarioYPasswordInvalidoDeberiaLlevarAlIndex() {
		Usuario primerUsuario = mock(Usuario.class);
		ServicioLogin servicioLogin = mock(ServicioLogin.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		when(primerUsuario.getEmail()).thenReturn("usuario@mock.com");
		when(primerUsuario.getPassword()).thenReturn("mock");
		when(servicioLogin.consultarUsuario(any(Usuario.class))).thenReturn(primerUsuario);
		when(request.getSession().getAttribute("ROL"));
		
		ControladorLogin controladorLogin = new ControladorLogin();
		
		assertThat(controladorLogin.irAHome().equals(primerUsuario)).isNotNull();
		
		verify(servicioLogin.consultarUsuario(primerUsuario) , times(1));
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	@Transactional
	@Rollback(true)
	public void testQueAlCrearUnNuevoUsuarioYaExistaYDevuelvaUnError() throws Exception {
		usuario1.setNombreYApellido("Juan Perez");
		usuario1.setEmail("juanp@hotmail.com");
		usuario1.setPassword("123456");
		
		usuario2.setNombreYApellido("Raul Gomez");
		usuario2.setEmail("juanp@hotmail.com");
		usuario2.setPassword("123456");
		
		
		if(this.usuario1.getEmail().equals(this.usuario2.getEmail()) || this.usuario1.getPassword().equals(this.usuario2.getPassword())) {
			throw new Exception("Ya existe un usuario con ese email , por favor elija otro");
		} else {
			System.out.println("Usuario valido");
		}
		
		getSession().save(usuario1);
		getSession().save(usuario2);
		
		
		listaDeUsuarios = sesion.createCriteria(Usuario.class)
						  .add(Restrictions.and(Restrictions.eq("email", "email") , Restrictions.eq("password", "password"))).list();
		
		assertThat(listaDeUsuarios.get(0).getEmail()).isEqualTo(usuario1.getEmail()).isNotNull();
		assertThat(listaDeUsuarios).hasSize(1);
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	@Transactional
	@Rollback(true)
	public void testQueAlIngresarUnUsuarioNoEscribaSuPasswordYDevuelvaUnError() throws Exception {
		usuario1.setNombreYApellido("Juan Perez");
		usuario1.setEmail("juanp@hotmail.com");
		usuario1.setPassword(null);
		
		if(this.usuario1.getPassword() == null) {
			throw new Exception("Por favor escriba una contraseña");
		} else {
			this.usuario1.getPassword();
		}
		
		getSession().save(usuario1);
		
		listaDeUsuarios = sesion.createCriteria(Usuario.class)
							.add(Restrictions.isNull("password")).list();
		
		assertThat(listaDeUsuarios.get(0).getPassword()).isNull();
		assertThat(listaDeUsuarios).hasSize(1);
			
	}
	
	
	@Test(expected = Exception.class)
	@Transactional
	@Rollback(true)
	public void testQueAlIngresarUnUsuarioEscribaSuPasswordDeFormaIncorrectaYDevuelvaUnError() throws Exception{
		usuario1.setNombreYApellido("Juan Perez");
		usuario1.setEmail("juanp@hotmail.com");
		usuario1.setPassword("123456");
		
		if(this.usuario1.getPassword().equals(this.usuario1.getPassword())) {
			throw new Exception("password incorrecta");
		} else {
			this.usuario1.getPassword();
		}
		
		getSession().save(usuario1);
		
		
	}
	
	

}
