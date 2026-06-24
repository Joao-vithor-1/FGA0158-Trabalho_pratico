package Modelo;

public class Cliente_standard extends Cliente{

	public Cliente_standard(String nome, String cpf, int saldo_xp) {
		super(nome, cpf, saldo_xp);
		
	}
	@Override
	public void cadastrar_xp(float dinheiro) {
		int n = 1;
		saldo_xp = ((int) dinheiro) * n;
	}
	
	
	
	
}

		
