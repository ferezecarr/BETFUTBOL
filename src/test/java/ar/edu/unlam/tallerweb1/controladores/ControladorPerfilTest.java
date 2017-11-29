package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

public class ControladorPerfilTest {	
	@Mock private HttpSession session;
	@Mock private HttpServletRequest request;
	@Mock private ServicioLogin servicioLogin;
	@Mock private Usuario usuario;
	@InjectMocks private ControladorPerfil controller;
	
	@Before
	public void inyeccionDeMocksInicializadaAntesDeCadaTest() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testQueDetectaUnAccesoInvalido(){
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userId")).thenReturn(null);
		
		ModelAndView modelo = controller.mostrarPerfil(request);
		
		assertThat(modelo.getViewName()).isEqualTo("redirect:/");
		assertThat(modelo.getModel()).isEmpty();
		
		verify(servicioLogin, never()).buscarPorId((Long) request.getSession().getAttribute("userId"));
		verify(usuario, never()).getNombreYApellido();
		verify(usuario, never()).getEmail();
		verify(usuario, never()).getPassword();
	}
	
	@Test
	public void testQueDetectaUnAccesoValido(){
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userId")).thenReturn(30L);
		when(servicioLogin.buscarPorId((Long) request.getSession().getAttribute("userId")))
				.thenReturn(usuario);
		when(usuario.getNombreYApellido()).thenReturn("No te voy a decir mi nombre");
		when(usuario.getEmail()).thenReturn("mejorBestH4x0R@securityteam.bleh");
		when(usuario.getPassword()).thenReturn("qwerty1234");
		
		ModelAndView modelo = controller.mostrarPerfil(request);
		
		assertThat(modelo.getViewName()).isEqualTo("perfil");
		assertThat(modelo.getModel()).isNotEmpty();
		assertThat(modelo.getModel().get("usuario")).isEqualTo(usuario);
		assertThat(modelo.getModel().get("nombre")).isEqualTo("No te voy a decir mi nombre");
		assertThat(modelo.getModel().get("email")).isEqualTo("mejorBestH4x0R@securityteam.bleh");
		assertThat(modelo.getModel().get("password")).isEqualTo("qwerty1234");
		
		verify(servicioLogin, times(1)).buscarPorId((Long) request.getSession().getAttribute("userId"));
		verify(usuario, times(1)).getNombreYApellido();
		verify(usuario, times(1)).getEmail();
		verify(usuario, times(1)).getPassword();		
	}
	
	@Test
	public void testQueFallaLaActualizacion(){
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userId")).thenReturn(-1L);
		when(servicioLogin.buscarPorId((Long) request.getSession().getAttribute("userId")))
			.thenReturn(usuario);
		when(usuario.getId()).thenReturn(null);
		when(usuario.getNombreYApellido()).thenReturn("Macricio Mauri");
		
		ModelAndView modelo = controller.actualizarPerfil(usuario, request);
		
		assertThat(modelo.getModel().get("aviso")).isEqualTo("No se pudo actualizar");
		assertThat(modelo.getModel().get("usuario")).isEqualTo(usuario);
		assertThat(modelo.getModel().get("nombre")).isEqualTo("Macricio Mauri");
		
		verify(servicioLogin, times(2)).buscarPorId(any(Long.class));
		verify(usuario, times(1)).getNombreYApellido();
		verify(servicioLogin, never()).actualizar(any(Usuario.class));
	}
	
	@Test
	public void testQueActualizaBienElPerfil(){
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userId")).thenReturn(8L);
		when(servicioLogin.buscarPorId((Long) request.getSession().getAttribute("userId")))
			.thenReturn(usuario);
		when(usuario.getId()).thenReturn(8L);
		when(usuario.getNombreYApellido()).thenReturn("Macricio Mauri");
		
		ModelAndView modelo = controller.actualizarPerfil(usuario, request);
		
		assertThat(modelo.getModel().get("aviso")).isEqualTo("Se actualizo correctamente");
		assertThat(modelo.getModel().get("usuario")).isEqualTo(usuario);
		assertThat(modelo.getModel().get("nombre")).isEqualTo("Macricio Mauri");
		
		verify(servicioLogin, times(2)).buscarPorId(any(Long.class));
		verify(usuario, times(1)).getNombreYApellido();
		verify(servicioLogin, times(1)).actualizar(any(Usuario.class));		
	}
}