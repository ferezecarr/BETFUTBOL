package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;

@Controller
public class ControladorABMEvento {
	
	@Inject
	private ServicioEquipo servicioEquipo;
	
	@Inject
	private ServicioLogin servicioLogin;
	
	@Inject
	private ServicioPartido servicioPartido;
	
	@Inject
	private ServicioEvento servicioEvento;
	
	

	
	@RequestMapping(path = "ABM-Evento")
	public ModelAndView añadirEquipo(HttpServletRequest request) {

		
		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");
			
		}
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogeado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("AdminId"));
		modelo.put("usuario",usuarioLogeado);
		modelo.put("nombre",usuarioLogeado.getNombreYApellido());
		
		List<Evento> eventos = servicioEvento.listarEventos();
		modelo.put("eventos", eventos);
		
		List<Partido> partidos = servicioPartido.listarTodosLosPartidos();
		modelo.put("partidos", partidos);
		
		return new ModelAndView("ABM-Evento",modelo);
	}
	
	
	
}
