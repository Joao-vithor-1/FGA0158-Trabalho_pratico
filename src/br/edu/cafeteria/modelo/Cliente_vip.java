package br.edu.cafeteria.modelo;
import br.edu.cafeteria.excecao.CpfInvalidoException;
public class Cliente_vip extends Cliente{

	public Cliente_vip(String nome, String cpf, int saldoXp) throws CpfInvalidoException{
		super(nome, cpf, saldoXp);
	}
	@Override
	public void cadastrarXp(float dinheiro) {
		int n = 2;
		saldoXp += ((int) dinheiro) * n;
	}

}
