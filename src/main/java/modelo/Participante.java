package modelo;

import java.util.Objects;

public class Participante {
	private String dni;
	private String nombre;
	private String apellido;

	public Participante(String dni, String nombre, String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public String obtenerDni() {
		return this.dni;
	}
}
