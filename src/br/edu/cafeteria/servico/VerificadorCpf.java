package br.edu.cafeteria.servico;
import br.edu.cafeteria.excecao.CpfInvalidoException;

public  class VerificadorCpf{
	
	public static String verficadorCpf(String cpf) throws CpfInvalidoException{
		String cpfVerificado = "";
		for(int i =0;i<cpf.length();i++) {
			char temp_char = cpf.charAt(i);
			if(Character.isDigit(temp_char)) {
				cpfVerificado+=temp_char;
			}
		}
		if(cpfVerificado.length()!=11) {
			throw new CpfInvalidoException(cpfVerificado,"CPF invalido");
		}
		return cpfVerificado;
	}
	
}
