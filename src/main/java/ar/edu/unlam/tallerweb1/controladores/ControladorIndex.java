package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import javax.inject.Inject;
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
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorIndex {	
	//Si no se lo injecta, no lo reconoce como servicio, sino como una clase normal
	@Inject
	private ServicioEvento servicioEvento;
	@Inject
	private ServicioApuesta servicioApuesta;
	@Inject
	private ServicioUsuario servicioUsuario;
	
	@RequestMapping("/index")
	public ModelAndView index() {		
		ModelMap modelo = new ModelMap();
		List<Evento> misEventos = servicioEvento.listarEventosPorNombre("Resultado");
		modelo.put("evento", misEventos);	
		Apuesta apu= new Apuesta();
		
		/*Estamos haciendo que todas las apuestas pertenezcan al usuario de id 1.
		 * Después cuando exista login, esto se saca*/
		Usuario usuarioDefault = new Usuario();
		usuarioDefault = servicioUsuario.traerUsuarioDeId1();	
		apu.setApostador(usuarioDefault);		
		
		modelo.put("apuesta",apu);
		modelo.put("usuario", usuarioDefault);
		
		return new ModelAndView("index", modelo);
	}


	@RequestMapping(path="/procesar-apuesta}", method=RequestMethod.POST)
	public ModelAndView buscarUsuarioPorId(@ModelAttribute("apuesta")Apuesta apuesta)
	{
		ModelMap model = new ModelMap();
		servicioApuesta.guardar(apuesta);
		
		model.put("usuarioId1",apuesta);
		return new ModelAndView("index",model);
	}
	
}
