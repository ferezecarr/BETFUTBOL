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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Cuota;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.NuevoEventoDTO;
import ar.edu.unlam.tallerweb1.modelo.Partido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuota;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPartido;

@Controller
public class ControladorABMEvento {
		
	@Inject
	private ServicioLogin servicioLogin;	
	@Inject
	private ServicioPartido servicioPartido;	
	@Inject
	private ServicioEvento servicioEvento;	
	@Inject
	private ServicioCuota servicioCuota;
	
	
	@RequestMapping(path = "ABM-Evento")
	public ModelAndView mostrarEventos(HttpServletRequest request) {
		
		if(request.getSession().getAttribute("AdminId") == null) {
			return new ModelAndView("redirect:/");			
		}
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogeado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("AdminId"));
		modelo.put("usuario",usuarioLogeado);
		modelo.put("nombre",usuarioLogeado.getNombreYApellido());
			
		List<Partido> partidos = servicioPartido.listarTodosLosPartidos();
		modelo.put("partidos", partidos);
		
		Evento eventoNuevo = new Evento();
		modelo.put("evento", eventoNuevo);
		
		List<Evento> eventos = servicioEvento.listarEventos();
		modelo.put("eventos", eventos);
		
		modelo.put("eventoDTO", new NuevoEventoDTO());
		
		return new ModelAndView("ABM-Evento",modelo);
	}
	
	@RequestMapping(path = "/crear-evento", method = RequestMethod.POST)
	public ModelAndView recibirEjemplo(@ModelAttribute("eventoDTO") NuevoEventoDTO eventoDTO){	
		//Seteo el evento a cada cuota (si no hago esto el 'evento_id' queda en null en la bdd)
		for (Cuota cuota : eventoDTO.getCuotas()) {
			cuota.setEvento(eventoDTO.getEvento());
		}
		
		//Asigno el partido al evento (Ya que solo capture la id)
		Long idPartido = eventoDTO.getEvento().getPartido().getId();
		eventoDTO.getEvento().setPartido(servicioPartido.buscarPorId(idPartido));
		
		//Agrego las cuotas del DTO al evento del mismo
		eventoDTO.getEvento().setCuotas(eventoDTO.getCuotas());
		
		//Guardo el evento y, por la cascada, las cuotas
		servicioEvento.guardar(eventoDTO.getEvento());		
		return new ModelAndView("redirect:/ABM-Evento");
	}
	
	/*Esta URL es a la que accede Ajax para obtener el listado de cuotas segun el evento 
	 * que se selecciono. Lo que retorna es un JSON que se usa en el 'success' para 
	 * generar el formulario. Se puede ver que devuelve si se escribe en el navegador una 
	 * url como: 'http://localhost:8080/proyecto-limpio-spring/traerCuotas?id=1'*/
	
	@RequestMapping(value = "/traerCuotas", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Cuota> traerCuotas(@RequestParam("id") String id) {
		return servicioCuota.traerCuotasSegunEvento(servicioEvento.consultarEvento(Long.parseLong(id)));
	}
	
	//Aca se llega con el boton 'Actualizar' del formulario generado
	@RequestMapping(value = "/actualizar-cuotas", method = RequestMethod.POST)
	public ModelAndView actualizarCuotas(@ModelAttribute("evento") Evento eventoNuevo){		
		/*Obtengo la id del evento que se quiere actualizar. Cuando mandas un form se crea 
		 * una nueva instancia del objeto. Como yo no estoy pidiendo id de partido, 
		 * tipo de evento y demas cosas (solo valores de las cuotas), el resto de cosas 
		 * queda en NULL.
		 * En la vista puse un input hidden con la id del evento para traerlo con todas las 
		 * cosas seteadas y otro hidden para la id de la cuota que uso en el if de abajo para 
		 * comparar (Este se podria obviar)*/
		Evento eventoOriginal = servicioEvento.consultarEvento(eventoNuevo.getId());
		
		/*Si la id de la cuota es la misma (me estoy reasegurando, por eso dije que se podria 
		 * obviar) y los valores son distintos, hago el seteo (no seteo algo que no se cambio)*/
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
		
		return new ModelAndView("redirect:/ABM-Evento");
	}
	
	
	
}
