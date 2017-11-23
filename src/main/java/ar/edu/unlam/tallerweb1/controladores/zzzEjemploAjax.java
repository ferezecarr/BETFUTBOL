package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
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
public class zzzEjemploAjax {		
	@Inject	private ServicioEvento servicioEvento;
	@Inject private ServicioCuota servicioCuota;
	
	@RequestMapping(path = "/ejemplo-ajax")
	public ModelAndView irAEjemplo(){
		ModelMap modelo = new ModelMap();
		List<Evento> eventos = servicioEvento.listarEventos();
		modelo.put("eventos", eventos);
		return new ModelAndView("ejemplo-ajax", modelo);		
	}

	@RequestMapping(value = "/traerCuotas", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Cuota> traerCuotas(@RequestParam("id") String id) {
		return servicioCuota.traerCuotasSegunEvento(servicioEvento.consultarEvento(Long.parseLong(id)));
	}
	
	@RequestMapping(value = "/actualizar-cuotas", method = RequestMethod.POST)
	public @ResponseBody ModelAndView actualizarCuotas(@RequestBody String json){	
		System.out.println(json);		
		return new ModelAndView("redirect:/ejemplo-ajax");
	}
}