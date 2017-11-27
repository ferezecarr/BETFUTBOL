package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorABMUsuarios {

	@Inject
	private ServicioLogin servicioLogin;
	
	@RequestMapping(path="perfil")
	public ModelAndView mostrarPerfil(HttpServletRequest request) {
		
		if(request.getSession().getAttribute("userId") == null) {
			return new ModelAndView("redirect:/");
		}
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		
		List<Usuario> usuarios = servicioLogin.listarTodos();
		modelo.put("usuario", usuarios);
		
		Usuario usuarioLogueado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("userId"));
		modelo.put("usuario", usuarioLogueado);
		modelo.put("nombre", usuarioLogueado.getNombreYApellido());
		modelo.put("email", usuarioLogueado.getEmail());
		modelo.put("password", usuarioLogueado.getPassword());
		
		return new ModelAndView("perfil" , modelo);
	}
	
	@RequestMapping(path="actualizar-perfil" , method = RequestMethod.POST)
	public ModelAndView actualizarPerfil(@ModelAttribute("usuario") Usuario usuario , HttpServletRequest request) {
		
		if(request.getSession().getAttribute("userId") == null) {
			return new ModelAndView("redirect:/");
		}
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogueado = servicioLogin.buscarPorId((Long) request.getSession().getAttribute("userId"));
		
		if(servicioLogin.buscarPorId(usuario.getId()) != null) {
			servicioLogin.actualizar(usuario);
			modelo.put("aviso", "Se actualizo correctamente");
			modelo.put("usuario", usuario);
			modelo.put("nombre", usuario.getNombreYApellido());
		} else {
			modelo.put("aviso", "No se pudo actualizar");
			modelo.put("usuario", usuarioLogueado);
			modelo.put("nombre", usuarioLogueado.getNombreYApellido());
		}
		return new ModelAndView("perfil", modelo);
	}
}
