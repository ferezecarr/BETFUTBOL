package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;

@Controller
public class ControladorIndex {
	
	
	//Si no se lo injecta, no lo reconoce como servicio, sino como una clase normal
	@Inject
	private ServicioEvento servicioEvento;
	@Inject
	private ServicioPartido servicioPartido;
	@Inject
	private ServicioEquipo servicioEquipo;

	
	@RequestMapping("/index")
	public ModelAndView index() {
		
		ModelMap modelo = new ModelMap();
		
		List<Evento> misEventos = servicioEvento.listarEventos();
		List<Partido>misPartidos = servicioPartido.listarTodosLosPartidos();
		List<Equipo>misEquipos = servicioEquipo.listarTodosLosEquipos();
		
		modelo.put("evento", misEventos);
		modelo.put("partido", misPartidos);
		modelo.put("equipo", misEquipos);
		
		return new ModelAndView("index", modelo);
	}
}
