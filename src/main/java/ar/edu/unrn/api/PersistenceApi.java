package ar.edu.unrn.api;

import ar.edu.unrn.accesos.*;
import ar.edu.unrn.modelo.Concurso;
import ar.edu.unrn.modelo.Inscripcion;
import ar.edu.unrn.modelo.Participante;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class PersistenceApi implements Registro {
	private InscripcionDAO inscripcionDAO = new InscripcionDAOJDBC();

	@Override
	public void registrar(LocalDate fecha, LocalTime hora, String dni, String codigoDenominacion) throws IOException {
		inscripcionDAO.create(new Inscripcion(fecha, hora, new Participante(dni), new Concurso(codigoDenominacion)));
	}
}
