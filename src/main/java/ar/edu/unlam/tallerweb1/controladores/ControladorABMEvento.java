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

import ar.edu.unlam.tallerweb1.modelo.Cuota;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuota;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;

@Controller
public class ControladorABMEvento {
	

	
	@Inject
	private ServicioLogin servicioLogin;
	
	@Inject
	private ServicioPartido servicioPartido;
	
	@Inject
	private ServicioEvento servicioEvento;
	
	@Inject
	private ServicioCuota servicioCuota;
	
	

	
	@RequestMapping(path = "ABM-Evento")
	public ModelAndView mostrarEventos(HttpServletRequest request) {

		
		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");
			
		}
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogeado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("AdminId"));
		modelo.put("usuario",usuarioLogeado);
		modelo.put("nombre",usuarioLogeado.getNombreYApellido());
			
		List<Partido> partidos = servicioPartido.listarTodosLosPartidos();
		modelo.put("partidos", partidos);
		
		Evento eventoNuevo = new Evento();
		modelo.put("evento", eventoNuevo);
		
		List<Evento> eventos = servicioEvento.listarEventos();
		modelo.put("eventos", eventos);
		
		return new ModelAndView("ABM-Evento",modelo);
	}
	
	@RequestMapping(path = "crear-evento")
	public ModelAndView crearEvento (@ModelAttribute("evento") Evento evento, HttpServletRequest request){
		
		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");
			
		}
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogeado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("AdminId"));
		modelo.put("usuario",usuarioLogeado);
		modelo.put("nombre",usuarioLogeado.getNombreYApellido());
				
		servicioEvento.guardar(evento);
					
		List<Cuota> cuotas = servicioCuota.traerCuotasSegunEvento(evento);
		modelo.put("cuotas", cuotas);
		
		Evento eventoNuevo = new Evento();
		modelo.put("evento", eventoNuevo);
		
		List<Partido> partidos = servicioPartido.listarTodosLosPartidos();
		modelo.put("partidos", partidos);
		
		List<Evento> eventos = servicioEvento.listarEventos();
		modelo.put("eventos", eventos);
		
		return new ModelAndView("ABM-Evento", modelo);
	}
	
	
	
}
