package Modelo;

public class Cliente {
	protected final String nome;
	protected final String cpf;
	protected int saldo_xp;
	
	
	
	public Cliente(String nome,String cpf, int saldo_xp) {
		this.nome = nome;
		this.cpf =  cpf;
		this.saldo_xp = saldo_xp;
		
	}
	public  void cadastrar_xp(float dinheiro) {
		int n = 0;
		saldo_xp = ((int) dinheiro) *n;
	}
	public String getCpf() {
		return cpf;
	}
	public int getSaldo_xp() {
		return saldo_xp;
	}
	
}
