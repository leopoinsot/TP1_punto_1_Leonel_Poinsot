package ar.edu.unrn.modelo;

import ar.edu.unrn.api.Email;
import ar.edu.unrn.api.PersistenceApi;
import ar.edu.unrn.excepciones.FechaCierreInscripcionFinalizadaException;
import ar.edu.unrn.excepciones.PuntajeNoExisteException;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class InscripcionTest {

	@Test
	void inscribirAEn() throws IOException, MessagingException {
		// Caso de prueba 1: Un participante se inscribe en un concurso
		var concurso = new Concurso("objetos2", LocalDate.now().minusDays(2), LocalDate.now().plusDays(3));

		var participante = new Participante("45260989", "Leonel", "Poinsot");

		var registro = new PersistenceApi() {
			boolean seLlamo = false;

			@Override
			public void registrar(LocalDate fecha, LocalTime hora, String dni, String codigoDenominacion) throws IOException {
				seLlamo = true;
			}

			public boolean isSeLlamo() {
				return seLlamo;
			}
		};

		var email = new Email("smtp.mailtrap.io", "2525", "1d8884f5484749", "ccb6e88c2f65a1") {
			private boolean seEnvioEmail = false;

			public void enviarEmail(String destinatarioEmail, String asunto, String tema) {
				seEnvioEmail = true;
			}

			public boolean seEnvioEmail() {
				return seEnvioEmail;
			}
		};

		Inscripcion.inscribirAEn(participante, concurso, registro, email);
		email.enviarEmail("leonrojopoinsot@gmail.com", "Practica 2 objetos II", "hola mundo");
		assertTrue(email.seEnvioEmail());
		// Verificación de que el participante se ha inscrito correctamente
		assertEquals(1, concurso.obtenerCantidadInscriptos());
		assertTrue(registro.isSeLlamo());
	}

	@Test
	void inscribirPrimerDia() throws PuntajeNoExisteException, IOException, MessagingException {
		// Caso de prueba 2: Un participante se inscribe en un concurso el primer día de abierta la inscripción
		var concurso = new Concurso("objetos2", LocalDate.now(), LocalDate.now().plusDays(3));

		var participante = new Participante("45260989", "Leonel", "Poinsot");

		var registro = new PersistenceApi() {
			boolean seLlamo = false;

			@Override
			public void registrar(LocalDate fecha, LocalTime hora, String dni, String codigoDenominacion) throws IOException {
				seLlamo = true;
			}

			public boolean isSeLlamo() {
				return seLlamo;
			}
		};

		var email = new Email("smtp.mailtrap.io", "2525", "1d8884f5484749", "ccb6e88c2f65a1") {
			private boolean seEnvioEmail = false;

			public void enviarEmail(String destinatarioEmail, String asunto, String tema) {
				seEnvioEmail = true;
			}

			public boolean seEnvioEmail() {
				return seEnvioEmail;
			}
		};
		Inscripcion.inscribirAEn(participante, concurso, registro, email);
		email.enviarEmail("leonrojopoinsot@gmail.com", "Practica 2 objetos II", "hola mundo");
		assertTrue(email.seEnvioEmail());

		// Verificación de que el participante ha ganado 10 puntos por inscribirse el primer día
		assertEquals(10, concurso.obtenerCantidadPuntosDeUn(participante));
		assertTrue(registro.isSeLlamo());
	}

	@Test
	void inscribirFueraDeRango() {
		// Caso de prueba 3: Un participante intenta inscribirse fuera del rango de inscripción
		var concurso = new Concurso("objetos2", LocalDate.now().minusDays(5), LocalDate.now().minusDays(2));

		var participante = new Participante("45260989", "Leonel", "Poinsot");

		var registro = new PersistenceApi() {
			boolean seLlamo = false;

			@Override
			public void registrar(LocalDate fecha, LocalTime hora, String dni, String codigoDenominacion) throws IOException {
				seLlamo = true;
			}

			public boolean isSeLlamo() {
				return seLlamo;
			}
		};
		var email = new Email("smtp.mailtrap.io", "2525", "1d8884f5484749", "ccb6e88c2f65a1") {
			private boolean seEnvioEmail = false;

			public void enviarEmail(String destinatarioEmail, String asunto, String tema) {
				seEnvioEmail = true;
			}

			public boolean seEnvioEmail() {
				return seEnvioEmail;
			}
		};
		// Verificación de que se lanza una excepción cuando el participante intenta inscribirse fuera del rango
		assertThrows(FechaCierreInscripcionFinalizadaException.class, () -> {
			Inscripcion.inscribirAEn(participante, concurso, registro, email);
		});
		assertFalse(registro.isSeLlamo());
	}
}