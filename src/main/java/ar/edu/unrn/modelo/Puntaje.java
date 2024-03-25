package ar.edu.unrn.modelo;

public class Puntaje {
	private int id;
	private int puntos;
	private Concurso concurso;
	private Participante participante;

	public Puntaje(int id, int puntos, Concurso concurso, Participante participante) {
		super();
		this.id = id;
		this.puntos = puntos;
		this.concurso = concurso;
		this.participante = participante;
	}

	public Puntaje(int puntos, Concurso concurso, Participante participante) {
		super();
		this.puntos = puntos;
		this.concurso = concurso;
		this.participante = participante;
	}

	public Participante obtenerParticipante() {
		return participante;
	}

	public int obtenerPuntos() {
		return puntos;
	}

}
