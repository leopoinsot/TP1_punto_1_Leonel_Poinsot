package ar.edu.unrn.accesos;

import ar.edu.unrn.modelo.Inscripcion;

import java.time.LocalDate;
import java.util.List;

public interface InscripcionDAO {
	void create(Inscripcion inscripcion);

	void update(Inscripcion inscripcion);

	void remove(Inscripcion inscripcion);

	Inscripcion find(String codigoDenominacion, String dni, LocalDate fecha);

	List<Inscripcion> findAll();
}
