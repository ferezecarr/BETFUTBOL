package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorEventosTerminados {

	
	@Inject ServicioLogin servicioLogin;
	@Inject ServicioEvento servicioEvento;
	
	@RequestMapping("/eventos-terminados")
	public ModelAndView listarEventosTerminados(HttpServletRequest request)
	{
		ModelMap modelo= new ModelMap();
		
		Usuario usuarioNuevo= new Usuario();
		
		if(request.getSession().getAttribute("userId") != null) {
			
			usuarioNuevo = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("userId"));
			
		}
		
		modelo.put("usuario",usuarioNuevo);
		modelo.put("nombre",usuarioNuevo.getNombreYApellido());
		List<Evento> misEventos = servicioEvento.listarEventosFinalizados();
		modelo.put("eventos", misEventos);	
		
		return new ModelAndView("eventos-terminados",modelo);
	}
}
