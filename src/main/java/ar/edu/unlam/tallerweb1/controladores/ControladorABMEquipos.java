package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorABMEquipos {
	
	@RequestMapping(path = "añadir-equipo" , method = RequestMethod.POST)
	public ModelAndView añadirEquipo() {
		ModelMap modelo = new ModelMap();
		return new ModelAndView();
	}

}
