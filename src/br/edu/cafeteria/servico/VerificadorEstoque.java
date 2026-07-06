package br.edu.cafeteria.servico;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.modelo.Produto;

public class VerificadorEstoque {
    public static int verificadorEstoque(int quantidadeDesejada, Produto produto)
            throws EstoqueInsuficienteException {

        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }

        if (quantidadeDesejada <= 0) {
            throw new IllegalArgumentException("A quantidade desejada deve ser maior que zero.");
        }

        if (quantidadeDesejada > produto.getQtdProduto()) {
            throw new EstoqueInsuficienteException(
                    produto.getNomeProduto(),
                    quantidadeDesejada,
                    produto.getQtdProduto()
            );
        }

        return quantidadeDesejada;
    }
}
