package modelo;

import excepciones.FechaCierreInscripcionFinalizadaException;
import excepciones.PuntajeNoExisteException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InscripcionTest {

	@Test
	void inscribirAEn() {
		// Caso de prueba 1: Un participante se inscribe en un concurso
		var concurso = new Concurso("objetos2", LocalDate.now().minusDays(2), LocalDate.now().plusDays(3));

		var participante = new Participante("45260989", "Leonel", "Poinsot");

		Inscripcion.inscribirAEn(participante, concurso);

		// Verificación de que el participante se ha inscrito correctamente
		assertEquals(1, concurso.obtenerCantidadInscriptos());
	}

	@Test
	void inscribirPrimerDia() throws PuntajeNoExisteException {
		// Caso de prueba 2: Un participante se inscribe en un concurso el primer día de abierta la inscripción
		var concurso = new Concurso("objetos2", LocalDate.now(), LocalDate.now().plusDays(3));

		var participante = new Participante("45260989", "Leonel", "Poinsot");

		Inscripcion.inscribirAEn(participante, concurso);

		// Verificación de que el participante ha ganado 10 puntos por inscribirse el primer día
		assertEquals(10, concurso.obtenerCantidadPuntosDeUn(participante));
	}

	@Test
	void inscribirFueraDeRango() {
		// Caso de prueba 3: Un participante intenta inscribirse fuera del rango de inscripción
		var concurso = new Concurso("objetos2", LocalDate.now().minusDays(5), LocalDate.now().minusDays(2));

		var participante = new Participante("45260989", "Leonel", "Poinsot");

		// Verificación de que se lanza una excepción cuando el participante intenta inscribirse fuera del rango
		assertThrows(FechaCierreInscripcionFinalizadaException.class, () -> {
			Inscripcion.inscribirAEn(participante, concurso);
		});
	}
}