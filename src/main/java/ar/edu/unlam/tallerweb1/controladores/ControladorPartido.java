package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;

@Controller
public class ControladorPartido {
	
	@Inject
	private ServicioPartido servicioPartido;
	
	@RequestMapping("/partido")
	public ModelAndView irAlPartido() {
		ModelMap modelo = new ModelMap();
		Partido partido = new Partido();
		modelo.put("partido", partido);
		return new ModelAndView("partido" , modelo);
		
	}
	
	@RequestMapping(path = "/validar-partido" , method = RequestMethod.POST)
	public ModelAndView validarPartido(@ModelAttribute("partido") Partido partido) {
		ModelMap modelo = new ModelMap();
		if(servicioPartido.consultarPartido(partido) != null) {
			return new ModelAndView("redirect:/partido");
		} else {
			modelo.put("error", "No hay ningun partido con ese id");
		}
		return new ModelAndView("partido" , modelo);
	}
		
	

}
