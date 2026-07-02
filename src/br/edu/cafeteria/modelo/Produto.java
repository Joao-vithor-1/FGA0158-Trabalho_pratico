package br.edu.cafeteria.modelo;

public class Produto {
    private final int codigoProduto;
    private final String nomeProduto;
    private int qtdProduto;
    private float precoBase;
    private static int tamanhoLista = 0;

    public Produto(String nomeProduto, int qtdProduto, float precoBase) {
        this.codigoProduto = tamanhoLista;
        tamanhoLista++;
        this.nomeProduto = nomeProduto;
        this.qtdProduto = qtdProduto;
        this.precoBase = precoBase;
    }
    
    public int getQtdProduto() {
        return qtdProduto;
    }
    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }
    public float getPrecoBase() {
        return precoBase;
    }
    public void setPrecoBase(float precoBase) {
        this.precoBase = precoBase;
    }
    public int getCodigoProduto() {
        return codigoProduto;
    }
    public String getNomeProduto() {
        return nomeProduto;
    }
}
