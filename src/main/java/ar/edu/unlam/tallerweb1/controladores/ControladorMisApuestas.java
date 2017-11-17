package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Apuesta;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioApuesta;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuota;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorMisApuestas {
	
	//Si no se lo injecta, no lo reconoce como servicio, sino como una clase normal
	@Inject	private ServicioEvento servicioEvento;
	@Inject	private ServicioApuesta servicioApuesta;
	@Inject	private ServicioUsuario servicioUsuario;
	@Inject	private ServicioCuota servicioCuota;
	@Inject	private ServicioLogin servicioLogin;
	
	
	@RequestMapping("/mis-apuestas")
	public ModelAndView irAMisApuestas(@ModelAttribute("apuesta") Apuesta apuesta, HttpServletRequest request){
		
		if(request.getSession().getAttribute("userId") == null) {
			return new ModelAndView("redirect:/");
		}
		Usuario usuario = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("userId"));
		
		apuesta.setApostador(usuario);
		
		ModelMap modelo = new ModelMap();
		
		//al no agregarle un usaurio al modelo, lanza una error, 
		//por no rellenar el fomulario de login y registro en el modal. 
		modelo.put("usuario",usuario);
		
		List<Apuesta> apuestas = servicioApuesta.buscarPorApuesta(usuario);

		modelo.put("apuestas", apuestas);

		return new ModelAndView("mis-apuestas", modelo);
	}

}
