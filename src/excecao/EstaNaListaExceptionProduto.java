package excecao;
import Modelo.Produto;
import Modelo.ItemPedido;
import java.util.ArrayList;

public class EstaNaListaExceptionProduto extends Exception{
	private Produto produto;
	private ItemPedido lista;
	//tentar colocar depois
	 public  EstaNaListaExceptionProduto(Produto produto,ItemPedido lista,String erro ) {
		super(erro);
		this.produto = produto;
		this.lista = lista;
	}
	
}
