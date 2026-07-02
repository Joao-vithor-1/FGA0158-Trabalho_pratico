package br.edu.cafeteria.modelo;
import java.util.ArrayList;

import br.edu.cafeteria.excecao.CpfInvalidoException;
import br.edu.cafeteria.servico.VerificadorCpf;

public class Lista_cliente {
	private ArrayList<Cliente> lista;
	public Lista_cliente() {
		lista = new ArrayList<>();
	}
	public void adicionarCliente(Cliente cliente) {
		lista.add(cliente);
	}
	//obs pode usar nome tambem ja que ambos sao string
	public boolean buscarCliente(String cpf) throws CpfInvalidoException{
		try{cpf = VerificadorCpf.verficadorCpf(cpf);
		}
		catch (CpfInvalidoException e) {
			System.out.println("Erro " + e.getLocalizedMessage());
			System.out.println("O cpf errado "+ e.getCpf());
		}

		for(int i = 0;i<lista.size();i++) {
			if(cpf.equals(lista.get(i).getCpf())) {
				return true;
			}
		}
		return false;
		
		
	}
	//so pra retorna o index
	private int BuscarCliente(String cpf) {
		try {
			String cpfverificado = VerificadorCpf.verficadorCpf(cpf);
			for(int i = 0;i<lista.size();i++) {
				if(cpfverificado.equals(lista.get(i).getCpf())) {
					return i;
				}
			}
			return -1;
			}
			catch (CpfInvalidoException e) {
				
				System.out.println("CPF invalido");
			}
			return -1;
		}

	
	
	public void removerCliente(Cliente cliente) {
		lista.remove(cliente);
	}
	// sobrecarga de metodo
	public void removerCliente(String cpf) {
		int remover = BuscarCliente(cpf);
		if(remover!=-1) {
			lista.remove(remover);
		}
	}
	
	public boolean buscaClienteVip(String cpf) throws CpfInvalidoException{
		try{cpf = VerificadorCpf.verficadorCpf(cpf);
		}
		catch (CpfInvalidoException e) {
			System.out.println("Erro " + e.getLocalizedMessage());
			System.out.println("O cpf errado "+ e.getCpf());
		}
		
	int Buscar = BuscarCliente(cpf);
	if(Buscar!=-1) {
		return(lista.get(Buscar) instanceof Cliente_vip);
		}
		
		return false;
	}
	
}
