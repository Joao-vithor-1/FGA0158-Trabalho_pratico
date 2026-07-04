package br.edu.cafeteria.servico;
import br.edu.cafeteria.excecao.ClienteVipException;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.modelo.Cliente;
import br.edu.cafeteria.modelo.Cliente_vip;
public class VerficarPontos {
	
	public  static void verificarPontos(Cliente cliente,int pontosNecessarios) throws ClienteVipException,PontosInsuficientesException{
		if(cliente == null) {
			throw new ClienteVipException(cliente,"Cliente não tem cadastro");
		}
		if(cliente instanceof Cliente_vip) {
			if(cliente.getCpf() == null) {
				throw new ClienteVipException(cliente,"Cliente com cadastro ou CPF invalido");
				
			}
			else if(cliente.getSaldoXp() >= pontosNecessarios) {
				((Cliente_vip) cliente).resgatarPontos(pontosNecessarios);
				return;
			}
			else {
				throw new PontosInsuficientesException(pontosNecessarios, cliente.getSaldoXp());
			}
				}
		else {
			throw new ClienteVipException(cliente,"Cliente não é vip : ");
		}
		
	}
}
