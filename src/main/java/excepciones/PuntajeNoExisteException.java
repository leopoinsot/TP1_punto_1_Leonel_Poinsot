package excepciones;

import javax.management.RuntimeMBeanException;

public class PuntajeNoExisteException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String mensaje;

	public PuntajeNoExisteException() {

	}

	public PuntajeNoExisteException(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMessage() {
		return mensaje;
	}
}
