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
	
	
	
	//lo cambie  que responda a "Index" porque sino, cuando el usuario se logueaba,
	//en la url aparecia como login en vez de index, 
	
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
			Usuario usuarioBuscado= servicioLogin.consultarUsuario(usuario);
			request.getSession().setAttribute("userId", usuarioBuscado.getId());
			
			modelo.put("usuario",usuarioBuscado);
			modelo.put("nombre",usuarioBuscado.getNombreYApellido());
		}
		else
		{
			return new ModelAndView("Error",modelo);
		}
			
		return new ModelAndView("index",modelo);
	}
	
	@RequestMapping("/logout")
	public ModelAndView cerrarSession(HttpServletRequest request) {
		
		request.getSession().invalidate();
		return new ModelAndView("redirect:/");
	}
	
	
	
	
	@RequestMapping(path = "/registro-usuario" , method = RequestMethod.POST)
	public ModelAndView registrarUsuario(@ModelAttribute("usuario") Usuario usuario,HttpServletRequest request) {
		
		ModelMap modelo= new ModelMap();
		
		List<Evento> misEventos = servicioEvento.listarEventosPorNombre("Resultado");
		modelo.put("evento_apostarPorGanadorEmpate", misEventos);	
		List<Evento> misEventos2 = servicioEvento.listarEventosPorNombre("Cuantos goles hace un equipo");
		modelo.put("evento_apostarPorGoles", misEventos2);
		Apuesta apuesta= new Apuesta();	
		modelo.put("apuesta",apuesta);	
		
		
		Usuario usuarioAguardar= new Usuario();
		
		if(servicioLogin.consultarUsuarioPorMail(usuario)==null)
		{
			
			usuarioAguardar.setEmail(usuario.getEmail());
			usuarioAguardar.setNombreYApellido(usuario.getNombreYApellido());
			usuarioAguardar.setPassword(usuario.getPassword());
			servicioLogin.guardar(usuarioAguardar);
			
			request.getSession().setAttribute("userId", usuarioAguardar.getId());
			
			modelo.put("usuario",usuarioAguardar);
			modelo.put("nombre",usuarioAguardar.getNombreYApellido());
		
		}
		else
		{
			return new ModelAndView("Error");
		}
		
		modelo.put("usuario",usuarioAguardar);
		modelo.put("registro","registro Exitoso");
		modelo.put("nombre",usuarioAguardar.getNombreYApellido());
		
		return new ModelAndView("index",modelo);
	}
	
	
	
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/index");
	}
	
	
}
