package Modelo;
import servico.VerificadorCpf;
import excecao.CpfInvalidoException;
public class Atendente {

	private String cpf;
	private String nome;
	
	public Atendente( String cpf, String nome) throws CpfInvalidoException{
		
		this.cpf = VerificadorCpf.verficadorCpf(cpf);
		this.nome = nome;
		
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
