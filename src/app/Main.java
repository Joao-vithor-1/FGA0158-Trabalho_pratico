package app;

import java.util.ArrayList;
import Modelo.Cliente;
import Modelo.Cliente_standard;
import Modelo.Cliente_vip;
import Modelo.Produto;
import Modelo.Comida;

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
