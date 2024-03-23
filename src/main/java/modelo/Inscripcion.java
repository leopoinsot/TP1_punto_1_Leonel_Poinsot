package modelo;

import excepciones.FechaCierreInscripcionFinalizadaException;
import persistencia.RegistrarInscripcionDisco;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalTime;

public class Inscripcion {
	private LocalDate fecha;
	private LocalTime hora;
	private Participante participante;
	private Concurso concurso;

	public Inscripcion(LocalDate fecha, LocalTime hora, Participante participante, Concurso concurso, RegistroInscripcion registroInscripcion) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.participante = participante;
		this.concurso = concurso;
	}

	public Inscripcion(LocalDate fecha, LocalTime hora, Participante participante, Concurso concurso) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.participante = participante;
		this.concurso = concurso;
	}

	public static void inscribirAEn(Participante participante, Concurso concurso, RegistroInscripcion registroInscripcion) throws FechaCierreInscripcionFinalizadaException, IOException {
		LocalDate fechaActual = LocalDate.now();
		if (concurso.fechaSeEncuentraDentroDelPeriodoInscripcion(fechaActual)) {
			var horaActual = LocalTime.now();
			if (concurso.pasoUnDiaDesdeLaAperturaInscripcion()) {
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
			registroInscripcion.registrar(FormatoFecha.aplicarFormatoEuropeo(fechaActual), horaActual.toString(), participante.obtenerDni(), concurso.obtenerCodigoDenominacion());

		} else {
			throw new FechaCierreInscripcionFinalizadaException("La fecha de cierre de inscripcion ah finalizado");
		}
	}
}
