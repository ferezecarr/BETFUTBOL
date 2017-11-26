package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.modelo.NuevoEventoDTO;
import ar.edu.unlam.tallerweb1.modelo.Cuota;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;

@Controller
public class zzzTestCreacion {		
	@Inject	private ServicioEvento servicioEvento;
	
	/*Trayendo la vista y el DTO*/
	@RequestMapping(path = "/test-creacion")
	public ModelAndView irAEjemplo(){
		NuevoEventoDTO eventoDTO = new NuevoEventoDTO();		
		ModelMap modelo = new ModelMap();
		modelo.put("eventoDTO", eventoDTO);
		return new ModelAndView("test-creacion", modelo);		
	}

	@RequestMapping(path = "/recibir-test", method = RequestMethod.POST)
	public ModelAndView recibirEjemplo(@ModelAttribute("eventoDTO") NuevoEventoDTO eventoDTO){	
		//Seteo el evento a cada cuota (si no hago esto el 'evento_id' queda en null en la bdd)
		for (Cuota cuota : eventoDTO.getCuotas()) {
			cuota.setEvento(eventoDTO.getEvento());
		}
		
		//Agrego las cuotas del DTO al evento del mismo
		eventoDTO.getEvento().setCuotas(eventoDTO.getCuotas());
		
		//Guardo el evento y, por la cascada, las cuotas
		servicioEvento.guardar(eventoDTO.getEvento());		
		return new ModelAndView("redirect:/test-creacion");
	}
}