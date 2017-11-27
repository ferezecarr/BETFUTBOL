package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.modelo.Apuesta;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioApuesta;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuota;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorIndex {	
	//Si no se lo injecta, no lo reconoce como servicio, sino como una clase normal
	@Inject	private ServicioEvento servicioEvento;
	@Inject	private ServicioApuesta servicioApuesta;
	@Inject	private ServicioCuota servicioCuota;
	@Inject	private ServicioLogin servicioLogin;
	
	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value = "filtro", required = false) String filtro, HttpServletRequest request) {		
		ModelMap modelo = new ModelMap();
		
		/*
		 * 
		 * al ir a validar-login, solo retorna un usuario, y jode el campo apuesta (modales, linea 18)..,
		 *  asique pase la lista de evento y las apuestas a validar login para que retorne el usuario con la apuesta y el evento*/
		/*aunque al no estar terminado lo de unir al usuario loguado con la apuesta y el evento, 
		 * no puedo sacar el evento y la apuesta de aca, por eso lo deje*/
		if(filtro==null)
		{
			filtro="Resultado";
		}
		
		List<Evento> misEventos = servicioEvento.listarEventosPorNombre(filtro);
		modelo.put("eventos", misEventos);	
		Apuesta apuesta= new Apuesta();	
		modelo.put("apuesta",apuesta);
		
		if(request.getSession().getAttribute("userId") != null) {
			
			Usuario usuarioLogeado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("userId"));
			modelo.put("usuario",usuarioLogeado);
			modelo.put("nombre",usuarioLogeado.getNombreYApellido());
			
			return new ModelAndView("index", modelo);
		}
		else if(request.getSession().getAttribute("userId") == null){
			Usuario usuario = new Usuario();
			modelo.put("usuario",usuario);
		}
		
		return new ModelAndView("index", modelo);			
		
	}


	
	
	@RequestMapping(path="/procesar-apuesta", method=RequestMethod.POST)
	public ModelAndView buscarUsuarioPorId(@ModelAttribute("apuesta")Apuesta apuesta,HttpServletRequest request){	
	
		
		//con esto evita que apueste sin loguearse
		if(request.getSession().getAttribute("userId") == null)
		{
			return new ModelAndView("redirect:/");
		}
		
		Usuario usuarioDefault = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("userId"));
		
		apuesta.setApostador(usuarioDefault);		

		//Aumentando los votos de la apuesta seleccionada
		servicioCuota.agregarVoto(apuesta.getEvento().getId(), apuesta.getCuotaNombre());
		
		/*Recalcular Cuotas*/
		Evento e = servicioEvento.consultarEvento(apuesta.getEvento().getId());
		e.setCuotas(servicioCuota.recalcularCuotas(e.getCuotas(), apuesta.getCuotaNombre()));	
		servicioEvento.actualizar(e);
		
		//Guardando la apuesta
		servicioApuesta.guardar(apuesta);
		
		
		
		return new ModelAndView("redirect:/");
	}
	
	
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
