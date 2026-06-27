package Modelo;
import excecao.CpfInvalidoException;
import excecao.EstaNaListaExceptionProduto;
public class Debug_deletar_depois {
	public static void main(String[] args) throws CpfInvalidoException,EstaNaListaExceptionProduto{
		Cliente cliente_st;
		Cliente cliente_vip;
		Lista_cliente lista_cliente;
		Comida comida;
		Bebida bebida;
		ItemPedido lista_pedido;
		// teste  lista cliente
		
		cliente_st = new Cliente_standard("nome1", "012.345.123-20", 0);
		cliente_vip = new Cliente_vip("nome2","012.342.212-30", 0);
		Cliente_vip cliente_t = new Cliente_vip("nome3","123",0);
		System.out.println(cliente_t.getCpf());// nao printa
		lista_cliente = new Lista_cliente();
		lista_cliente.adicionarCliente(cliente_st);
		lista_cliente.adicionarCliente(cliente_vip);
		
		System.out.println(lista_cliente.buscaClienteVip("012.345.1233-20")); 
		System.out.println(lista_cliente.buscarCliente("012.342.212-30"));
		System.out.println(lista_cliente.buscaClienteVip("012.345.123-20"));
		System.out.println(lista_cliente.buscaClienteVip("012.342.212-30"));
		//System.out.println(lista_cliente.buscaClienteVip(null));
		System.out.println(lista_cliente.buscaClienteVip("23"));
		System.out.println(lista_cliente.buscarCliente("23"));
				//teste item pedido
		
		comida = new Comida("Comida", 2, 0, 4, false, false);
		bebida = new Bebida("Bebida", "P", 3, 4, 5);
		lista_pedido = new ItemPedido();
		
		lista_pedido.adicionarProduto(bebida);
		lista_pedido.adicionarProduto(comida,20);
		lista_pedido.adicionarProduto(comida);// nao é pra colocar na lista
		lista_pedido.adicionarProduto(bebida,20);// nao é pra colocar na lista
		System.out.println(lista_pedido.quantidadeProdutos());
		
		lista_pedido.ImprimirCardapio();
		// teste atedente
		
	}
}
