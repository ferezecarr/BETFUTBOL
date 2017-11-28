package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;

@Controller
public class zzzTestFinalizacion {	
	@Inject
	private ServicioEvento servicioEvento;
	
	@Inject
	private ServicioPartido servicioPartido;
	
	@RequestMapping("/test-finalizacion")
	public ModelAndView irAlPartido() {
		List<Evento> eventosFinalizables = servicioEvento.listarEventosFinalizables();
		
		ModelMap modelo = new ModelMap();
		modelo.put("eventosFinalizables", eventosFinalizables);
		return new ModelAndView("test-finalizacion", modelo);
		
	}
	
	@RequestMapping(value = "/traerEvento", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Evento traerEvento(@RequestParam("id") String id) {
		return servicioEvento.consultarEvento(Long.parseLong(id)); 
	}
	
	@RequestMapping(path="/finalizar-evento", method = RequestMethod.POST)
	public ModelAndView finalizarEvento(@ModelAttribute("eventoAFinalizar")Evento eventoAFinalizar){		
		/*Se recupera el partido original, porque el que viene del form, solo tiene seteado lo 
		 * que se le paso (id, golesLocal y golesVisitante)*/
		Partido partidoConDatosNuevos = eventoAFinalizar.getPartido();
		Partido partidoOriginal = servicioPartido.consultarPartido(partidoConDatosNuevos);		

		//Una vez el partido recuperado, le agrego los nuevos datos y lo actualizo
		partidoOriginal.setGolesLocal(partidoConDatosNuevos.getGolesLocal());
		partidoOriginal.setGolesVisitante(partidoConDatosNuevos.getGolesVisitante());
		partidoOriginal.setIsResultadoFinal(true);
		servicioPartido.actualizarPartido(partidoOriginal);
		
		//La misma idea de arriba, pero con el evento
		Evento eventoOriginal = servicioEvento.consultarEvento(eventoAFinalizar.getId());
		eventoOriginal.setCuotaGanadora(eventoAFinalizar.getCuotaGanadora());
		eventoOriginal.setIsTerminado(true);
		servicioEvento.actualizar(eventoOriginal);
		
		return new ModelAndView("redirect:/test-finalizacion");
	}
}