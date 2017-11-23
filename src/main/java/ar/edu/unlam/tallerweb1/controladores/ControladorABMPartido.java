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
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;

@Controller
public class ControladorABMPartido {
	
	@Inject
	private ServicioEquipo servicioEquipo;
	
	@Inject
	private ServicioLogin servicioLogin;
	
	@Inject
	private ServicioPartido servicioPartido;

	

	
	@RequestMapping(path = "ABM-Partido")
	public ModelAndView mostrarPartido(HttpServletRequest request) {

		
		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");
			
		}
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogeado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("AdminId"));
		modelo.put("usuario",usuarioLogeado);
		modelo.put("nombre",usuarioLogeado.getNombreYApellido());
		
		
		List<Equipo> equipos = servicioEquipo.listarTodosLosEquipos();
		modelo.put("equipos", equipos);
		
		Partido partidoNuevo = new Partido();
		modelo.put("partido", partidoNuevo);
		
		List<Partido> partidos = servicioPartido.listarTodosLosPartidos();
		modelo.put("partidos", partidos);
		
		
		return new ModelAndView("ABM-Partido",modelo);
	}
	
	
	
	

	@RequestMapping(path = "crear-Partido", method = RequestMethod.POST)
	public ModelAndView crearPartido(@ModelAttribute("partido") Partido partido,HttpServletRequest request) {

		
		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");
			
		}
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogeado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("AdminId"));
		modelo.put("usuario",usuarioLogeado);
		modelo.put("nombre",usuarioLogeado.getNombreYApellido());
		
		
		if(servicioPartido.consultarPartido(partido)==null)
		{
			servicioPartido.guardarPartido(partido);
		}
		
		
		List<Equipo> equipos = servicioEquipo.listarTodosLosEquipos();
		modelo.put("equipos", equipos);
		
		Partido partidoNuevo = new Partido();
		modelo.put("partido", partidoNuevo);
		
		List<Partido> partidos = servicioPartido.listarTodosLosPartidos();
		modelo.put("partidos", partidos);
		
		
		return new ModelAndView("ABM-Partido",modelo);
	}
	
	
	
	

	@RequestMapping(path ="editar-Partido", method = RequestMethod.POST)
	public ModelAndView editarPartido(@ModelAttribute("partido")Partido partido,HttpServletRequest request) {

		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");
			
		}
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogeado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("AdminId"));
		modelo.put("usuario",usuarioLogeado);
		modelo.put("nombre",usuarioLogeado.getNombreYApellido());
		
		
		if(servicioPartido.consultarPartido(partido)!=null)
		{
			servicioPartido.actualizarPartido(partido);
		}
		
		
		List<Equipo> equipos = servicioEquipo.listarTodosLosEquipos();
		modelo.put("equipos", equipos);
		
		Partido partidoNuevo = new Partido();
		modelo.put("partido", partidoNuevo);
		
		List<Partido> partidos = servicioPartido.listarTodosLosPartidos();
		modelo.put("partidos", partidos);
		
		
		return new ModelAndView("ABM-Partido",modelo);
	}
	
	

	
	
	@RequestMapping(path ="eliminar-Partido", method = RequestMethod.POST)
	public ModelAndView eliminarPartido(@ModelAttribute("partido")Partido partido, HttpServletRequest request) {

		
		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");
			
		}
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogeado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("AdminId"));
		modelo.put("usuario",usuarioLogeado);
		modelo.put("nombre",usuarioLogeado.getNombreYApellido());
		
		
		if(servicioPartido.consultarPartido(partido)!=null)
		{
			servicioPartido.eliminarPartido(partido);
		}
		
		
		List<Equipo> equipos = servicioEquipo.listarTodosLosEquipos();
		modelo.put("equipos", equipos);
		
		Partido partidoNuevo = new Partido();
		modelo.put("partido", partidoNuevo);
		
		List<Partido> partidos = servicioPartido.listarTodosLosPartidos();
		modelo.put("partidos", partidos);
		
		
		return new ModelAndView("ABM-Partido",modelo);
	}
	
}
