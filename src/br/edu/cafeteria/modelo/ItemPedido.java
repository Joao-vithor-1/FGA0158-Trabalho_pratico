package br.edu.cafeteria.modelo;

import java.util.ArrayList;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.EstaNaListaExceptionProduto;
import br.edu.cafeteria.servico.VerificaDuplicidadeProduto;
import br.edu.cafeteria.servico.VerificadorEstoque;

public class ItemPedido {

    private ArrayList<Produto> lista = new ArrayList<>();
    private ArrayList<Integer> quantidades = new ArrayList<>();
    
    	
    public void adicionarProduto(Produto produto, int quantidadeDesejada) throws EstaNaListaExceptionProduto, EstoqueInsuficienteException{
    	Produto produto_verificado = VerificaDuplicidadeProduto.verificaDuplicidadeProduto(this, produto);
        quantidadeDesejada = VerificadorEstoque.verificadorEstoque(quantidadeDesejada, produto_verificado);

        lista.add(produto);
        quantidades.add(quantidadeDesejada);
	}

    public void adicionarProduto(Produto produto) throws EstaNaListaExceptionProduto, EstoqueInsuficienteException {
    	adicionarProduto(produto, 1);
    } 
    
    
    
    
    public void removerProduto(Produto produto) {
    	int index = lista.indexOf(produto);
    	if (index != -1) {
            lista.remove(index);
            quantidades.remove(index);
    	}
    }
    
    public int getCodigoProduto(int i) {
    	return lista.get(i).getCodigoProduto();
    }
    
    /*
    public ArrayList<Produto> getLista() {
       return lista;
    }*/
    
   /* public int getQuantidade(int i) {
    	// adicionar exeçoes depois
    	return quantidades.get(i);
    }*/
    
    
    protected float somaComida() {
    	float total = 0;
    	for(int i = 0;i<lista.size();i++) {
    		if(lista.get(i) instanceof Comida) {
    			total+=lista.get(i).getPrecoBase()*quantidades.get(i);
    		}
    	}
    	return total;

    }
    
    protected float somaBebida() {
    	float total = 0;
    	for(int i = 0;i<lista.size();i++) {
    		if(lista.get(i) instanceof Bebida) {
    			total+=lista.get(i).getPrecoBase()*quantidades.get(i);
    		}
    	}
    	return total;

    }

    
    private void imprimirComida() {
    		System.out.println("Quantidade...COMIDA : PRECO");
    		for(int i = 0;i<lista.size();i++) {
    		if(lista.get(i) instanceof Comida) {
    			System.out.println(quantidades.get(i)+"..."+lista.get(i).getNomeProduto() +" :  R$" + lista.get(i).getPrecoBase());
    		}
    	}
    }
    private void imprimirBebida() {
    	System.out.println("Quantidade...BEBIDA :  PRECO");
		for(int i = 0;i<lista.size();i++) {
		if(lista.get(i) instanceof Bebida) {
			System.out.println(quantidades.get(i)+"..."+lista.get(i).getNomeProduto() +" :  R$" + lista.get(i).getPrecoBase());
		}
      }
		
    }
    
    public void imprimirCardapio() {
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
        quantidades.clear();
    }
    
    protected void baixaEstoque() throws EstoqueInsuficienteException{
    	for (int i = 0; i < lista.size(); i++) {
            Produto produto = lista.get(i);
            int quantidadeVendida = quantidades.get(i);
            
            produto.reduzirEstoque(quantidadeVendida);
        }
    }
}
	
