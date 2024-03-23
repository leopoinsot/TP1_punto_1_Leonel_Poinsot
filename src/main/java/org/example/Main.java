package org.example;

import excepciones.FechaCierreInscripcionFinalizadaException;
import excepciones.PuntajeNoExisteException;
import modelo.Concurso;
import modelo.Inscripcion;
import modelo.Participante;
import modelo.RegistroInscripcion;
import persistencia.RegistrarInscripcionDisco;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
	public static void main(String[] args) throws IOException {

		//-----------------Inscribir participante iniciada la fecha de cierre de inscripcion--------------------
		var registroInscripcion = new RegistrarInscripcionDisco();
		var concurso = new Concurso("objetos2", LocalDate.now(), LocalDate.now().plusDays(3));
		var participante = new Participante("45260989", "Leonel", "Poinsot");
		Inscripcion.inscribirAEn(participante, concurso, registroInscripcion);
		concurso.obtenerCantidadPuntosDeUn(participante);

		//-----------------Inscribir participante finalizada la fecha de cierre de inscripcion--------------------
		var concursonNuevo = new Concurso("objetos1", LocalDate.now().minusDays(7), LocalDate.now().minusDays(1));

		Inscripcion.inscribirAEn(participante, concursonNuevo, registroInscripcion);
	}
}
