package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
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
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorMisApuestas {
	
	//Si no se lo injecta, no lo reconoce como servicio, sino como una clase normal
	@Inject	private ServicioEvento servicioEvento;
	@Inject	private ServicioApuesta servicioApuesta;
	@Inject	private ServicioUsuario servicioUsuario;
	@Inject	private ServicioCuota servicioCuota;
	
	
	@RequestMapping("/mis-apuestas")
	public ModelAndView irAMisApuestas(@ModelAttribute("apuesta") Apuesta apuesta){
		
		Usuario usuario1 = new Usuario();
		usuario1 = servicioUsuario.traerUsuarioDeId1();
		apuesta.setApostador(usuario1);
		
		ModelMap modelo = new ModelMap();
		
		
		List<Apuesta> apuestas = servicioApuesta.buscarPorApuesta(usuario1);

		modelo.put("apuestas", apuestas);

		return new ModelAndView("mis-apuestas", modelo);
	}

}
