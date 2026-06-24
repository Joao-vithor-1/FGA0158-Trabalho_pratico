package Modelo;

public class Produto {
	final int  codico_produto;
	final String nome_produto;
	int qtd_produto;
	float preco_base;
	static int tamanho_lista = 0;
	public Produto(String nome_produto,int qtd_produto, float preco_base) {
		this.codico_produto = tamanho_lista;
		tamanho_lista++;
		this.nome_produto = nome_produto;
		this.qtd_produto = qtd_produto;
		this.preco_base = preco_base;
		
	}
	public int getQtd_produto() {
		return qtd_produto;
	}
	public void setQtd_produto(int qtd_produto) {
		this.qtd_produto = qtd_produto;
	}
	public float getPreco_base() {
		return preco_base;
	}
	public void setPreco_base(float preco_base) {
		this.preco_base = preco_base;
	}
	public int getCodico_produto() {
		return codico_produto;
	}
	public String getNome_produto() {
		return nome_produto;
	}
	
}
