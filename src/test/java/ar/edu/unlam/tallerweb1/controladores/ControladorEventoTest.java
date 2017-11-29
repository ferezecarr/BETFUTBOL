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
import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuota;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;

public class ControladorEventoTest {	
	@Mock private HttpSession session;
	@Mock private HttpServletRequest request;
	@Mock private ServicioLogin servicioLogin;
	@Mock private ServicioPartido servicioPartido;
	@Mock private ServicioEvento servicioEvento;
	@Mock private ServicioCuota servicioCuota;
	@Mock private Usuario usuario;
	@Mock private List<Evento> listaEventos;
	@Mock private List<Partido> listaPartidos;
	@InjectMocks private ControladorABMEvento controller;
	
	@Before
	public void inyeccionDeMocksInicializadaAntesDeCadaTest() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testQueDetectaUnAccesoInvalido(){
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("AdminId")).thenReturn(null);
		
		ModelAndView modelo = controller.mostrarEventos(request);
		
		assertThat(modelo.getViewName()).isEqualTo("redirect:/");
		assertThat(modelo.getModel()).isEmpty();
	}
	
	@Test
	public void testQueDetectaUnAccesoValido(){
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("AdminId")).thenReturn(777L);
		when(servicioLogin.buscarPorId((Long) request.getSession().getAttribute("AdminId")))
			.thenReturn(usuario);
		when(usuario.getNombreYApellido()).thenReturn("Juan Pez");
		when(servicioPartido.listarTodosLosPartidos()).thenReturn(listaPartidos);
		when(servicioEvento.listarEventos()).thenReturn(listaEventos);
		when(servicioEvento.listarEventosFinalizables()).thenReturn(listaEventos);
		
		ModelAndView modelo = controller.mostrarEventos(request);
		
		assertThat(modelo.getViewName()).isEqualTo("ABM-Evento");
		assertThat(modelo.getModel()).isNotEmpty();
		assertThat(modelo.getModel().get("usuario")).isEqualTo(usuario);
		assertThat(modelo.getModel().get("nombre")).isEqualTo("Juan Pez");
		assertThat(modelo.getModel().get("partidos")).isEqualTo(listaPartidos);
		assertThat(modelo.getModel().get("eventos")).isEqualTo(listaEventos);
		assertThat(modelo.getModel().get("eventosFinalizables")).isEqualTo(listaEventos);
	}
}