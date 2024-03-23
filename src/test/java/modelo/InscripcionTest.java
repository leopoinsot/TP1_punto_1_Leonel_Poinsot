package modelo;

import excepciones.FechaCierreInscripcionFinalizadaException;
import excepciones.PuntajeNoExisteException;
import org.junit.jupiter.api.Test;
import persistencia.RegistrarInscripcionDisco;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InscripcionTest {

	@Test
	void inscribirAEn() throws IOException {
		// Caso de prueba 1: Un participante se inscribe en un concurso
		var concurso = new Concurso("objetos2", LocalDate.now().minusDays(2), LocalDate.now().plusDays(3));

		var participante = new Participante("45260989", "Leonel", "Poinsot");

		var registro = new RegistroInscripcion() {
			boolean seLlamo = false;

			@Override
			public void registrar(String fecha, String hora, String dni, String codigoDenominacion) throws IOException {
				seLlamo = true;
			}

			public boolean isSeLlamo() {
				return seLlamo;
			}
		};

		Inscripcion.inscribirAEn(participante, concurso, registro);

		// Verificación de que el participante se ha inscrito correctamente
		assertEquals(1, concurso.obtenerCantidadInscriptos());
		assertTrue(registro.isSeLlamo());
	}

	@Test
	void inscribirPrimerDia() throws PuntajeNoExisteException, IOException {
		// Caso de prueba 2: Un participante se inscribe en un concurso el primer día de abierta la inscripción
		var concurso = new Concurso("objetos2", LocalDate.now(), LocalDate.now().plusDays(3));

		var participante = new Participante("45260989", "Leonel", "Poinsot");

		var registro = new RegistroInscripcion() {
			boolean seLlamo = false;

			@Override
			public void registrar(String fecha, String hora, String dni, String codigoDenominacion) throws IOException {
				seLlamo = true;
			}

			public boolean isSeLlamo() {
				return seLlamo;
			}
		};

		Inscripcion.inscribirAEn(participante, concurso, registro);

		// Verificación de que el participante ha ganado 10 puntos por inscribirse el primer día
		assertEquals(10, concurso.obtenerCantidadPuntosDeUn(participante));
		assertTrue(registro.isSeLlamo());
	}

	@Test
	void inscribirFueraDeRango() {
		// Caso de prueba 3: Un participante intenta inscribirse fuera del rango de inscripción
		var concurso = new Concurso("objetos2", LocalDate.now().minusDays(5), LocalDate.now().minusDays(2));

		var participante = new Participante("45260989", "Leonel", "Poinsot");

		var registro = new RegistroInscripcion() {
			boolean seLlamo = false;

			@Override
			public void registrar(String fecha, String hora, String dni, String codigoDenominacion) throws IOException {
				seLlamo = true;
			}

			public boolean isSeLlamo() {
				return seLlamo;
			}
		};

		// Verificación de que se lanza una excepción cuando el participante intenta inscribirse fuera del rango
		assertThrows(FechaCierreInscripcionFinalizadaException.class, () -> {
			Inscripcion.inscribirAEn(participante, concurso, registro);
		});
		assertFalse(registro.isSeLlamo());
	}
}