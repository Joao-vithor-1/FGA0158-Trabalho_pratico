package servico;

public interface Promocao {

	public float descontoDiaGeek( float totalBebida );
	
	public int descontoClienteVip( float totalPedido, int pontosXp );
	
	public 	int descontoPersonalizado( float totalPedido, float desconto);
}
