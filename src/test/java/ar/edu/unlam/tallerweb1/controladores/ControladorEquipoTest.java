package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorEquipoTest {

	@Mock
	ServicioEquipo servicioEquipo;
	
	@Mock
	ServicioLogin servicioLogin;
	
	@Mock
	List<Equipo> equipos;
	
	@Mock
	Equipo equipo;
	
	@Mock
	Usuario usuario;
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpSession session;
	
	@InjectMocks
	ControladorABMEquipos controlador;
	
	@Before
	public void iniciarInjecciones(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void deberiaDevolverALaVistaEnCasoDeQueLaSessionSeaNula() {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("AdminID")).thenReturn(null);
		
		ModelAndView modelo = controlador.mostrarEquipo(request);
		
		assertThat(modelo.getViewName()).isEqualTo("redirect:/");
		
		verify(session, times(1)).getAttribute("AdminId");
	
	}
	
	@Test
	public void siLaSessionNoEsNulaDeberiaLlevarALaVista() {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("AdminId")).thenReturn(1L);
		when(servicioEquipo.listarTodosLosEquipos()).thenReturn(equipos);
		when(servicioLogin.buscarPorId((Long)session.getAttribute("AdminId"))).thenReturn(usuario);
		when(usuario.getNombreYApellido()).thenReturn("Ariel Rivero");
		when(servicioEquipo.traerEquiposQueNoJueganPartidos(equipos)).thenReturn(equipos);
		
		ModelAndView modelo = controlador.mostrarEquipo(request);
		
		assertThat(modelo.getViewName()).isEqualTo("ABM-Equipo");
		assertThat(modelo.getModel()).isNotEmpty();
		assertThat(modelo.getModel().get("usuario")).isEqualTo(usuario);
		assertThat(modelo.getModel().get("nombre")).isEqualTo(usuario.getNombreYApellido());
		assertThat(modelo.getModel().get("equiposSinPartidos")).isEqualTo(equipos);
		
		verify(servicioEquipo, times(1)).listarTodosLosEquipos();
		verify(servicioLogin, times(1)).buscarPorId((Long) session.getAttribute("AdminId"));
		verify(servicioEquipo, times(1)).traerEquiposQueNoJueganPartidos(equipos);
		verify(request, times(2)).getSession();
	
	}
	
	@Test
	public void siElNombreDeEquipoNuevoNoEstaSiendoUsadoDeberiaActualizar() {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("AdminId")).thenReturn(1L);
		when(servicioLogin.buscarPorId((Long) session.getAttribute("AdminId"))).thenReturn(usuario);
		when(servicioEquipo.consultarEquipo(equipo)).thenReturn(null);
		when(servicioEquipo.listarTodosLosEquipos()).thenReturn(equipos);
		when(servicioEquipo.traerEquiposQueNoJueganPartidos(equipos)).thenReturn(equipos);
		when(equipo.getNombre()).thenCallRealMethod();
		
		ModelAndView modelo = controlador.actualizarEquipo(equipo, request);
		
		assertThat(modelo.getViewName()).isEqualTo("ABM-Equipo");
		assertThat(modelo.getModel().get("aviso")).isEqualTo("Se actualizo correctamente");
		
		verify(servicioLogin, times(1)).buscarPorId((Long) session.getAttribute("AdminId"));
		verify(usuario, times(1)).getNombreYApellido();
		verify(servicioEquipo, times(1)).consultarEquipo(equipo);
		verify(servicioEquipo, times(1)).listarTodosLosEquipos();
		verify(servicioEquipo, times(1)).traerEquiposQueNoJueganPartidos(equipos);
		verify(request, times(2)).getSession();
		
	}

	@Test
	public void siElNombreDeEquipoNuevoEstaSiendoUsadoNoDeberiaActualizar() {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("AdminId")).thenReturn(1L);
		when(servicioLogin.buscarPorId((Long) session.getAttribute("AdminId"))).thenReturn(usuario);
		when(servicioEquipo.consultarEquipo(any(Equipo.class))).thenReturn(equipo);
		when(servicioEquipo.listarTodosLosEquipos()).thenReturn(equipos);
		when(servicioEquipo.traerEquiposQueNoJueganPartidos(equipos)).thenReturn(equipos);
		when(equipo.getNombre()).thenCallRealMethod();
		
		ModelAndView modelo = controlador.actualizarEquipo(equipo, request);
		
		assertThat(modelo.getViewName()).isEqualTo("ABM-Equipo");
		assertThat(modelo.getModel().get("aviso")).isEqualTo("El nombre se encuentra en uso");

		verify(servicioLogin, times(1)).buscarPorId((Long) session.getAttribute("AdminId"));
		verify(usuario, times(1)).getNombreYApellido();
		verify(servicioEquipo, times(1)).consultarEquipo(equipo);
		verify(servicioEquipo, times(1)).listarTodosLosEquipos();
		verify(servicioEquipo, times(1)).traerEquiposQueNoJueganPartidos(equipos);
		verify(request, times(2)).getSession();
	}

	@Test
	public void siElEquipoNoExisteSeDeberiaPoderCrear() {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("AdminId")).thenReturn(1L);
		when(servicioLogin.buscarPorId((Long) session.getAttribute("AdminId"))).thenReturn(usuario);
		when(servicioEquipo.consultarEquipo(equipo)).thenReturn(null);
		when(servicioEquipo.listarTodosLosEquipos()).thenReturn(equipos);
		when(servicioEquipo.traerEquiposQueNoJueganPartidos(equipos)).thenReturn(equipos);
		when(equipo.getNombre()).thenCallRealMethod();
		
		ModelAndView modelo = controlador.crearEquipo(equipo, request);
		
		assertThat(modelo.getViewName()).isEqualTo("ABM-Equipo");
		assertThat(modelo.getModel().get("aviso")).isEqualTo("Se creo el equipo correctamente");
		
		verify(servicioLogin, times(1)).buscarPorId((Long) session.getAttribute("AdminId"));
		verify(usuario, times(1)).getNombreYApellido();
		verify(servicioEquipo, times(1)).listarTodosLosEquipos();
		verify(servicioEquipo, times(1)).traerEquiposQueNoJueganPartidos(equipos);
		verify(request, times(2)).getSession();
	}

	@Test
	public void siElEquipoExisteNoSeDeberiaPoderCrear() {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("AdminId")).thenReturn(1L);
		when(servicioLogin.buscarPorId((Long) session.getAttribute("AdminId"))).thenReturn(usuario);
		when(servicioEquipo.consultarEquipo(any(Equipo.class))).thenReturn(equipo);
		when(servicioEquipo.listarTodosLosEquipos()).thenReturn(equipos);
		when(servicioEquipo.traerEquiposQueNoJueganPartidos(equipos)).thenReturn(equipos);
		when(equipo.getNombre()).thenCallRealMethod();
		
		ModelAndView modelo = controlador.crearEquipo(equipo, request);
		
		assertThat(modelo.getViewName()).isEqualTo("ABM-Equipo");
		assertThat(modelo.getModel().get("aviso")).isEqualTo("No se pudo crear el equipo");

		verify(servicioLogin, times(1)).buscarPorId((Long) session.getAttribute("AdminId"));
		verify(usuario, times(1)).getNombreYApellido();
		verify(servicioEquipo, times(1)).listarTodosLosEquipos();
		verify(servicioEquipo, times(1)).traerEquiposQueNoJueganPartidos(equipos);
		verify(request, times(2)).getSession();
	}
}