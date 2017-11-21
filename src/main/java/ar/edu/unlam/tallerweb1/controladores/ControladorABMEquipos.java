package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;

@Controller
public class ControladorABMEquipos {
	
	@Inject
	private ServicioEquipo servicioEquipo;
	
	/*@RequestMapping(path = "añadir-equipo" , method = RequestMethod.POST)
	public ModelAndView añadirEquipo(@ModelAttribute("equipo") Equipo equipo) {
		ModelMap modelo = new ModelMap();
		equipo = servicioEquipo.guardarEquipo(equipo);
		return new ModelAndView("equipos" , modelo);
	}
	
	public ModelAndView actualizarEquipo() {
		
	}*/

}
