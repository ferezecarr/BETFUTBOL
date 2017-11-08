package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		return new ModelAndView("redirect:/validar-login", modelo);
	}
	
	@RequestMapping(path = "/validar-login" , method = RequestMethod.POST)
	public ModelAndView solicitarLoginParaApostar(@ModelAttribute("usuario") Usuario usuario) {
		ModelMap modelo = new ModelMap();
		if(servicioLogin.consultarUsuario(usuario) == null) {
			modelo.put("error", "Necesita Ingresar para poder apostar");
		} else {
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("index",modelo);
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
