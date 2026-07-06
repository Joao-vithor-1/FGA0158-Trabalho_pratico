package br.edu.cafeteria.modelo;
import br.edu.cafeteria.excecao.CpfInvalidoException;
public class ClienteStandard extends Cliente{

	public ClienteStandard(String nome, String cpf, int saldoXp) throws CpfInvalidoException{
		super(nome, cpf, saldoXp);
		
		
	}
	@Override
	public void cadastrarXp(float dinheiro) {
		int n = 1;
		saldoXp += ((int) dinheiro) * n;
	}
	
	
	
	
}

		
