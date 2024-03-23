package persistencia;

import modelo.FormatoFecha;
import modelo.RegistroInscripcion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegistrarInscripcionDisco implements RegistroInscripcion {
	@Override
	public void registrar(String fecha, String hora, String dni, String codigoDenominacion) throws IOException {
		String cadena = fecha + ", " + hora + ", " + dni + ", " + codigoDenominacion;
		Files.write(Paths.get("C:\\Users\\leonr\\OneDrive\\Escritorio\\inscripcion.txt"), cadena.getBytes(), StandardOpenOption.APPEND);
	}
}
