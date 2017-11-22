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
	


	
	@RequestMapping(path = "añadir-equipo" , method = RequestMethod.POST)
	public ModelAndView añadirEquipo(@ModelAttribute("equipo") Equipo equipo , HttpServletRequest request) {

		
		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");
			
		}
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogeado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("AdminId"));
		modelo.put("usuario",usuarioLogeado);
		modelo.put("nombre",usuarioLogeado.getNombreYApellido());
		
		List<Equipo> equipos = servicioEquipo.listarTodosLosEquipos();
		modelo.put("equipos", equipo.getNombre());
		
		if(servicioEquipo.consultarEquipo(equipo) == null) {
			servicioEquipo.guardarEquipo(equipo);
			request.getSession().setAttribute("userAdmin", equipo.getId());
			modelo.put("equipo", equipo);
			modelo.put("nombre", equipo.getNombre());
			modelo.put("aviso-añadir-equipo", "Se añadio el equipo correctamente");
		} else {
			modelo.put("aviso-error-añadir-equipo", "No se pudo añadir el equipo");
		}
		
		return new ModelAndView("ABM-Equipo",modelo);
	}
	
	@RequestMapping(path = "actualizar-equipo" , method = RequestMethod.POST)
	public ModelAndView actualizarEquipo(@ModelAttribute("equipo") Equipo equipo , HttpServletRequest request) {
		
		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");
		}
		
		ModelMap modelo = new ModelMap();
		
		List<Equipo> equipos = servicioEquipo.listarTodosLosEquipos();
		modelo.put("equipos", equipos);
		
		if(servicioEquipo.consultarEquipo(equipo) != null) {
			servicioEquipo.actualizarEquipo(equipo);
			request.getSession().setAttribute("userAdmin", equipo.getId());
			modelo.put("equipo", equipo);
			modelo.put("nombre", equipo.getNombre());
			modelo.put("aviso-actualizar-equipo", "Se actualizo correctamente");
		} else {
			modelo.put("aviso-error-actualizar-equipo", "No se pudo actualizar");
		}
		
		return new ModelAndView("ABM-Equipo" , modelo);
	}
	
	@RequestMapping(path = "eliminar-equipo" , method = RequestMethod.POST)
	public ModelAndView eliminarEquipo(@ModelAttribute("equipo") Equipo equipo , HttpServletRequest request) {
		
		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");
		}
		
		ModelMap modelo = new ModelMap();
		
		List<Equipo> equipos = servicioEquipo.listarTodosLosEquipos();
		modelo.put("equipos", equipos);
		
		if(servicioEquipo.consultarEquipo(equipo) != null) {
			servicioEquipo.eliminarEquipo(equipo);
			request.getSession().setAttribute("userAdmin", equipo.getId());
			modelo.put("equipo", equipo);
			modelo.put("nombre", equipo.getNombre());
			modelo.put("aviso-eliminar-equipo", "Se elimino correctamente");
		} else {
			modelo.put("aviso-error-eliminar-equipo", "No se pudo eliminar");
		}
		
		return new ModelAndView("ABM-Equipo" , modelo);
		
	}
	
	
	
}
