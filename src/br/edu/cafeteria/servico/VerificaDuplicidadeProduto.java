package br.edu.cafeteria.servico;
import br.edu.cafeteria.excecao.EstaNaListaExceptionProduto;
import br.edu.cafeteria.modelo.ItemPedido;
import br.edu.cafeteria.modelo.Produto;
public class VerificaDuplicidadeProduto {
	
	static public Produto verificaDuplicidadeProduto(ItemPedido lista, Produto produto) throws EstaNaListaExceptionProduto{
		for(int i=0;i<lista.quantidadeProdutos();i++) {
			if(produto.getCodigoProduto()==lista.getCodigoProduto(i)) {
				throw new EstaNaListaExceptionProduto(produto,lista,"Item ja esta na lista");
			}
		}
		return produto;
	}
	
}
