package Modelo;
import excecao.CpfInvalidoException;
public class Cliente_standard extends Cliente{

	public Cliente_standard(String nome, String cpf, int saldoXp) throws CpfInvalidoException{
		super(nome, cpf, saldoXp);
		
		
	}
	@Override
	public void cadastrarXp(float dinheiro) {
		int n = 1;
		saldoXp += ((int) dinheiro) * n;
	}
	
	
	
	
}

		
