package br.edu.cafeteria.app;

import java.util.ArrayList;

import br.edu.cafeteria.modelo.Cliente;
import br.edu.cafeteria.modelo.Cliente_standard;
import br.edu.cafeteria.modelo.Cliente_vip;
import br.edu.cafeteria.modelo.Comida;
import br.edu.cafeteria.modelo.Produto;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		System.out.println("=== Cafeteria Byte & Brew ===");
		cadastrarProdutosIniciais();
		cadastrarClientesIniciais();
		
	}
	
	private static void cadastrarProdutosIniciais() {
		System.out.println("[Sistema] Carregando cardápio de produtos...");
	}
	private static void cadastrarClientesIniciais() {
		System.out.println("[Sistema] Carregando banco de clientes...");
	}

}
