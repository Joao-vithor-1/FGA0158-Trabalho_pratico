package br.edu.cafeteria.servico;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.excecao.ClienteVipException;
import br.edu.cafeteria.modelo.*;
import br.edu.cafeteria.servico.VerficarPontos;
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

	
	private float descontoClienteVip(float totalPedido) throws PontosInsuficientesException,ClienteVipException{

		int pontosNecessarios = (int) (totalPedido * 10);
		try {
			VerficarPontos.verificarPontos(cliente, pontosNecessarios);
			
		}
		catch(PontosInsuficientesException e){
			System.out.println(e.getLocalizedMessage());
			return totalPedido;
		}
		catch(ClienteVipException c){
			if(c.getCliente() !=null && cliente.getCpf()!=null) {
				System.out.println(c.getLocalizedMessage()+ cliente.getCpf());
				return totalPedido;
			}
			System.out.println(c.getLocalizedMessage());
			return totalPedido;
		}
		return 0;
		
	}

	@Override
	public int descontoPersonalizado(float totalComida, float totalBebida, float percentualComida, float percentualBebida) {
		
		if(percentualComida < 0 || percentualComida > 100 || percentualBebida < 0 || percentualBebida > 100) {
			
			throw new IllegalArgumentException("O percentual de desconto precisa estar estar entre 0 e 100");
		}
		float descontoComida = totalComida * (percentualComida / 100f);
		float descontoBebida = totalBebida * (percentualBebida / 100f);
		
		return (int) (descontoComida + descontoBebida);
	}
	
	public float finalizarVenda(boolean diaEventoGeek, boolean pagarComXp) throws PontosInsuficientesException, ClienteVipException {
		
		float totalComida = pedido.getTotalComida();
		float totalBebida = pedido.getTotalBebida();
		
		float descontoBebida;
		if(diaEventoGeek) {
			
			descontoBebida = descontoDiaGeek(totalBebida);
		}
		else {
			descontoBebida = 0;
		}
		float totalFinal = (totalComida + totalBebida) - descontoBebida;
		if(pagarComXp) {
		totalFinal = descontoClienteVip(totalFinal);
		}
		/*if(pagarComXp && cliente instanceof Cliente_vip) {
			
			Cliente_vip clienteVip = (Cliente_vip) cliente;
			
			int pontosDebitados = descontoClienteVip(totalFinal, clienteVip.getSaldoXp());
			clienteVip.resgatarPontos(pontosDebitados);
			totalFinal = 0;
		}
		else if (cliente != null) {
			
			cliente.cadastrarXp(totalFinal);
		}*/
		if(cliente!=null) {
			cliente.cadastrarXp(totalFinal);
		}
		pedido.confirmarEstoque();
		
		return totalFinal;
	}
}
