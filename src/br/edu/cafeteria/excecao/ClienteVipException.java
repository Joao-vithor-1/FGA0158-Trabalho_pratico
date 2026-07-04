package br.edu.cafeteria.excecao;
import br.edu.cafeteria.modelo.Cliente;
public class ClienteVipException extends Exception{
	final Cliente cliente;
	public ClienteVipException(Cliente cliente,String erro) {
		super(erro);
		this.cliente = cliente;
	}
	public Cliente getCliente() {
		return cliente;
	}
}
