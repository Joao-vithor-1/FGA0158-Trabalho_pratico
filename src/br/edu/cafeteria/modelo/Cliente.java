package br.edu.cafeteria.modelo;
import br.edu.cafeteria.excecao.CpfInvalidoException;
import br.edu.cafeteria.servico.VerificadorCpf;
public class Cliente  {
	protected final String nome;
	protected final String cpf;
	protected int saldoXp;
	
	
	public Cliente(String nome,String cpf, int saldoXp) throws CpfInvalidoException{
		this.nome = nome;
		this.saldoXp = saldoXp; 
		try {
		cpf = VerificadorCpf.verficadorCpf(cpf);
		}
		catch (CpfInvalidoException e) {
			cpf = null;
			System.out.println("Erro " + e.getLocalizedMessage());
			System.out.println("O cpf errado "+ e.getCpf());
		}
		this.cpf = cpf;
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
