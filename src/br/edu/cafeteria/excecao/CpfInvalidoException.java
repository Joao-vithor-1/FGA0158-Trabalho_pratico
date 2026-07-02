package br.edu.cafeteria.excecao;

public class CpfInvalidoException extends Exception{
	private final String cpf;
	public CpfInvalidoException(String cpf, String erro) {
				super(erro);
				
				this.cpf = cpf;
			}
	public String getCpf() {
		return cpf;  
	}
}
