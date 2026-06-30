package servico;
import excecao.EstaNaListaExceptionProduto;
import Modelo.Produto;
import Modelo.ItemPedido;
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
