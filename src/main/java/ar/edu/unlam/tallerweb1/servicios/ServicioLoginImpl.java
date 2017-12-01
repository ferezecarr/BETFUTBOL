package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.controladores.EmailUtil;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	@Inject
	private UsuarioDao servicioLoginDao;

	@Override
	public Usuario consultarUsuario (Usuario usuario) {
		return servicioLoginDao.consultarUsuario(usuario);
	}

	@Override
	public Usuario consultarUsuarioPorMail (Usuario usuario) {
		return servicioLoginDao.consultarUsuarioPorMail(usuario);
	}
	
	@Override
	public void guardar(Usuario usuario) {
		servicioLoginDao.save(usuario);
		
	}

	@Override
	public List<Usuario> listarTodos() {
		return servicioLoginDao.findAll();
	}

	@Override
	public Usuario buscarPorId(Long id) {
		return servicioLoginDao.findById(id);
	}

	@Override
	public void actualizar(Usuario usuario) {
		servicioLoginDao.update(usuario);
		
	}
	
	@Override
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
