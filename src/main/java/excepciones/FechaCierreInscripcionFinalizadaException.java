package excepciones;

import javax.management.RuntimeMBeanException;

public class FechaCierreInscripcionFinalizadaException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String mensaje;

	public FechaCierreInscripcionFinalizadaException() {

	}

	public FechaCierreInscripcionFinalizadaException(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMessage() {
		return mensaje;
	}
}
