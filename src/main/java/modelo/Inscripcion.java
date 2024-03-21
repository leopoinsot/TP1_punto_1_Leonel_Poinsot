package modelo;

import excepciones.FechaCierreInscripcionFinalizadaException;

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

	public static void inscribirAEn(Participante participante, Concurso concurso) throws FechaCierreInscripcionFinalizadaException {
		LocalDate fechaActual = LocalDate.now();
		if (concurso.fechaSeEncuentraDentroDelPeriodoInscripcion(fechaActual)) {
			LocalTime horaActual = LocalTime.now();
			if (concurso.pasoUnDiaDesdeLaAperturaInscripcion()) {
				Inscripcion inscripcion = new Inscripcion(fechaActual, horaActual, participante, concurso);
				Puntaje puntaje = new Puntaje(10, concurso, participante);
				concurso.agregarUna(inscripcion);
				concurso.agregarUn(puntaje);
			} else {
				Inscripcion inscripcion = new Inscripcion(fechaActual, horaActual, participante, concurso);
				Puntaje puntaje = new Puntaje(0, concurso, participante);
				concurso.agregarUna(inscripcion);
				concurso.agregarUn(puntaje);
			}
		} else {
			throw new FechaCierreInscripcionFinalizadaException("La fecha de cierre de inscripcion ah finalizado");
		}
	}
}
