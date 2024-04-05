package ar.edu.unrn.api;

public interface ServicioEmail {
	public void enviarEmail(String destinatarioEmail, String asunto, String tema);
}
