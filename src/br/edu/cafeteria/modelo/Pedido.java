package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.EstaNaListaExceptionProduto;
import br.edu.cafeteria.servico.*;

public class Pedido {
	private ItemPedido itemPedido;
	private final int comanda;
	protected  float totalComida;
	private float totalBebida;
	private float totalPedido;
	private static int tamanho_lista;
	
	public Pedido() {
		comanda = tamanho_lista;
		tamanho_lista++;
		itemPedido = new ItemPedido();
		
		totalBebida = 0;
		totalComida = 0;
		totalPedido = 0;
	}
	public Pedido(ItemPedido itemPedido) {
		comanda = tamanho_lista;
		tamanho_lista++;
		this.itemPedido = itemPedido;
		totalBebida = 0;
		totalComida = 0;
		totalPedido = 0;
	}
	
	public float getTotalComida() {
		totalComida = itemPedido.somaComida();
		return totalComida;
	}
	
	public float getTotalBebida() {
		totalBebida = itemPedido.somaBebida();
		return totalBebida;
	}
	
	public float getTotalPedido() {

		return getTotalComida() + getTotalBebida();
	}

	public void adicionarProduto(Produto produto, int qtd_desejada) throws EstaNaListaExceptionProduto, EstoqueInsuficienteException {
		
		itemPedido.adicionarProduto(produto, qtd_desejada);
	}
	
	public void adicionarProduto(Produto produto) throws EstaNaListaExceptionProduto, EstoqueInsuficienteException {
		
		itemPedido.adicionarProduto(produto);
	}
	
	public void confirmarEstoque() {
		itemPedido.baixaEstoque();
	}
	
	// sem cliente
	public Venda finalizarPedido(Atendente atedente) {
		getTotalComida();
		getTotalBebida();
		getTotalPedido();
		Venda venda = new Venda(atedente,this);
		return venda;
	}
	// com cliente
	public Venda finalizarPedido(Atendente atedente,Cliente cliente) {
		getTotalComida();
		getTotalBebida();
		getTotalPedido();
		Venda  venda = new Venda(atedente,cliente,this);
		return venda;
	}
}
