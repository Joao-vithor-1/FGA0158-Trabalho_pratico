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
    
    public Produto getProduto(int i) {
    	// adicionar exeçoes depois
    	return lista.get(i);
    }
    
    
    protected float somaComida() {
    	float total = 0;
    	for(int i = 0;i<lista.size();i++) {
    		if(lista.get(i) instanceof Comida) {
    			total+=lista.get(i).getPrecoBase()*lista.get(i).getQtdProduto();
    		}
    	}
    	return total;

    }
    
    protected float somaBebida() {
    	float total = 0;
    	for(int i = 0;i<lista.size();i++) {
    		if(lista.get(i) instanceof Bebida) {
    			total+=lista.get(i).getPrecoBase()*lista.get(i).getQtdProduto();
    		}
    	}
    	return total;

    }

    
    private void imprimirComida() {
    		System.out.println("Index...COMIDA : PRECO");
    		for(int i = 0;i<lista.size();i++) {
    		if(lista.get(i) instanceof Comida) {
    			System.out.println(i+"..."+lista.get(i).getNomeProduto() +" :  R$" + lista.get(i).getPrecoBase());
    		}
    	}
    }
    private void imprimirBebida() {
    	System.out.println("Index...BEBIDA :  PRECO");
		for(int i = 0;i<lista.size();i++) {
		if(lista.get(i) instanceof Bebida) {
			System.out.println(i+"..."+lista.get(i).getNomeProduto() +" :  R$" + lista.get(i).getPrecoBase());
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
