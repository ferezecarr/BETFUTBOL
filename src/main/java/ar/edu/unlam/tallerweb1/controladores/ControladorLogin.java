package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.modelo.Apuesta;
import ar.edu.unlam.tallerweb1.modelo.Equipo;
import ar.edu.unlam.tallerweb1.modelo.Evento;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEquipo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEvento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorLogin {

	@Inject
	private ServicioLogin servicioLogin;
	
	@Inject
	private ServicioEvento servicioEvento;
	
	@Inject
	private ServicioEquipo servicioEquipo;
	
	
	@RequestMapping(path="/login", method = RequestMethod.POST)
	public ModelAndView irALogin(@ModelAttribute("usuario") Usuario usuario ,HttpServletRequest request) {
		
		ModelMap modelo = new ModelMap();
		
		List<Evento> misEventos = servicioEvento.listarEventosPorNombre("Resultado");
		modelo.put("eventos", misEventos);	
		Apuesta apuesta= new Apuesta();	
		modelo.put("apuesta",apuesta);	
		
		try {
			
			if(servicioLogin.consultarUsuario(usuario) != null)
			{	
				Usuario usuarioBuscado= servicioLogin.consultarUsuario(usuario);
			
				
				modelo.put("usuario",usuarioBuscado);
				modelo.put("nombre",usuarioBuscado.getNombreYApellido());
				
				
				/*al ser un rol: admin, le redirije a otra vista, y cambia los botones en la barra de navegacion */
				if(usuarioBuscado.getRol().equals("ADMIN"))
				{	
					request.getSession().setAttribute("AdminId", usuarioBuscado.getId());
					
					Equipo equipo= new Equipo();
					modelo.put("equipo",equipo);
					
					List<Equipo> equipos = servicioEquipo.listarTodosLosEquipos();
					modelo.put("equipos", equipos);
					
					
					return new ModelAndView("ABM-Equipo",modelo);
				}
				
				request.getSession().setAttribute("userId", usuarioBuscado.getId());
			}
			else
			{
				modelo.put("aviso","Usuario inexistente");
				return new ModelAndView("index",modelo);
			}
		}
		catch(NullPointerException e) {
			modelo.put("aviso", e.getMessage());
		}
			
		return new ModelAndView("index",modelo);
	}
	
	@RequestMapping("/logout")
	public ModelAndView cerrarSession(HttpServletRequest request) {
		
		request.getSession().invalidate();
		return new ModelAndView("redirect:/");
	}
	
	
	
	
	@RequestMapping(path = "/registro-usuario" , method = RequestMethod.POST)
	public ModelAndView registrarUsuario(@ModelAttribute("usuario") Usuario usuario,HttpServletRequest request) {
		
		ModelMap modelo= new ModelMap();
		
		List<Evento> misEventos = servicioEvento.listarEventosPorNombre("Resultado");
		modelo.put("eventos", misEventos);
		Apuesta apuesta= new Apuesta();	
		modelo.put("apuesta",apuesta);	
		
		Usuario usuarioDefault= new Usuario();
	try {
			if(usuario.getNombreYApellido().isEmpty()) {
				throw new NullPointerException("No puede dejar el campo 'Nombre' vacío");
			}
			if(usuario.getEmail().isEmpty())
				throw new NullPointerException("No puede dejar el campo 'E-Mail' vacío");
			if(usuario.getPassword().isEmpty()){
				throw new NullPointerException("No puede dejar el campo 'Password' vacío");
			}
			
			if(servicioLogin.consultarUsuarioPorMail(usuario)==null)
			{
				
				servicioLogin.guardar(usuario);
				
				request.getSession().setAttribute("userId", usuario.getId());
				
				modelo.put("usuario",usuario);
				modelo.put("nombre",usuario.getNombreYApellido());
				modelo.put("aviso","Registro exitoso");
				enviarMail(usuario);
			
			}
			else
			{
				modelo.put("aviso","El E-mail ya se encuentra en uso");
				modelo.put("usuario",usuarioDefault);
				return new ModelAndView("index",modelo);
			}
		}
	catch(NullPointerException e) {
			modelo.put("aviso", e.getMessage());
			modelo.put("usuario",usuarioDefault);
		}
		
		return new ModelAndView("index",modelo);
	}
	
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/index");
	}
	
	@RequestMapping(value= "/validar-email", method = RequestMethod.POST)
	public @ResponseBody String validarEmail(@RequestBody String json) {
		Usuario usuario =  new Usuario();
		usuario.setEmail(json);
		Usuario usuario2 = servicioLogin.consultarUsuarioPorMail(usuario);
		String mensaje = "OK";
		try {			
			if(usuario2.getEmail() != null) {
				mensaje = "BAD";
				return mensaje;
			}
		}
		catch(NullPointerException e) {
			mensaje = "OK";			
			return mensaje;
		}
		
		return mensaje;
	}
	
	public void enviarMail(Usuario usuario){

		//Voy a usar un servicio SMTP con autenticacion TLS,
		//por lo que tiene que ser un email valido con password correctamente escrito
		final String fromEmail = "proyectospruebaunlam@gmail.com";
		final String password = "unlam123456789";
		
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		//SMTP Host
		props.put("mail.smtp.host", "smtp.gmail.com");
		//TLS Puerto
		props.put("mail.smtp.port", "587");
		//activo autenticacion
		props.put("mail.smtp.auth", "true");
		//activo STARTTLS
		props.put("mail.smtp.starttls.enable", "true");
		
        //Creo un objeto autenticador para pasar como argumento a Session.getInstance
		
		Authenticator auth = new Authenticator() {
		
			//Desabilito el getPasswordAuthentication de nuestra cuenta
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		//preparo el mensaje
		String cuerpoMensaje = "Gracias "+usuario.getNombreYApellido() + " por registrarte en BETFULBOL!";
		
		EmailUtil.sendEmail(session, usuario.getEmail(),"Registro en BetFutbol", cuerpoMensaje);
		
	}

	
	
}
