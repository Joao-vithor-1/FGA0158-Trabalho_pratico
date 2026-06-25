package Modelo;

public class Comida extends Produto{

	int tempoPreparo;
	final boolean glutem;
	final boolean vegano;
	
	public Comida(String nomeProduto, int qtdProduto, float precoBase,int tempoPreparo,boolean glutem,boolean vegano) {
		
		super(nomeProduto, qtdProduto, precoBase);
		this.tempoPreparo = tempoPreparo;
		this.glutem = glutem;
		this.vegano = vegano;
	}

}
