package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Apuesta;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioApuesta;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuota;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorLogin {

	@Inject
	private ServicioLogin servicioLogin;
	@Inject
	private ServicioApuesta servicioApuesta;
	@Inject
	private ServicioEvento servicioEvento;
	@Inject
	private ServicioCuota servicioCuota;
	
	
	
	
	@RequestMapping(path="/login", method = RequestMethod.POST)
	public ModelAndView irALogin(@ModelAttribute("usuario") Usuario usuario ,HttpServletRequest request) {
		
		ModelMap modelo = new ModelMap();
		
		List<Evento> misEventos = servicioEvento.listarEventosPorNombre("Resultado");
		modelo.put("evento_apostarPorGanadorEmpate", misEventos);	
		List<Evento> misEventos2 = servicioEvento.listarEventosPorNombre("Cuantos goles hace un equipo");
		modelo.put("evento_apostarPorGoles", misEventos2);
		Apuesta apuesta= new Apuesta();	
		modelo.put("apuesta",apuesta);	
		
		
		if(servicioLogin.consultarUsuario(usuario) != null)
		{	
			request.getSession().setAttribute("userLogin", usuario);
			
			Usuario usuarioBuscado= servicioLogin.consultarUsuario(usuario);
			modelo.put("usuario",usuarioBuscado);
			modelo.put("nombre",usuarioBuscado.getNombreYApellido());
		}
		else
		{
			modelo.put("error", "no se encuentra registrado");
			return new ModelAndView("Error",modelo);
		}
		
		
		return new ModelAndView("index", modelo);
		
	}
	
	
	
	@RequestMapping(path = "/validar-login" , method = RequestMethod.POST)
	public ModelAndView solicitarLoginParaApostar(@ModelAttribute("usuario") Usuario usuario ,HttpServletRequest request) {
		
		ModelMap modelo = new ModelMap();
		
		List<Evento> misEventos = servicioEvento.listarEventosPorNombre("Resultado");
		modelo.put("evento", misEventos);	
		Apuesta apuesta= new Apuesta();		
		modelo.put("apuesta",apuesta);	
		
		if(servicioLogin.consultarUsuario(usuario) == null) {
			modelo.put("error", "Necesita Ingresar para poder apostar");
		} else {
			
			request.getSession().setAttribute("userLogin", usuario);
			
		
			Usuario usuarioBuscado= servicioLogin.consultarUsuario(usuario);
			
			modelo.put("usuario",usuarioBuscado);
			modelo.put("nombre",usuarioBuscado.getNombreYApellido());
		
			return new ModelAndView("index",modelo);
		}
		return new ModelAndView("Error",modelo);
	}
	
	
	
	
	@RequestMapping(path = "/registro-usuario" , method = RequestMethod.POST)
	public ModelAndView registrarUsuario(@ModelAttribute("registroUsuario") Long id) {
		ModelMap modelo = new ModelMap();
		if(servicioLogin.buscarPorId(id) == null){
			modelo.put("error", "Por favor ingrese sus datos");
		} else {
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("index",modelo);
	}
	
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("home");
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/index");
	}
	
	
}
