package br.edu.cafeteria.excecao;

public class EstoqueInsuficienteException extends Exception {
      
    private final String nomeProduto;
    private final int quantidadeSolicitada;
    private final int quantidadeDisponivel;

    public EstoqueInsuficienteException(String nomeProduto, int quantidadeSolicitada, int quantidadeDisponivel) {
       
        super("Estoque insuficiente para " + nomeProduto + ". Quantidade solicitada: " + quantidadeSolicitada + ", Quantidade disponível: " + quantidadeDisponivel);
        this.nomeProduto = nomeProduto;
        this.quantidadeSolicitada = quantidadeSolicitada;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

}