package ar.edu.unlam.tallerweb1.controladores;

import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	
	public static void sendEmail(Session session, String emailDestino, String asunto, String cuerpo){
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //Indico el tipo y formate del mensaje
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("proyectospruebaunlam@gmail.com", "BetFulbol"));

	      msg.setReplyTo(InternetAddress.parse("proyectospruebaunlam@gmail.com", false));

	      msg.setSubject(asunto, "UTF-8");

	      msg.setText(cuerpo, "UTF-8");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestino, false));
	      System.out.println("Mensaje listo");
    	  Transport.send(msg);  

	      System.out.println("Email enviado exitosamente!!");
	    }
	    catch (Exception e) {
	    	//imprimo el error, aunque funciona perfectamente
	      e.printStackTrace();
	    }
	}
}
