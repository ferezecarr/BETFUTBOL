package ar.edu.unlam.tallerweb1.controladores;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import static org.assertj.core.api.Assertions.assertThat;

public class ControladorLoginTest {
	
	@Mock
	private ServicioLogin servicioLogin;
	
	@Mock
	private Usuario usuario;
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;

	@InjectMocks
	private ControladorLogin controladorLogin;
	
	@Before
	public void inyeccionDeMocksInicializada() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testQueVerificaQueElLoginSeaValido() {
		when(request.getSession()).thenReturn(session);
		when(servicioLogin.consultarUsuario(any(Usuario.class))).thenReturn(usuario);
		when(usuario.getEmail()).thenReturn("admin@betfutbol.com");
		when(usuario.getPassword()).thenReturn("admin");
		when(usuario.getRol()).thenReturn("ADMIN");
		
		ModelAndView modelo = controladorLogin.irALogin(usuario, request);

		assertThat(modelo.getViewName()).isEqualTo("redirect:/index");
		assertThat(modelo.getModel()).isEmpty();
		verify(session , times(1)).setAttribute("ROL", "ADMIN");
		
	}
	
	@Test
	public void testQueVerificaQueElLoginSeaInvalido() {
		when(request.getSession()).thenReturn(session);
		when(servicioLogin.consultarUsuario(any(Usuario.class))).thenReturn(null);
		
		ModelAndView modelo = controladorLogin.irALogin(usuario, request);
		
		assertThat(modelo.getViewName()).isEqualTo("login");
		assertThat(modelo.getModel().get("error")).isEqualTo("Usuario o clave incorrecta");
		verify(session , never()).setAttribute("ROL", "ADMIN");
	}

}
