package Modelo;

public class Cliente_vip extends Cliente{

	public Cliente_vip(String nome, String cpf, int saldo_xp) {
		super(nome, cpf, saldo_xp);
	}
	@Override
	public void cadastrar_xp(float dinheiro) {
		int n = 2;
		saldo_xp = ((int) dinheiro) *n;
	}

}
