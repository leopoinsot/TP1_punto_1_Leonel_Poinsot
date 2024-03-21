package modelo;

import excepciones.PuntajeNoExisteException;

import java.time.LocalDate;
import java.util.*;

public class Concurso {
	private String codigoDenominacion;
	private LocalDate fechaAperturaInscripcion;
	private LocalDate fechaCierreInscripcion;
	private List<Inscripcion> listadoInscriptos;
	private List<Puntaje> listadoPuntajes;

	public Concurso(String codigoDenominacion, LocalDate fechaAperturaInscripcion, LocalDate fechaCierreInscripcion) {
		super();
		this.codigoDenominacion = codigoDenominacion;
		this.fechaAperturaInscripcion = fechaAperturaInscripcion;
		this.fechaCierreInscripcion = fechaCierreInscripcion;
		this.listadoInscriptos = new ArrayList();
		this.listadoPuntajes = new ArrayList();
	}

	public boolean fechaSeEncuentraDentroDelPeriodoInscripcion(LocalDate fecha) {
		boolean fechaSeEncuentraEnRango = false;
		if ((fecha.equals(fechaAperturaInscripcion) || fecha.isAfter(fechaAperturaInscripcion)) &&
				(fecha.equals(fechaCierreInscripcion) || fecha.isBefore(fechaCierreInscripcion))) {
			fechaSeEncuentraEnRango = true;
		}
		return fechaSeEncuentraEnRango;
	}

	public boolean pasoUnDiaDesdeLaAperturaInscripcion() {
		boolean pasoUnDia = false;
		LocalDate fechaActual = LocalDate.now();
		LocalDate DiaDespuesDeLaAperturaInscripcion = fechaAperturaInscripcion.plusDays(1);
		if ((fechaActual.isBefore(DiaDespuesDeLaAperturaInscripcion) || fechaActual.equals(DiaDespuesDeLaAperturaInscripcion)) && (fechaActual.isAfter(fechaAperturaInscripcion) || fechaActual.equals(fechaAperturaInscripcion))) {
			pasoUnDia = true;
		}
		return pasoUnDia;
	}

	public int obtenerCantidadPuntosDeUn(Participante participante) throws PuntajeNoExisteException {
		int puntos = 0;
		for (Puntaje puntaje : listadoPuntajes) {
			if (puntaje.obtenerParticipante().equals(participante)) {
				puntos = puntaje.obtenerPuntos();
				break;
			} else {
				throw new PuntajeNoExisteException("El puntaje no existe para este participante");
			}
		}
		return puntos;
	}

	public void agregarUna(Inscripcion inscripcion) {
		listadoInscriptos.add(inscripcion);
	}

	public void agregarUn(Puntaje puntaje) {
		listadoPuntajes.add(puntaje);
	}

	public int obtenerCantidadInscriptos() {
		return listadoInscriptos.size();
	}
}
