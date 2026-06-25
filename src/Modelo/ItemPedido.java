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

    public ArrayList<Produto> getLista() {
        return lista;
    }

    public int quantidadeProdutos () {
        return lista.size();
    }

    public void limparLista() {
        lista.clear();
    }

}
