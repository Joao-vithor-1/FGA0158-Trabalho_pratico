package br.edu.cafeteria.modelo;
import br.edu.cafeteria.excecao.CpfInvalidoException;
public class ClienteStandart extends Cliente{

	public ClienteStandart(String nome, String cpf, int saldoXp) throws CpfInvalidoException{
		super(nome, cpf, saldoXp);
		
		
	}
	@Override
	public void cadastrarXp(float dinheiro) {
		int n = 1;
		saldoXp += ((int) dinheiro) * n;
	}
	
	
	
	
}

		
