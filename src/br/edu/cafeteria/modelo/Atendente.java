package br.edu.cafeteria.modelo;
import br.edu.cafeteria.excecao.CpfInvalidoException;
import br.edu.cafeteria.servico.VerificadorCpf;
public class Atendente {

	private String cpf;
	private String nome;
	
	public Atendente( String cpf, String nome) throws CpfInvalidoException{
		
		try {
			cpf = VerificadorCpf.verficadorCpf(cpf);
			this.cpf = cpf;
			this.nome = nome;
			}
			catch (CpfInvalidoException e) {
				cpf = null;
				System.out.println("Erro " + e.getLocalizedMessage());
				System.out.println("O cpf errado "+ e.getCpf());
				
			}
		
		
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
