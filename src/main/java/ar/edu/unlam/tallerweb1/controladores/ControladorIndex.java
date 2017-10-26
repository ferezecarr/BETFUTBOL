package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;

@Controller
public class ControladorIndex {
	
	
	//Si no se lo injecta, no lo reconoce como servicio, sino como una clase normal
	@Inject
	private ServicioEvento servicioEvento;

	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelMap modelo = new ModelMap();
		List<Evento> misEventos = servicioEvento.listarEventos();
		modelo.put("evento", misEventos);
		return new ModelAndView("index", modelo);
	}
}
