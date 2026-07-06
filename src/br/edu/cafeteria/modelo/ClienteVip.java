package br.edu.cafeteria.modelo;
import br.edu.cafeteria.excecao.CpfInvalidoException;
public class ClienteVip extends Cliente{

	public ClienteVip(String nome, String cpf, int saldoXp) throws CpfInvalidoException{
		super(nome, cpf, saldoXp);
	}
	@Override
	public void cadastrarXp(float dinheiro) {
		
		int n = 2;
		saldoXp += ((int) dinheiro) * n;
	}
	
	public void resgatarPontos(int pontos) {
		saldoXp -= pontos;
	}
}
