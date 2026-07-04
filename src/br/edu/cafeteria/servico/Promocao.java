package br.edu.cafeteria.servico;

import br.edu.cafeteria.excecao.PontosInsuficientesException;

public interface Promocao {

	public float descontoDiaGeek( float totalBebida );
	
	public int descontoClienteVip( float totalPedido, int pontosXp ) throws PontosInsuficientesException;
	
	public 	int descontoPersonalizado( float totalComida, float totalBebida, float percentualComida, float percentualBebida);
}
