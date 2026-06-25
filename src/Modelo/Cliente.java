package Modelo;

public class Cliente {
	protected final String nome;
	protected final String cpf;
	protected int saldoXp;
	
	
	
	public Cliente(String nome,String cpf, int saldoXp) {
		this.nome = nome;
		this.cpf =  cpf;
		this.saldoXp = saldoXp;
		
	}
	public  void cadastrarXp(float dinheiro) {
		int n = 0;
		saldoXp = ((int) dinheiro) *n;
	}
	public String getCpf() {
		return cpf;
	}
	public int getSaldoXp() {
		return saldoXp;
	}
	public String getNome() {
		return nome;
	}
}
