package Modelo;

public class Comida extends Produto{
	int tempo_preparo;
	final boolean glutem;
	final boolean vegano;
	public Comida(String nome_produto, int qtd_produto, float preco_base,int tempo_preparo,boolean glutem,boolean vegano) {
		this.tempo_preparo = tempo_preparo;
		this.glutem = glutem;
		this.vegano = vegano;
		
		super(nome_produto, qtd_produto, preco_base);
	}

}
