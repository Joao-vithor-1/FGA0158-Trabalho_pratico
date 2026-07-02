package br.edu.cafeteria.excecao;

public class PontosInsuficientesException extends Exception {
	
	private final float pontosNecessarios;
	private final float pontosDisponiveis;
	
	public PontosInsuficientesException(float pontosNecessarios, float pontosDisponiveis) {
		super("Pontos de XP insuficientes: necessario " + pontosNecessarios + ", disponivel " + pontosDisponiveis);
		this.pontosNecessarios = pontosNecessarios;
		this.pontosDisponiveis = pontosDisponiveis;
	}

	public float getPontosNecessarios() {
		return pontosNecessarios;
	}
	
	public float getPontosDisponiveis() {
		return pontosDisponiveis;
	}
}
