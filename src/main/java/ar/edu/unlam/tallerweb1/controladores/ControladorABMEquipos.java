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
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorABMEquipos {
	
	@Inject
	private ServicioEquipo servicioEquipo;
	
	@Inject
	private ServicioLogin servicioLogin;
	
	@RequestMapping(path = "ABM-Equipo")
	public ModelAndView mostrarEquipo(HttpServletRequest request) {

		
		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");
			
		}
		
		ModelMap modelo = new ModelMap();
		
		Equipo equipo= new Equipo();
		modelo.put("equipo",equipo);
		
		List<Equipo> equipos = servicioEquipo.listarTodosLosEquipos();
		modelo.put("equipos", equipos);
		
		Usuario usuarioLogeado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("AdminId"));
		modelo.put("usuario",usuarioLogeado);
		modelo.put("nombre",usuarioLogeado.getNombreYApellido());
		
		//Agrego equipos que no juegan ningun partido
		List<Equipo> equiposSinEncuentros = servicioEquipo.traerEquiposQueNoJueganPartidos(equipos);
		modelo.put("equiposSinPartidos", equiposSinEncuentros);
		
		return new ModelAndView("ABM-Equipo",modelo);
	}

	
	@RequestMapping(path = "crear-equipo" , method = RequestMethod.POST)
	public ModelAndView crearEquipo(@ModelAttribute("equipo") Equipo equipo , HttpServletRequest request) {

		
		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");
			
		}
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogeado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("AdminId"));
		modelo.put("usuario",usuarioLogeado);
		modelo.put("nombre",usuarioLogeado.getNombreYApellido());
		
		
		
		if(servicioEquipo.consultarEquipo(equipo) == null) {
			
			servicioEquipo.guardarEquipo(equipo);
			modelo.put("aviso", "Se creo el equipo correctamente");
		
		} else {
			modelo.put("aviso", "No se pudo crear el equipo");
			
		}
		
		Equipo equipoNuevo= new Equipo();
		modelo.put("equipo", equipoNuevo);
		
		
		List<Equipo> equipos = servicioEquipo.listarTodosLosEquipos();
		modelo.put("equipos", equipos);
		
		List<Equipo> equiposSinEncuentros = servicioEquipo.traerEquiposQueNoJueganPartidos(equipos);
		modelo.put("equiposSinPartidos", equiposSinEncuentros);
		
		return new ModelAndView("ABM-Equipo",modelo);
	}
	
	@RequestMapping(path = "actualizar-equipo" , method = RequestMethod.POST)
	public ModelAndView actualizarEquipo(@ModelAttribute("equipo") Equipo equipo , HttpServletRequest request) {
		
		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");
		}
		//para ver si trae el usuario editado, y lo trae
		
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogeado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("AdminId"));
		modelo.put("usuario",usuarioLogeado);
		modelo.put("nombre",usuarioLogeado.getNombreYApellido());
		
		
		//como estoy actializando el nombre pero no el id, lo tengo que buscar por este ultimo
		if(servicioEquipo.consultarEquipo(equipo) == null) {
	
			
			servicioEquipo.actualizarEquipo(equipo);
		
			modelo.put("aviso", "Se actualizo correctamente");
			
		} else {
			modelo.put("aviso", "El nombre se encuentra en uso");
		}
		
		Equipo equipoCreado = new Equipo();
		modelo.put("equipo",equipoCreado);

		List<Equipo> equipos = servicioEquipo.listarTodosLosEquipos();
		modelo.put("equipos", equipos);
		
		List<Equipo> equiposSinEncuentros = servicioEquipo.traerEquiposQueNoJueganPartidos(equipos);
		modelo.put("equiposSinPartidos", equiposSinEncuentros);
		
		return new ModelAndView("ABM-Equipo" , modelo);
	}
}
