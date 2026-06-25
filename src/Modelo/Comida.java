package Modelo;

public class Comida extends Produto{

	private int tempoPreparo;
	private final boolean gluten;
	private final boolean vegano;
	
	public Comida(String nomeProduto, int qtdProduto, float precoBase,int tempoPreparo,boolean glutem,boolean vegano) {
		
		super(nomeProduto, qtdProduto, precoBase);
		this.tempoPreparo = tempoPreparo;
		this.gluten = glutem;
		this.vegano = vegano;
	}


    public int getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(int tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }
    
    public boolean getVegano() {
        return vegano;
    }

    public boolean getGluten() {
        return gluten;
    }



}
