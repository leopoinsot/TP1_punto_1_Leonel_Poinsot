package ar.edu.unrn.modelo;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import jakarta.mail.Session;;

public class Email {
	private Properties properties;
	private Session session;

	public Email(String ruta) throws IOException {
		this.properties = new Properties();
		loadConfig(ruta);
	}

	public void loadConfig(String ruta) throws IOException {
		InputStream is = new FileInputStream(ruta);
		this.properties.load(is);
	}

	public void enviarEmail(String asunto, String mensaje, String correo) throws MessagingException {
		MimeMessage contenedor = new MimeMessage(session);
		contenedor.setFrom(new InternetAddress((String) this.properties.get("mail.smtp.user")));
		contenedor.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(correo)));
		contenedor.setSubject(asunto);
		contenedor.setText(mensaje);
		Transport t = session.getTransport("smtp");
		t.connect((String) this.properties.get("mail.smtp.user"), (String) this.properties.get("mail.smtp.password"));
		t.sendMessage(contenedor, contenedor.getAllRecipients());
	}
}
