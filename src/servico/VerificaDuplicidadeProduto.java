package servico;
import excecao.EstaNaListaExceptionProduto;
import Modelo.Produto;
import Modelo.ItemPedido;
public class VerificaDuplicidadeProduto {
	
	static public Produto verificaDuplicidadeProduto(ItemPedido lista, Produto produto) throws EstaNaListaExceptionProduto{
		System.out.println("this runned");
		for(int i=0;i<lista.quantidadeProdutos();i++) {
			System.out.println(produto.getCodigoProduto());
			System.out.println(lista.getCodicoProduto(i));
			if(produto.getCodigoProduto()==lista.getCodicoProduto(i)) {
				throw new EstaNaListaExceptionProduto(produto,lista,"Item ja esta na lista");
			}
		}
		return produto;
	}
	
}
