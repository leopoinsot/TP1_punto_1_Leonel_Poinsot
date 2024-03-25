package ar.edu.unrn.accesos;

import ar.edu.unrn.modelo.Inscripcion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

public class InscripcionDAOJDBC implements InscripcionDAO {
	private Properties properties;

	@Override
	public void create(Inscripcion inscripcion) {
		// Proporciona los detalles de conexión a la base de datos
		String url = "jdbc:mysql://localhost:3306/sistema_de_concursos";
		String username = "LeonelAriel";
		String password = "villa2015";

		try {
			// Establece la conexión a la base de datos
			Connection conn = DriverManager.getConnection(url, username, password);

			// Prepara la sentencia SQL para la inserción
			PreparedStatement statement = conn.prepareStatement("INSERT INTO inscripciones(fecha, hora, dni_Participante, codigoDenominacion_Concurso) VALUES (?, ?, ?, ?)");

			// Establece los valores de los parámetros en la sentencia SQL
			statement.setDate(1, java.sql.Date.valueOf(inscripcion.obtenerFecha()));
			statement.setTime(2, java.sql.Time.valueOf(inscripcion.obtenerHora()));
			statement.setString(3, inscripcion.obtenerDniParticipante());
			statement.setString(4, inscripcion.obtenerCodigoDenominacionConcurso());

			// Ejecuta la inserción
			statement.executeUpdate();

			// Cierra la conexión y la declaración
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error al conectar con la base de datos", e);
		}
	}

	@Override
	public void update(Inscripcion inscripcion) {

	}

	@Override
	public void remove(Inscripcion inscripcion) {

	}

	@Override
	public Inscripcion find(String codigoDenominacion, String dni, LocalDate fecha) {
		return null;
	}

	@Override
	public List<Inscripcion> findAll() {
		return null;
	}
}
