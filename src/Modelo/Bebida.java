
package Modelo;

public class Bebida extends Produto {

    private String tamanho;
    private float densidadeCafeina; 

    public Bebida(String nomeProduto, String tamanho, float densidadeCafeina,
                  int qtdProduto, float precoBase) {

        super(nomeProduto, qtdProduto, precoBase);
        this.tamanho = tamanho;
        this.densidadeCafeina = densidadeCafeina;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public float getDensidadeCafeina() {
        return densidadeCafeina;
    }

    public void setDensidadeCafeina(float densidadeCafeina) {
        this.densidadeCafeina = densidadeCafeina;
    }

    public int quantidadeCafeina() {

        int volumeML;

        switch (tamanho) { // eduardo. =-) estipulei valores comuns para pequeno medio e grande, confirmar dps

            case "pequeno":
                volumeML = 200;
                break;

            case "medio":
            case "médio":
                volumeML = 300;
                break;

            case "grande":
                volumeML = 500;
                break;

            default:
                return 0;
        }

       
        return (int) (densidadeCafeina * (volumeML / 1000.0f));
    }
}