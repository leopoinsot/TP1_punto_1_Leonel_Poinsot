package modelo;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public interface RegistroInscripcion {
	public void registrar(String fecha, String hora, String dni, String codigoDenominacion) throws IOException;

}
