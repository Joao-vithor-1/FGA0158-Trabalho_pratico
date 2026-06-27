package Modelo;
import servico.*;
public class Pedido {
	private ItemPedido itemPedido;
	private final int comanda;
	private  float totalComida;
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
		totalPedido = totalComida+totalBebida;
		return totalPedido;
	}
	//arrumar uma maneira de fazer isso melhor
	public void adicionarProduto(Produto produto, int qtd_desejada) {
		produto.setQtdProduto(qtd_desejada);
		itemPedido.adicionarProduto(produto);
	}
	
	public void adiconarProduto(Produto produto) {
		produto.setQtdProduto(1);
		itemPedido.adicionarProduto(produto);
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
