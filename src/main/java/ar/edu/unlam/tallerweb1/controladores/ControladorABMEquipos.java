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

import ar.edu.unlam.tallerweb1.modelo.Apuesta;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorABMEquipos {
	
	@Inject
	private ServicioEquipo servicioEquipo;
	
	@Inject
	private ServicioLogin servicioLogin;
	

	
	/*@RequestMapping(path = "añadir-equipo" , method = RequestMethod.POST)
	public ModelAndView añadirEquipo(@ModelAttribute("equipo") Equipo equipo) {
		ModelMap modelo = new ModelMap();
		equipo = servicioEquipo.guardarEquipo(equipo);
		return new ModelAndView("equipos" , modelo);
	}
	
	public ModelAndView actualizarEquipo() {
		
	}*/

	
	@RequestMapping(path = "ABM-Equipo")
	public ModelAndView añadirEquipo(HttpServletRequest request) {

		
		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");
			
		}
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogeado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("AdminId"));
		modelo.put("usuario",usuarioLogeado);
		modelo.put("nombre",usuarioLogeado.getNombreYApellido());
		
		List<Equipo> equipos = servicioEquipo.listarTodosLosEquipos();
		modelo.put("equipos", equipos);
		
		return new ModelAndView("ABM-Equipo",modelo);
	}
	
	
	
}
