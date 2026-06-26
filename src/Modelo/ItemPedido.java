package Modelo;

import java.util.ArrayList;


public class ItemPedido {

    protected ArrayList<Produto> lista;

    public ItemPedido() {
        lista = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        lista.add(produto);
    }

    public void removerProduto(Produto produto) {
        lista.remove(produto);
    }
    //porque tem um metodo get pra lista?
    public ArrayList<Produto> getLista() {
        return lista;
    }
    
    private void imprimirComida() {
    		System.out.println("COMIDA : PRECO");
    		for(int i = 0;i<lista.size();i++) {
    		if(lista.get(i) instanceof Comida) {
    			System.out.println(lista.get(i).getNomeProduto() +" :  R$" + lista.get(i).getPrecoBase());
    		}
    	}
    }
    private void imprimirBebida() {
    	System.out.println("BEBIDA :  PRECO");
		for(int i = 0;i<lista.size();i++) {
		if(lista.get(i) instanceof Bebida) {
			System.out.println(lista.get(i).getNomeProduto() +" :  R$" + lista.get(i).getPrecoBase());
		}
      }
		
    }
    
    public void ImprimirCardapio() {
    	System.out.println();
    	imprimirBebida();
    	System.out.println();
    	imprimirComida();
    }
    
    //o nome pode confudir com a quantidade de comida e bebida total
    //tipo se tenho bebida 1 com qtd 5 e comida 2 com qtd 7 achei que retornava 12
    public int quantidadeProdutos () {
        return lista.size();
    }

    public void limparLista() {
        lista.clear();
    }

}
