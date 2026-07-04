package br.edu.cafeteria.modelo;
import br.edu.cafeteria.excecao.ClienteVipException;
import br.edu.cafeteria.excecao.CpfInvalidoException;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.servico.Venda;
import br.edu.cafeteria.excecao.EstaNaListaExceptionProduto;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
public class Debug_deletar_depois {
	public static void main(String[] args) throws CpfInvalidoException,EstaNaListaExceptionProduto, PontosInsuficientesException, EstoqueInsuficienteException, ClienteVipException {
		Cliente cliente_st;
		Cliente cliente_vip;
		Lista_cliente lista_cliente;
		Comida comida;
		Bebida bebida;
		ItemPedido lista_pedido;
		// teste  lista cliente
		
		cliente_st = new Cliente_standard("nome1", "012.345.123-20", 0);
		cliente_vip = new Cliente_vip("nome2","012.342.212-30", 15);
		Cliente_vip cliente_t = new Cliente_vip("nome3","123",0);
		System.out.println(cliente_t.getCpf());
		lista_cliente = new Lista_cliente();
		lista_cliente.adicionarCliente(cliente_st);
		lista_cliente.adicionarCliente(cliente_vip);
		
		System.out.println(lista_cliente.buscaClienteVip("012.345.123-20")); 
		System.out.println(lista_cliente.buscarCliente("012.342.212-30"));
		System.out.println(lista_cliente.buscaClienteVip("012.345.123-20"));
		System.out.println(lista_cliente.buscaClienteVip("012.342.212-30"));
		//System.out.println(lista_cliente.buscaClienteVip(null));
		System.out.println(lista_cliente.buscaClienteVip("23"));
		System.out.println(lista_cliente.buscarCliente("23"));
				//teste item pedido
		System.out.println("teste lista produto");
		comida = new Comida("Comida", 2, 12, 4, false, false);
		bebida = new Bebida("Bebida", "P", 3, 4, 5);
		lista_pedido = new ItemPedido();
		
		lista_pedido.adicionarProduto(bebida);
		//lista_pedido.adicionarProduto(comida,20); // programa crasha quando executa essa linha
		lista_pedido.adicionarProduto(comida);// nao é pra colocar na lista
		lista_pedido.adicionarProduto(bebida,20);// nao é pra colocar na lista
		System.out.println(lista_pedido.quantidadeProdutos());
		
		lista_pedido.imprimirCardapio();
		// teste atedente
		System.out.println("teste atedente");
		Atendente adente1 = new Atendente("123-345-123-22", "nome 1");
		Atendente adente2 = new Atendente("123-423-13-52", "nome 2");
		System.out.println(adente1.getNome());
		System.out.println(adente2.getNome());
		//teste pedido
		Pedido pedido = new Pedido(lista_pedido);
		pedido.adicionarProduto(bebida);
		pedido.adicionarProduto(comida);
		Comida comida_2 = new Comida("comida 2",4,23,12,true,false);
		pedido.adicionarProduto(comida_2);
		//teste venda
		Venda venda = pedido.finalizarPedido(adente1,cliente_t);
		Venda venda2 = pedido.finalizarPedido(adente2);
		Venda venda_cliente_vip = pedido.finalizarPedido(adente1,cliente_vip);
		Venda venda_cliente = pedido.finalizarPedido(adente1,cliente_st);
		//venda.descontoClienteVip(12, 34); crash por erro de pontos, e sem verificaçao cliente_vip
		System.out.println(venda_cliente_vip.finalizarVenda(true, true));
		System.out.println(cliente_vip.getSaldoXp());
		System.out.println(venda_cliente.finalizarVenda(true, true));
		System.out.println(cliente_st.saldoXp);
		System.out.println(venda2.finalizarVenda(false, true));
		System.out.println(venda.finalizarVenda(true, true));
		
		
	}
}
