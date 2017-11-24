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
import ar.edu.unlam.tallerweb1.modelo.Cuota;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuota;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;

@Controller
public class zzzEjemploAjax2 {		
	@Inject	private ServicioEvento servicioEvento;
	@Inject private ServicioCuota servicioCuota;
	
	@RequestMapping(path = "/ejemplo-ajax2")
	public ModelAndView irAEjemplo(){
		ModelMap modelo = new ModelMap();
		List<Evento> eventos = servicioEvento.listarEventos();
		modelo.put("eventos", eventos);
		return new ModelAndView("ejemplo-ajax2", modelo);		
	}

	@RequestMapping(value = "/traerCuotasV2", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Cuota> traerCuotas(@RequestParam("id") String id) {
		return servicioCuota.traerCuotasSegunEvento(servicioEvento.consultarEvento(Long.parseLong(id)));
	}
	
	@RequestMapping(value = "/actualizar-cuotasV2", method = RequestMethod.POST)
	public ModelAndView actualizarCuotas(@ModelAttribute("evento") Evento eventoNuevo){		
		/*Obtengo la id del evento que se quiere actualizar. Cuando mandas un form para un 
		 * update, aparentemente se crea una nueva instancia del objeto. Como yo no estoy 
		 * pidiendo id de partido, tipo de evento y demas cosas (solo valores de las cuotas), 
		 * el resto de cosas quedan en NULL. En la vista puse un input hidden con la id del 
		 * evento para traerlo con todas las cosas seteadas y otro hidden para la id de la cuota 
		 * que uso en el if de abajo para comparar*/
		Evento eventoOriginal = servicioEvento.consultarEvento(eventoNuevo.getId());
		
		/*Si la id de la cuota es la misma (me estoy reasegurando, se podria obviar esto) y 
		 * los valores son distintos, hago el seteo (no seteo algo que no se cambio)*/
		Cuota cuotaOriginal = new Cuota();
		Cuota cuotaAnalizada = new Cuota();
		for(int i=0;i<eventoOriginal.getCuotas().size();i++){
			cuotaOriginal = eventoOriginal.getCuotas().get(i);
			cuotaAnalizada = eventoNuevo.getCuotas().get(i);
			if((cuotaOriginal.getValor() != cuotaAnalizada.getValor()) && 
					cuotaOriginal.getId() == cuotaAnalizada.getId()){
				eventoOriginal.getCuotas().get(i).setValor(cuotaAnalizada.getValor());
			}
		}
		
		//Actualizando el evento con los valores nuevos ya seteados
		servicioEvento.actualizar(eventoOriginal);
		
		return new ModelAndView("redirect:/ejemplo-ajax2");
	}
}