package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorIndex {	
	//Si no se lo injecta, no lo reconoce como servicio, sino como una clase normal
	@Inject	private ServicioEvento servicioEvento;
	@Inject	private ServicioApuesta servicioApuesta;
	@Inject	private ServicioUsuario servicioUsuario;
	@Inject	private ServicioCuota servicioCuota;
	
	@RequestMapping("/index")
	public ModelAndView index() {		
		ModelMap modelo = new ModelMap();
		
		/*
		 * 
		 * al ir a validar-login, solo retorna un usuario, y jode el campo apuesta (modales, linea 18)..,
		 *  asique pasé la lista de evento y las apuestas a validar login para que retorne el usuario con la apuesta y el evento*/
		/*aunque al no estar terminado lo de unir al usuario loguado con la apuesta y el evento, 
		 * no puedo sacar el evento y la apuesta de acá, por eso lo dejé*/
	
		
		
		
		
		List<Evento> misEventos = servicioEvento.listarEventosPorNombre("Resultado");
		modelo.put("evento_apostarPorGanadorEmpate", misEventos);	
		List<Evento> misEventos2 = servicioEvento.listarEventosPorNombre("Cuantos goles hace un equipo");
		modelo.put("evento_apostarPorGoles", misEventos2);
		Apuesta apuesta= new Apuesta();	
		modelo.put("apuesta",apuesta);	
		Usuario usuario =new Usuario();
		modelo.put("usuario",usuario);
		
		return new ModelAndView("index", modelo);
	}


	@RequestMapping(path="/procesar-apuesta", method=RequestMethod.POST)
	public ModelAndView buscarUsuarioPorId(@ModelAttribute("apuesta")Apuesta apuesta,HttpServletRequest request){	
		
		/*Estamos haciendo que todas las apuestas pertenezcan al usuario de id 1.
		 * Después cuando exista login, esto se saca*/
		
		
		//con ésto evita que apueste sin loguearse
		
		/*
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userLogin") == null) {
		  return new ModelAndView("Error");
		}
		*/
		
		Usuario usuarioDefault = new Usuario();
		
		usuarioDefault = servicioUsuario.traerUsuarioDeId1();	
		apuesta.setApostador(usuarioDefault);		

		//Aumentando los votos de la apuesta seleccionada
		servicioCuota.agregarVoto(apuesta.getEvento().getId(), apuesta.getCuotaNombre());
		
		/*Recalcular Cuotas*/
		Evento e = servicioEvento.consultarEvento(apuesta.getEvento().getId());
		e.setCuotas(servicioCuota.recalcularCuotas(e.getCuotas(), apuesta.getCuotaNombre()));	
		servicioEvento.actualizar(e);
		
		//Guardando la apuesta
		servicioApuesta.guardar(apuesta);
		
		return new ModelAndView("redirect:/index");
	}
	
	@RequestMapping(path="/Error",method=RequestMethod.GET)
	public ModelAndView pruebaError()
	{
		return new ModelAndView("Error");
	}
	
}
