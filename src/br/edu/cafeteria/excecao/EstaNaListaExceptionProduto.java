package br.edu.cafeteria.excecao;
import java.util.ArrayList;

import br.edu.cafeteria.modelo.ItemPedido;
import br.edu.cafeteria.modelo.Produto;

public class EstaNaListaExceptionProduto extends Exception{
	private Produto produto;
	private ItemPedido lista;
	//tentar colocar depois
	 public  EstaNaListaExceptionProduto(Produto produto,ItemPedido lista,String erro ) {
		super(erro);
		this.produto = produto;
		this.lista = lista;
	}
	 public Produto getProduto() {
		 return produto;
	 }
	
}
