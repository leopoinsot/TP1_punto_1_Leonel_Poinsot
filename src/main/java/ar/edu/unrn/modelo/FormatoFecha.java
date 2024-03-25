package ar.edu.unrn.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatoFecha {
	private LocalDate fecha;

	public FormatoFecha(LocalDate fecha) {

	}

	public static String aplicarFormatoEuropeo(LocalDate fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return fecha.format(formatter);
	}
}
