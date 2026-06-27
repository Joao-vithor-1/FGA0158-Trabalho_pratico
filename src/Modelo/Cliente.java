package Modelo;
import servico.VerificadorCpf;
import excecao.CpfInvalidoException;
public class Cliente  {
	protected final String nome;
	protected final String cpf;
	protected int saldoXp;
	
	
	public Cliente(String nome,String cpf, int saldoXp) throws CpfInvalidoException{
		this.nome = nome;
		this.cpf = VerificadorCpf.verficadorCpf(cpf);
		this.saldoXp = saldoXp; 
	}
	public  void cadastrarXp(float dinheiro) {
		int n = 1;
		saldoXp += ((int) dinheiro) * n;
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
