package br.edu.cafeteria.servico;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.modelo.*;

public class Venda  implements Promocao{
	final private Cliente cliente;
	final private Atendente atendente;
	final private Pedido pedido;
	
	//cliente nao cadastrado
	public Venda(Atendente atendente, Pedido pedido) {
		this.atendente = atendente;
		cliente = null;
		this.pedido = pedido;
	}
	//cliente cadastrado
	public Venda(Atendente atendente, Cliente cliente,Pedido pedido) {
		this.atendente = atendente;
		this.cliente = cliente;
		this.pedido = pedido;
	}
	
	@Override
	public float descontoDiaGeek(float totalBebida) {

		return totalBebida * 0.10f;
	}

	@Override
	public int descontoClienteVip(float totalPedido, int pontosXp) throws PontosInsuficientesException{

		int pontosNecessarios = (int) totalPedido * 10;
		
		if (pontosXp < pontosNecessarios) {
			throw new PontosInsuficientesException(pontosNecessarios, pontosXp);
		}
		return pontosNecessarios;
	}

	@Override
	public int descontoPersonalizado(float totalBebida, float totalComida, float desconto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
