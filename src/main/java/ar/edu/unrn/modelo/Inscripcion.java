package ar.edu.unrn.modelo;

import ar.edu.unrn.api.Registro;
import ar.edu.unrn.api.ServicioEmail;
import ar.edu.unrn.excepciones.FechaCierreInscripcionFinalizadaException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Inscripcion {
	private LocalDate fecha;
	private LocalTime hora;
	private Participante participante;
	private Concurso concurso;

	public Inscripcion(LocalDate fecha, LocalTime hora, Participante participante, Concurso concurso) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.participante = participante;
		this.concurso = concurso;
	}

	public static void inscribirAEn(Participante participante, Concurso concurso, Registro registroInscripcion, ServicioEmail email) throws FechaCierreInscripcionFinalizadaException, IOException {
		LocalDate fechaActual = LocalDate.now();
		var horaActual = LocalTime.now();

		if (!concurso.fechaSeEncuentraDentroDelPeriodoInscripcion(fechaActual)) {
			throw new FechaCierreInscripcionFinalizadaException("La fecha de cierre de inscripcion ah finalizado");
		} else if (concurso.pasoUnDiaDesdeLaAperturaInscripcion()) {
			var inscripcion = new Inscripcion(fechaActual, horaActual, participante, concurso);
			var puntaje = new Puntaje(10, concurso, participante);
			concurso.agregarUna(inscripcion);
			concurso.agregarUn(puntaje);
		} else {
			Inscripcion inscripcion = new Inscripcion(fechaActual, horaActual, participante, concurso);
			var puntaje = new Puntaje(0, concurso, participante);
			concurso.agregarUna(inscripcion);
			concurso.agregarUn(puntaje);
		}
		email.enviarEmail("leonrojopoinsot@gmail.com", "Practica 2 objetos II", "hola mundo");
		registroInscripcion.registrar(fechaActual, horaActual, participante.obtenerDni(), concurso.obtenerCodigoDenominacion());
	}

	public String obtenerDniParticipante() {
		String dniParticipante = this.participante.obtenerDni();
		return dniParticipante;
	}

	public String obtenerCodigoDenominacionConcurso() {
		String codigoDenominacionConcurso = this.concurso.obtenerCodigoDenominacion();
		return codigoDenominacionConcurso;
	}

	public LocalDate obtenerFecha() {
		LocalDate fecha = this.fecha;
		return fecha;
	}

	public LocalTime obtenerHora() {
		LocalTime hora = this.hora;
		return hora;
	}
}
