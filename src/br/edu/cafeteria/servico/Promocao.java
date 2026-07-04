package br.edu.cafeteria.servico;

import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.excecao.ClienteVipException;

public interface Promocao {

	public float descontoDiaGeek( float totalBebida );
	
	public 	int descontoPersonalizado( float totalComida, float totalBebida, float percentualComida, float percentualBebida);
}
