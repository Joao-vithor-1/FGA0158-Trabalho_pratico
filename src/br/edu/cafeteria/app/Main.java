
package br.edu.cafeteria.app;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

import br.edu.cafeteria.excecao.ClienteVipException;
import br.edu.cafeteria.excecao.CpfInvalidoException;
import br.edu.cafeteria.excecao.EstaNaListaExceptionProduto;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.modelo.Atendente;
import br.edu.cafeteria.modelo.Bebida;
import br.edu.cafeteria.modelo.Cliente;
import br.edu.cafeteria.modelo.ClienteStandard;
import br.edu.cafeteria.modelo.ClienteVip;
import br.edu.cafeteria.modelo.Comida;
import br.edu.cafeteria.modelo.ListaCliente;
import br.edu.cafeteria.modelo.Pedido;
import br.edu.cafeteria.modelo.Produto;
import br.edu.cafeteria.servico.Venda;
import br.edu.cafeteria.servico.VerificadorCpf;

public class Main {

    private static final Scanner entrada = new Scanner(System.in);

    private static final ArrayList<Produto> cardapio = new ArrayList<>();
    private static final ListaCliente clientes = new ListaCliente();
    private static final ArrayList<Atendente> atendentes = new ArrayList<>();
    private static final ArrayList<RegistroVenda> historicoVendas = new ArrayList<>();
    private static final Map<String, Integer> comprasPorCpf = new LinkedHashMap<>();

    private static int indiceAtendenteAtual = 0;
    private static int atendimentosFinalizados = 0;
    private static final Random sorteadorEventoGeek = new Random();


    private static final int COMPRAS_PARA_VIP = 5;


    private static final int INTERVALO_EVENTO_GEEK = 3;
    private static final int TAXA_XP_POR_REAL = 10;
    private static final float LIMITE_PAGAMENTO_XP_STANDARD = 0.50f;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        carregarDadosIniciais();
        menuPrincipal();

        entrada.close();
    }

    private static void carregarDadosIniciais() {
        carregarCardapioPreDefinido();
        carregarClientesIniciais();
        carregarAtendentesIniciais();
    }

    private static void carregarCardapioPreDefinido() {
                cardapio.add(new Bebida("Cafe do Programador", "grande", 320.0f, 25, 14.90f));
        cardapio.add(new Bebida("Pocao de Mana", "medio", 120.0f, 30, 12.50f));
        cardapio.add(new Bebida("Elixir Java", "medio", 180.0f, 22, 13.90f));
        cardapio.add(new Bebida("Latte do Hobbit", "grande", 95.0f, 20, 16.50f));
        cardapio.add(new Bebida("Cha da Elfa", "pequeno", 35.0f, 18, 9.90f));
        cardapio.add(new Bebida("Mocha Multiverso", "grande", 210.0f, 16, 18.00f));
        cardapio.add(new Bebida("Soda Arcana", "medio", 0.0f, 26, 11.90f));
        cardapio.add(new Bebida("Capuccino do Mestre", "medio", 150.0f, 20, 15.50f));
        cardapio.add(new Bebida("Expresso Dark Mode", "pequeno", 360.0f, 28, 10.00f));
        cardapio.add(new Bebida("Milkshake Pixelado", "grande", 20.0f, 14, 19.90f));
        cardapio.add(new Bebida("Cold Brew Cyberpunk", "grande", 260.0f, 18, 17.90f));
        cardapio.add(new Bebida("Suco de Estamina", "medio", 0.0f, 25, 10.90f));
        cardapio.add(new Bebida("Chocolate Quente de Valfenda", "grande", 25.0f, 12, 16.90f));
        cardapio.add(new Bebida("Energetico do Boss Final", "medio", 300.0f, 20, 15.90f));
        cardapio.add(new Bebida("Agua Mineral Cristalina", "pequeno", 0.0f, 40, 5.00f));

        cardapio.add(new Comida("Lembas Bread", 24, 9.90f, 8, true, false));
        cardapio.add(new Comida("Portal Cake", 18, 18.00f, 12, true, false));
        cardapio.add(new Comida("Cookies do Mestre Yoda", 30, 8.50f, 6, true, false));
        cardapio.add(new Comida("Sanduiche do Jogador", 16, 21.90f, 15, true, false));
        cardapio.add(new Comida("Torta de Mana", 12, 17.50f, 18, true, false));
        cardapio.add(new Comida("Brownie do Multiverso", 20, 12.90f, 10, true, false));
        cardapio.add(new Comida("Croissant Steampunk", 18, 13.90f, 9, true, false));
        cardapio.add(new Comida("Pao de Queijo Quantico", 35, 7.90f, 5, false, false));
        cardapio.add(new Comida("Salada Druida", 14, 19.90f, 11, false, true));
        cardapio.add(new Comida("Wrap Galactico", 16, 22.50f, 14, true, false));
        cardapio.add(new Comida("Quiche de Cogumelos Magicos", 10, 20.90f, 20, true, false));
        cardapio.add(new Comida("Muffin de Dragao", 22, 11.90f, 8, true, false));
        cardapio.add(new Comida("Panini da Guilda", 15, 23.90f, 16, true, false));
        cardapio.add(new Comida("Bolo Vegano Matrix", 10, 18.90f, 13, false, true));
        cardapio.add(new Comida("Batata Rustica do Anao", 25, 14.90f, 12, false, true));
    }

    private static void carregarClientesIniciais() {
        cadastrarClienteInicial("Arthur Dent", "11122233344");
        cadastrarClienteInicial("Bilbo Bolseiro", "12312312399");
        cadastrarClienteInicial("Ada Lovelace", "22233344455");
        cadastrarClienteInicial("Linus Torvalds", "33344455566");
    }

    private static void cadastrarClienteInicial(String nome, String cpf) {
        try {
            Cliente cliente = new ClienteStandard(nome, cpf, 0);
            clientes.adicionarCliente(cliente);
            comprasPorCpf.put(cliente.getCpf(), 0);
        } catch (CpfInvalidoException | IllegalArgumentException e) {
            System.out.println("[Aviso] Erro ao carregar cliente inicial: " + nome + " - " + e.getMessage());
        }
    }

    private static void carregarAtendentesIniciais() {
        adicionarAtendenteInicial("10120230344", "Marvin");
        adicionarAtendenteInicial("20230340455", "Leia");
        adicionarAtendenteInicial("30340450566", "Spock");
    }

    private static void adicionarAtendenteInicial(String cpf, String nome) {
        try {
            atendentes.add(new Atendente(cpf, nome));
        } catch (CpfInvalidoException e) {
            System.out.println("[Aviso] Atendente inicial invalido: " + nome);
        }
    }

    private static void menuPrincipal() {
        int opcao;

        do {
            limparTelaVisual();
            System.out.println("============================================================");
            System.out.println("                 BYTE & BREW - CAFETERIA GEEK              ");
            System.out.println("============================================================");
            System.out.println("1 - Fazer pedido no balcao");
            System.out.println("2 - Ver cardapio completo");
            System.out.println("3 - Consultar item do cardapio");
            System.out.println("4 - Programa de fidelidade / meu cadastro");
            System.out.println("5 - Consultar minha comanda");
            System.out.println("6 - Conhecer atendentes da cafeteria");
            System.out.println("0 - Sair da cafeteria");
            System.out.println("------------------------------------------------------------");
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    abrirAtendimentoBalcao();
                    break;
                case 2:
                    exibirCardapioParaCliente();
                    pausar();
                    break;
                case 3:
                    buscarProdutoMenu();
                    pausar();
                    break;
                case 4:
                    menuClientes();
                    break;
                case 5:
                    buscarVendaPorComanda();
                    pausar();
                    break;
                case 6:
                    exibirAtendentesParaCliente();
                    pausar();
                    break;
                case 0:
                    System.out.println("Obrigado pela visita. Volte sempre a Byte & Brew!");
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    pausar();
                    break;
            }
        } while (opcao != 0);
    }


    private static void abrirAtendimentoBalcao() {
        if (cardapio.isEmpty()) {
            System.out.println("Nao ha produtos no cardapio.");
            pausar();
            return;
        }

        Atendente atendente = proximoAtendente();
        Cliente cliente = identificarCliente();
        boolean diaEventoGeekAtendimento = sortearEventoGeekAtendimento();
        Map<Integer, ItemCarrinho> carrinho = new LinkedHashMap<>();

        int opcao;
        do {
            limparTelaVisual();
            System.out.println("============================================================");
            System.out.println("                    ATENDIMENTO NO BALCAO                  ");
            System.out.println("============================================================");
            System.out.println("Atendente: " + atendente.getNome());
            System.out.println("Cliente: " + nomeClienteOuCasual(cliente));
            System.out.println("Atendimento numero: " + numeroDoProximoAtendimento());
            System.out.println("Evento Geek hoje: " + simNao(diaEventoGeekAtendimento));
            if (diaEventoGeekAtendimento) {
                System.out.println("Promocao ativa: 10% de desconto sobre bebidas.");
            }
            System.out.println("------------------------------------------------------------");
            System.out.println("1 - Ver cardapio");
            System.out.println("2 - Adicionar item ao pedido");
            System.out.println("3 - Ver comanda atual");
            System.out.println("4 - Alterar quantidade de item");
            System.out.println("5 - Remover item");
            System.out.println("6 - Fechar pedido e pagar");
            System.out.println("0 - Cancelar atendimento");
            System.out.println("------------------------------------------------------------");
            mostrarResumoCarrinho(carrinho);
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    exibirCardapioParaCliente();
                    pausar();
                    break;
                case 2:
                    adicionarItemAoCarrinho(carrinho);
                    pausar();
                    break;
                case 3:
                    exibirComandaAtual(carrinho);
                    pausar();
                    break;
                case 4:
                    alterarQuantidadeCarrinho(carrinho);
                    pausar();
                    break;
                case 5:
                    removerItemCarrinho(carrinho);
                    pausar();
                    break;
                case 6:
                    if (fecharPedido(atendente, cliente, carrinho, diaEventoGeekAtendimento)) {
                        opcao = 0;
                    }
                    pausar();
                    break;
                case 0:
                    System.out.println("Atendimento cancelado.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    pausar();
                    break;
            }
        } while (opcao != 0);
    }

    private static Cliente identificarCliente() {
        System.out.println();
        System.out.println("Cliente possui cadastro no programa de fidelidade?");
        System.out.println("1 - Sim, informar CPF");
        System.out.println("2 - Nao, cliente casual");
        System.out.println("3 - Cadastrar agora no programa de fidelidade");
        int opcao = lerInteiro("Opcao: ");

        switch (opcao) {
            case 1:
                return buscarClienteParaVenda();
            case 3:
                return cadastrarClienteDuranteVenda();
            case 2:
            default:
                System.out.println("Venda seguira como cliente casual, sem XP.");
                return null;
        }
    }

    private static Cliente buscarClienteParaVenda() {
        try {
            String cpf = lerCpfValido("CPF do cliente: ");
            Cliente cliente = clientes.buscarClientePorCpf(cpf);

            if (cliente == null) {
                System.out.println("CPF nao cadastrado. A venda seguira como cliente casual.");
                return null;
            }

            System.out.println("Cliente encontrado: " + cliente.getNome());
            mostrarPainelFidelidade(cliente);
            return cliente;
        } catch (CpfInvalidoException e) {
            System.out.println("CPF invalido. A venda seguira como cliente casual.");
            return null;
        }
    }

    private static Cliente cadastrarClienteDuranteVenda() {
        try {
            Cliente cliente = lerDadosClienteStandard();
            clientes.adicionarCliente(cliente);
            comprasPorCpf.put(cliente.getCpf(), 0);
            System.out.println("Cliente cadastrado como Standard com 0 XP e associado a venda.");
            System.out.println("Ele sera elevado a VIP automaticamente apos " + COMPRAS_PARA_VIP + " compras registradas.");
            return cliente;
        } catch (CpfInvalidoException | IllegalArgumentException e) {
            System.out.println("Nao foi possivel cadastrar cliente: " + e.getMessage());
            System.out.println("A venda seguira como cliente casual.");
            return null;
        }
    }

    private static void adicionarItemAoCarrinho(Map<Integer, ItemCarrinho> carrinho) {
        exibirCardapioParaCliente();
        int codigo = lerInteiro("Codigo do item desejado: ");
        Produto produto = buscarProdutoPorCodigo(codigo);

        if (produto == null) {
            System.out.println("Produto nao encontrado.");
            return;
        }

        if (produto.getQtdProduto() <= 0) {
            System.out.println("Produto sem estoque no momento.");
            return;
        }

        int quantidade = lerInteiroMinimo("Quantidade: ", 1);
        int quantidadeAtual = carrinho.containsKey(codigo) ? carrinho.get(codigo).quantidade : 0;
        int novaQuantidade = quantidadeAtual + quantidade;

        if (novaQuantidade > produto.getQtdProduto()) {
            System.out.println("Quantidade indisponivel.");
            System.out.println("Estoque atual: " + produto.getQtdProduto());
            System.out.println("Ja na comanda: " + quantidadeAtual);
            return;
        }

        carrinho.put(codigo, new ItemCarrinho(produto, novaQuantidade));
        System.out.println("Item adicionado a comanda: " + produto.getNomeProduto() + " x" + novaQuantidade);
    }

    private static void alterarQuantidadeCarrinho(Map<Integer, ItemCarrinho> carrinho) {
        if (carrinho.isEmpty()) {
            System.out.println("Comanda vazia.");
            return;
        }

        exibirComandaAtual(carrinho);
        int codigo = lerInteiro("Codigo do item: ");
        ItemCarrinho item = carrinho.get(codigo);

        if (item == null) {
            System.out.println("Item nao encontrado na comanda.");
            return;
        }

        int novaQuantidade = lerInteiroMinimo("Nova quantidade (0 remove): ", 0);

        if (novaQuantidade == 0) {
            carrinho.remove(codigo);
            System.out.println("Item removido.");
            return;
        }

        if (novaQuantidade > item.produto.getQtdProduto()) {
            System.out.println("Quantidade indisponivel. Estoque atual: " + item.produto.getQtdProduto());
            return;
        }

        item.quantidade = novaQuantidade;
        System.out.println("Quantidade atualizada.");
    }

    private static void removerItemCarrinho(Map<Integer, ItemCarrinho> carrinho) {
        if (carrinho.isEmpty()) {
            System.out.println("Comanda vazia.");
            return;
        }

        exibirComandaAtual(carrinho);
        int codigo = lerInteiro("Codigo do item a remover: ");

        if (carrinho.remove(codigo) != null) {
            System.out.println("Item removido da comanda.");
        } else {
            System.out.println("Item nao encontrado.");
        }
    }

    private static boolean fecharPedido(Atendente atendente, Cliente cliente, Map<Integer, ItemCarrinho> carrinho, boolean diaEventoGeek) {
        if (carrinho.isEmpty()) {
            System.out.println("Nao e possivel fechar pedido vazio.");
            return false;
        }

        exibirComandaAtual(carrinho);

        float totalBruto = calcularTotalCarrinho(carrinho);
        float descontoGeek = diaEventoGeek ? calcularTotalBebidasCarrinho(carrinho) * 0.10f : 0.0f;
        float totalPrevisto = totalBruto - descontoGeek;

        System.out.println();
        System.out.println("Resumo antes do pagamento:");
        System.out.println("Atendimento numero: " + numeroDoProximoAtendimento());
        System.out.println("Dia de Evento Geek: " + simNao(diaEventoGeek));
        if (diaEventoGeek) {
            System.out.printf("Desconto automatico em bebidas: R$ %.2f%n", descontoGeek);
        }
        System.out.printf("Total previsto: R$ %.2f%n", totalPrevisto);
        mostrarPainelFidelidade(cliente);

        boolean confirmar = lerSimNao("Confirmar fechamento do pedido? (s/n): ");

        if (!confirmar) {
            System.out.println("Fechamento cancelado.");
            return false;
        }

        PagamentoXp pagamentoXp = decidirPagamentoComXp(cliente, totalPrevisto);
        int xpAntes = cliente == null ? 0 : cliente.getSaldoXp();

        Pedido pedido = new Pedido();

        if (!passarCarrinhoParaPedido(pedido, carrinho)) {
            System.out.println("Pedido nao foi finalizado.");
            return false;
        }

        Venda venda = (cliente == null)
                ? pedido.finalizarPedido(atendente)
                : pedido.finalizarPedido(atendente, cliente);

        try {

            float totalSemXp = venda.finalizarVenda(diaEventoGeek, false);
            float totalFinal = Math.max(0.0f, totalSemXp - pagamentoXp.descontoEmReais);

            if (cliente != null) {
                int xpDesejado = xpAntes - pagamentoXp.pontosUsados + calcularXpGanhos(cliente, totalFinal);
                cliente = substituirClienteNoCadastro(cliente, xpDesejado);
            }

            RegistroVenda registro = new RegistroVenda(venda, totalSemXp, totalFinal, diaEventoGeek, pagamentoXp, carrinho);
            historicoVendas.add(registro);

            atendimentosFinalizados++;

            if (cliente != null) {
                registrarCompraCliente(cliente);
                avaliarPromocaoParaVip(cliente);
                Cliente clienteAposVenda = buscarClientePorCpfSeguro(cliente.getCpf());
                int xpDepois = clienteAposVenda == null ? cliente.getSaldoXp() : clienteAposVenda.getSaldoXp();

                System.out.println();
                System.out.println("XP do cliente:");
                System.out.println("Antes da compra: " + xpAntes + " XP");
                if (pagamentoXp.usouXp) {
                    System.out.println("Usado no pagamento: " + pagamentoXp.pontosUsados + " XP");
                }
                System.out.println("Ganho nesta compra: " + calcularXpGanhos(cliente, totalFinal) + " XP");
                System.out.println("Depois da compra: " + xpDepois + " XP");
                System.out.println("Compras registradas: " + contarComprasCliente(cliente.getCpf()));
            }

            imprimirNotaFiscal(registro);
            return true;
        } catch (PontosInsuficientesException e) {
            System.out.println("Pontos insuficientes para pagar com XP.");
            System.out.println(e.getMessage());
        } catch (ClienteVipException e) {
            System.out.println("Pagamento com XP nao permitido.");
            System.out.println(e.getMessage());
        } catch (EstoqueInsuficienteException e) {
            System.out.println("Estoque insuficiente ao finalizar pedido.");
            System.out.println(e.getMessage());
        } catch (CpfInvalidoException | IllegalArgumentException e) {
            System.out.println("Erro ao atualizar XP do cliente.");
            System.out.println(e.getMessage());
        }

        return false;
    }

    private static PagamentoXp decidirPagamentoComXp(Cliente cliente, float totalPrevisto) {
        if (cliente == null) {
            System.out.println("Cliente casual nao acumula XP e nao pode pagar com XP.");
            return PagamentoXp.semXp();
        }

        if (totalPrevisto <= 0.0f) {
            return PagamentoXp.semXp();
        }

        if (cliente.getSaldoXp() <= 0) {
            System.out.println("Cliente cadastrado sem saldo XP para usar neste pedido.");
            return PagamentoXp.semXp();
        }

        System.out.println();
        System.out.println("Pagamento com XP:");
        System.out.println("Tipo de cliente: " + tipoCliente(cliente));
        System.out.println("Saldo atual: " + cliente.getSaldoXp() + " XP");
        System.out.printf("Conversao: %d XP = R$ 1,00%n", TAXA_XP_POR_REAL);

        if (cliente instanceof ClienteVip) {
            return decidirPagamentoXpVip(cliente, totalPrevisto);
        }

        return decidirPagamentoXpStandard(cliente, totalPrevisto);
    }

    private static PagamentoXp decidirPagamentoXpStandard(Cliente cliente, float totalPrevisto) {
        float limiteDesconto = totalPrevisto * LIMITE_PAGAMENTO_XP_STANDARD;
        float valorDisponivelEmXp = cliente.getSaldoXp() / (float) TAXA_XP_POR_REAL;
        float maximoPermitido = Math.min(limiteDesconto, valorDisponivelEmXp);

        if (maximoPermitido <= 0.0f) {
            System.out.println("Saldo XP insuficiente para abater este pedido.");
            return PagamentoXp.semXp();
        }

        System.out.printf("Cliente Standard pode pagar ate 50%% do pedido com XP: R$ %.2f.%n", limiteDesconto);
        System.out.printf("Pelo saldo atual, pode usar ate: R$ %.2f.%n", maximoPermitido);

        while (true) {
            System.out.println("1 - Usar o maior valor possivel com XP");
            System.out.println("2 - Escolher um valor menor para pagar com XP");
            System.out.println("0 - Nao usar XP neste pedido");
            int opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    return criarPagamentoXpPorValor(maximoPermitido, "Standard - abatimento de ate 50%", totalPrevisto);
                case 2:
                    return escolherValorPersonalizadoXp(maximoPermitido, totalPrevisto, "Standard - valor escolhido pelo cliente");
                case 0:
                    return PagamentoXp.semXp();
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }
    }

    private static PagamentoXp decidirPagamentoXpVip(Cliente cliente, float totalPrevisto) {
        int pontosParaPedidoInteiro = calcularPontosNecessarios(totalPrevisto);
        int pontosParaMetade = calcularPontosNecessarios(totalPrevisto * 0.50f);

        System.out.println("Cliente VIP pode pagar metade ou o pedido inteiro com XP.");
        System.out.printf("Pedido inteiro: R$ %.2f -> %d XP.%n", totalPrevisto, pontosParaPedidoInteiro);
        System.out.printf("Metade do pedido: R$ %.2f -> %d XP.%n", totalPrevisto * 0.50f, pontosParaMetade);

        while (true) {
            System.out.println("1 - Pagar o pedido inteiro com XP");
            System.out.println("2 - Pagar metade do pedido com XP");
            System.out.println("3 - Escolher outro valor para pagar com XP");
            System.out.println("0 - Nao usar XP neste pedido");
            int opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    if (cliente.getSaldoXp() >= pontosParaPedidoInteiro) {
                        return new PagamentoXp(true, pontosParaPedidoInteiro, totalPrevisto, "VIP - pedido inteiro pago com XP");
                    }
                    System.out.println("Saldo insuficiente para pagar o pedido inteiro com XP.");
                    break;
                case 2:
                    if (cliente.getSaldoXp() >= pontosParaMetade) {
                        return new PagamentoXp(true, pontosParaMetade, totalPrevisto * 0.50f, "VIP - metade do pedido paga com XP");
                    }
                    System.out.println("Saldo insuficiente para pagar metade do pedido com XP.");
                    break;
                case 3:
                    float valorDisponivelEmXp = cliente.getSaldoXp() / (float) TAXA_XP_POR_REAL;
                    float maximoPermitido = Math.min(totalPrevisto, valorDisponivelEmXp);
                    return escolherValorPersonalizadoXp(maximoPermitido, totalPrevisto, "VIP - valor escolhido pelo cliente");
                case 0:
                    return PagamentoXp.semXp();
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }
    }

    private static PagamentoXp escolherValorPersonalizadoXp(float maximoPermitido, float totalPrevisto, String descricao) {
        if (maximoPermitido <= 0.0f) {
            System.out.println("Nao ha saldo XP disponivel para esta opcao.");
            return PagamentoXp.semXp();
        }

        System.out.printf("Valor maximo permitido nesta opcao: R$ %.2f.%n", maximoPermitido);
        float valorEscolhido = lerFloatMinimoMaximo("Valor em reais a pagar com XP: R$ ", 0.0f, maximoPermitido);

        if (valorEscolhido <= 0.0f) {
            return PagamentoXp.semXp();
        }

        return criarPagamentoXpPorValor(valorEscolhido, descricao, totalPrevisto);
    }

    private static PagamentoXp criarPagamentoXpPorValor(float valorEmReais, String descricao, float totalPrevisto) {
        float desconto = Math.min(valorEmReais, totalPrevisto);
        int pontosNecessarios = calcularPontosNecessarios(desconto);
        return new PagamentoXp(true, pontosNecessarios, desconto, descricao);
    }

    private static int calcularPontosNecessarios(float total) {
        return (int) Math.ceil(total * TAXA_XP_POR_REAL);
    }

    private static int calcularXpGanhos(Cliente cliente, float valorPagoEmDinheiro) {
        if (cliente == null) {
            return 0;
        }

        int multiplicador = cliente instanceof ClienteVip ? 2 : 1;
        return ((int) valorPagoEmDinheiro) * multiplicador;
    }

    private static Cliente substituirClienteNoCadastro(Cliente cliente, int novoSaldoXp) throws CpfInvalidoException {
        int saldoAjustado = Math.max(novoSaldoXp, 0);
        Cliente clienteAtualizado;

        if (cliente instanceof ClienteVip) {
            clienteAtualizado = new ClienteVip(cliente.getNome(), cliente.getCpf(), saldoAjustado);
        } else {
            clienteAtualizado = new ClienteStandard(cliente.getNome(), cliente.getCpf(), saldoAjustado);
        }

        clientes.atualizarCliente(cliente.getCpf(), clienteAtualizado);
        return clienteAtualizado;
    }

    private static Cliente buscarClientePorCpfSeguro(String cpf) {
        try {
            return clientes.buscarClientePorCpf(cpf);
        } catch (CpfInvalidoException e) {
            return null;
        }
    }

    private static int numeroDoProximoAtendimento() {
        return atendimentosFinalizados + 1;
    }

    private static boolean sortearEventoGeekAtendimento() {
        return sorteadorEventoGeek.nextInt(INTERVALO_EVENTO_GEEK) == 0;
    }

    private static boolean passarCarrinhoParaPedido(Pedido pedido, Map<Integer, ItemCarrinho> carrinho) {
        for (ItemCarrinho item : carrinho.values()) {
            try {
                pedido.adicionarProduto(item.produto, item.quantidade);
            } catch (EstaNaListaExceptionProduto e) {
                System.out.println("Erro de duplicidade ao montar pedido: " + e.getMessage());
                System.out.println("A Main agrupa itens iguais antes de chamar Pedido.adicionarProduto.");
                return false;
            } catch (EstoqueInsuficienteException e) {
                System.out.println("Estoque insuficiente: " + e.getMessage());
                return false;
            }
        }
        return true;
    }


    private static void exibirCardapioParaCliente() {
        System.out.println();
        System.out.println("========================= CARDAPIO BYTE & BREW =========================");
        System.out.println("BEBIDAS / POCOES");
        System.out.println("------------------------------------------------------------------------");
        imprimirCabecalhoCardapio();
        for (Produto produto : cardapio) {
            if (produto instanceof Bebida) {
                imprimirLinhaCardapio(produto);
            }
        }

        System.out.println();
        System.out.println("COMIDAS / COMESTIVEIS");
        System.out.println("------------------------------------------------------------------------");
        imprimirCabecalhoCardapio();
        for (Produto produto : cardapio) {
            if (produto instanceof Comida) {
                imprimirLinhaCardapio(produto);
            }
        }
        System.out.println("========================================================================");
    }

    private static void imprimirCabecalhoCardapio() {
        System.out.printf("%-5s %-35s %10s %8s %-18s%n", "Cod", "Item", "Preco", "Estoque", "Detalhe");
    }

    private static void imprimirLinhaCardapio(Produto produto) {
        String detalhe;

        if (produto instanceof Bebida) {
            Bebida bebida = (Bebida) produto;
            detalhe = bebida.getTamanho() + ", " + bebida.quantidadeCafeina() + "mg cafeina";
        } else if (produto instanceof Comida) {
            Comida comida = (Comida) produto;
            String restricao = comida.getVegano() ? "vegano" : "tradicional";
            if (!comida.getGluten()) {
                restricao += ", sem gluten";
            }
            detalhe = comida.getTempoPreparo() + "min, " + restricao;
        } else {
            detalhe = "produto";
        }

        System.out.printf(
                "%-5d %-35s R$ %7.2f %8d %-18s%n",
                produto.getCodigoProduto(),
                produto.getNomeProduto(),
                produto.getPrecoBase(),
                produto.getQtdProduto(),
                detalhe
        );
    }

    private static Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto produto : cardapio) {
            if (produto.getCodigoProduto() == codigo) {
                return produto;
            }
        }
        return null;
    }

    private static void mostrarResumoCarrinho(Map<Integer, ItemCarrinho> carrinho) {
        System.out.printf("Itens na comanda: %d | Total parcial: R$ %.2f%n", carrinho.size(), calcularTotalCarrinho(carrinho));
    }

    private static void exibirComandaAtual(Map<Integer, ItemCarrinho> carrinho) {
        System.out.println();
        System.out.println("============================ COMANDA ATUAL =============================");

        if (carrinho.isEmpty()) {
            System.out.println("Comanda vazia.");
            return;
        }

        System.out.printf("%-5s %-35s %8s %10s %10s%n", "Cod", "Item", "Qtd", "Unitario", "Subtotal");
        for (ItemCarrinho item : carrinho.values()) {
            float subtotal = item.produto.getPrecoBase() * item.quantidade;
            System.out.printf(
                    "%-5d %-35s %8d R$ %7.2f R$ %7.2f%n",
                    item.produto.getCodigoProduto(),
                    item.produto.getNomeProduto(),
                    item.quantidade,
                    item.produto.getPrecoBase(),
                    subtotal
            );
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("Total parcial: R$ %.2f%n", calcularTotalCarrinho(carrinho));
    }

    private static float calcularTotalCarrinho(Map<Integer, ItemCarrinho> carrinho) {
        float total = 0;
        for (ItemCarrinho item : carrinho.values()) {
            total += item.produto.getPrecoBase() * item.quantidade;
        }
        return total;
    }

    private static float calcularTotalBebidasCarrinho(Map<Integer, ItemCarrinho> carrinho) {
        float total = 0;
        for (ItemCarrinho item : carrinho.values()) {
            if (item.produto instanceof Bebida) {
                total += item.produto.getPrecoBase() * item.quantidade;
            }
        }
        return total;
    }



    private static void menuClientes() {
        int opcao;

        do {
            limparTelaVisual();
            System.out.println("================== PROGRAMA DE FIDELIDADE - MEU CADASTRO ==================");
            System.out.println("1 - Consultar meu cadastro e XP");
            System.out.println("2 - Atualizar meu cadastro");
            System.out.println("3 - Remover meu cadastro");
            System.out.println("4 - Ver regras de XP, Standard e VIP");
            System.out.println("0 - Voltar");
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    buscarClienteMenu();
                    break;
                case 2:
                    atualizarCliente();
                    break;
                case 3:
                    removerCliente();
                    break;
                case 4:
                    mostrarRegraVip();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
            if (opcao != 0) {
                pausar();
            }
        } while (opcao != 0);
    }

    private static Cliente lerDadosClienteStandard() throws CpfInvalidoException {
        String nome = lerTextoObrigatorio("Nome: ");
        String cpf = lerCpfValido("CPF: ");
        return new ClienteStandard(nome, cpf, 0);
    }

    private static Cliente lerDadosClientePreservandoCategoria(Cliente clienteAtual) throws CpfInvalidoException {
        String nome = lerTextoObrigatorio("Nome: ");
        String cpf = lerCpfValido("CPF: ");
        int xpAtual = clienteAtual.getSaldoXp();

        if (clienteAtual instanceof ClienteVip) {
            return new ClienteVip(nome, cpf, xpAtual);
        }

        return new ClienteStandard(nome, cpf, xpAtual);
    }

    private static void buscarClienteMenu() {
        try {
            String cpf = lerCpfValido("CPF do seu cadastro: ");
            Cliente cliente = clientes.buscarClientePorCpf(cpf);

            if (cliente == null) {
                System.out.println("Cliente nao encontrado.");
            } else {
                imprimirClienteDetalhado(cliente);
            }
        } catch (CpfInvalidoException e) {
            System.out.println("CPF invalido: " + e.getCpf());
        }
    }

    private static void atualizarCliente() {
        try {
            String cpfAntigo = lerCpfValido("CPF do seu cadastro a atualizar: ");
            Cliente clienteAtual = clientes.buscarClientePorCpf(cpfAntigo);

            if (clienteAtual == null) {
                System.out.println("Cadastro nao encontrado.");
                return;
            }

            imprimirClienteDetalhado(clienteAtual);
            System.out.println("Seu tipo de cliente sera preservado.");
            System.out.println("Clientes Standard so viram VIP automaticamente por quantidade de compras.");
            Cliente novoCliente = lerDadosClientePreservandoCategoria(clienteAtual);

            if (clientes.atualizarCliente(cpfAntigo, novoCliente)) {
                int compras = contarComprasCliente(cpfAntigo);
                comprasPorCpf.remove(cpfAntigo);
                comprasPorCpf.put(novoCliente.getCpf(), compras);
                System.out.println("Cadastro atualizado. XP e compras foram preservados.");
            } else {
                System.out.println("Nao foi possivel atualizar.");
            }
        } catch (CpfInvalidoException | IllegalArgumentException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    private static void removerCliente() {
        try {
            String cpf = lerCpfValido("CPF do seu cadastro a remover: ");
            Cliente cliente = clientes.buscarClientePorCpf(cpf);

            if (cliente == null) {
                System.out.println("Cadastro nao encontrado.");
                return;
            }

            imprimirClienteDetalhado(cliente);
            if (lerSimNao("Confirmar remocao do seu cadastro? (s/n): ")) {
                boolean removido = clientes.removerCliente(cpf);
                if (removido) {
                    comprasPorCpf.remove(cpf);
                }
                System.out.println(removido ? "Seu cadastro foi removido." : "Cadastro nao removido.");
            }
        } catch (CpfInvalidoException e) {
            System.out.println("CPF invalido: " + e.getCpf());
        }
    }

    private static void imprimirClienteDetalhado(Cliente cliente) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Tipo: " + tipoCliente(cliente));
        System.out.println("Saldo XP: " + cliente.getSaldoXp());
        System.out.printf("Equivalencia aproximada: R$ %.2f em XP (%d XP = R$ 1,00)%n",
                cliente.getSaldoXp() / (float) TAXA_XP_POR_REAL, TAXA_XP_POR_REAL);
        System.out.println("Compras registradas: " + contarComprasCliente(cliente.getCpf()));
        if (cliente instanceof ClienteStandard) {
            int faltam = COMPRAS_PARA_VIP - contarComprasCliente(cliente.getCpf());
            System.out.println("Faltam para VIP: " + Math.max(faltam, 0));
        }
    }

    private static void mostrarPainelFidelidade(Cliente cliente) {
        System.out.println();
        System.out.println("Painel de fidelidade:");
        if (cliente == null) {
            System.out.println("Cliente casual: nao acumula XP e nao pode resgatar XP.");
            return;
        }

        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Tipo: " + tipoCliente(cliente));
        System.out.println("Saldo XP: " + cliente.getSaldoXp());
        System.out.println("Compras registradas: " + contarComprasCliente(cliente.getCpf()));
        if (cliente instanceof ClienteVip) {
            System.out.println("Beneficio VIP: ganha 2 XP por R$ 1,00 e pode pagar 50% ou 100% do pedido com XP.");
        } else {
            int faltam = Math.max(COMPRAS_PARA_VIP - contarComprasCliente(cliente.getCpf()), 0);
            System.out.println("Cliente Standard: ganha 1 XP por R$ 1,00 e pode usar XP para pagar ate 50% do pedido.");
            System.out.println("Faltam " + faltam + " compra(s) para virar VIP.");
        }
    }

    private static String tipoCliente(Cliente cliente) {
        if (cliente instanceof ClienteVip) {
            return "VIP";
        }
        if (cliente instanceof ClienteStandard) {
            return "Standard";
        }
        return "Cliente";
    }

    private static void mostrarRegraVip() {
        System.out.println("Todo cliente cadastrado entra como Standard com 0 XP.");
        System.out.println("XP nao e cadastrado manualmente: ele e ganho ao finalizar compras.");
        System.out.println("Standard: ganha 1 XP por R$ 1,00 gasto e pode usar XP para pagar ate 50% do pedido.");
        System.out.println("Ao completar " + COMPRAS_PARA_VIP + " compras registradas, o cliente e elevado automaticamente a VIP.");
        System.out.println("VIP: ganha 2 XP por R$ 1,00 e pode escolher pagar 50% ou 100% do pedido com XP.");
        System.out.println("Conversao: " + TAXA_XP_POR_REAL + " XP = R$ 1,00.");
        System.out.println("Dia de Evento Geek: sorteado automaticamente a cada atendimento, com chance de 1 em " + INTERVALO_EVENTO_GEEK + ".");
    }

    private static void registrarCompraCliente(Cliente cliente) {
        if (cliente == null || cliente.getCpf() == null) {
            return;
        }

        int comprasAtuais = contarComprasCliente(cliente.getCpf());
        comprasPorCpf.put(cliente.getCpf(), comprasAtuais + 1);
    }

    private static void avaliarPromocaoParaVip(Cliente cliente) {
        if (!(cliente instanceof ClienteStandard) || cliente instanceof ClienteVip) {
            return;
        }

        int compras = contarComprasCliente(cliente.getCpf());

        if (compras < COMPRAS_PARA_VIP) {
            return;
        }

        try {
            ClienteVip clienteVip = new ClienteVip(cliente.getNome(), cliente.getCpf(), cliente.getSaldoXp());
            clientes.atualizarCliente(cliente.getCpf(), clienteVip);
            System.out.println();
            System.out.println("*** PROMOCAO DE FIDELIDADE ***");
            System.out.println(cliente.getNome() + " atingiu " + compras + " compras e agora e Cliente VIP!");
            System.out.println("Nas proximas compras, ganhara XP em dobro e podera pagar pedidos com XP.");
        } catch (CpfInvalidoException | IllegalArgumentException e) {
            System.out.println("Nao foi possivel promover cliente para VIP: " + e.getMessage());
        }
    }

    private static int contarComprasCliente(String cpf) {
        if (cpf == null) {
            return 0;
        }

        Integer compras = comprasPorCpf.get(cpf);
        return compras == null ? 0 : compras;
    }

    private static String nomeClienteOuCasual(Cliente cliente) {
        if (cliente == null) {
            return "Cliente casual";
        }
        return cliente.getNome() + " (" + tipoCliente(cliente) + ")";
    }



    private static void buscarProdutoMenu() {
        int codigo = lerInteiro("Codigo do produto: ");
        Produto produto = buscarProdutoPorCodigo(codigo);

        if (produto == null) {
            System.out.println("Produto nao encontrado.");
        } else {
            imprimirProdutoDetalhado(produto);
        }
    }

    private static void imprimirProdutoDetalhado(Produto produto) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Codigo: " + produto.getCodigoProduto());
        System.out.println("Nome: " + produto.getNomeProduto());
        System.out.printf("Preco base: R$ %.2f%n", produto.getPrecoBase());
        System.out.println("Estoque: " + produto.getQtdProduto());

        if (produto instanceof Bebida) {
            Bebida bebida = (Bebida) produto;
            System.out.println("Categoria: Bebida");
            System.out.println("Tamanho: " + bebida.getTamanho());
            System.out.println("Densidade cafeina: " + bebida.getDensidadeCafeina());
            System.out.println("Cafeina estimada: " + bebida.quantidadeCafeina() + " mg");
        } else if (produto instanceof Comida) {
            Comida comida = (Comida) produto;
            System.out.println("Categoria: Comida");
            System.out.println("Tempo preparo: " + comida.getTempoPreparo() + " min");
            System.out.println("Vegano: " + simNao(comida.getVegano()));
            System.out.println("Contem gluten: " + simNao(comida.getGluten()));
        }
    }



    private static void buscarVendaPorComanda() {
        int comanda = lerInteiro("Numero da sua comanda: ");

        for (RegistroVenda registro : historicoVendas) {
            if (registro.comanda == comanda) {
                imprimirNotaFiscal(registro);
                return;
            }
        }

        System.out.println("Comanda nao encontrada.");
    }

    private static void imprimirNotaFiscal(RegistroVenda registro) {
        System.out.println();
        System.out.println("======================= BYTE & BREW =======================");
        System.out.println("Comanda: " + registro.comanda);
        System.out.println("Atendente: " + registro.nomeAtendente);
        System.out.println("Cliente: " + registro.nomeCliente);
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-35s %6s %10s %10s%n", "Item", "Qtd", "Unitario", "Subtotal");
        for (ItemRegistro item : registro.itens) {
            System.out.printf("%-35s %6d R$ %7.2f R$ %7.2f%n", item.nome, item.quantidade, item.precoUnitario, item.subtotal);
        }
        System.out.println("------------------------------------------------------------");
        System.out.printf("Total bruto: R$ %.2f%n", registro.totalBruto);
        System.out.println("Dia Evento Geek: " + simNao(registro.diaEventoGeek));
        if (registro.descontoEventoGeek > 0.0f) {
            System.out.printf("Desconto Evento Geek: R$ %.2f%n", registro.descontoEventoGeek);
        }
        System.out.printf("Subtotal apos descontos automaticos: R$ %.2f%n", registro.totalSemXp);
        System.out.println("Pagamento com XP: " + simNao(registro.pagamentoComXp));
        if (registro.pagamentoComXp) {
            System.out.println("Modalidade XP: " + registro.descricaoPagamentoXp);
            System.out.println("XP usados: " + registro.pontosXpUsados + " XP");
            System.out.printf("Valor pago com XP: R$ %.2f%n", registro.descontoXp);
        }
        System.out.printf("Total final a pagar em dinheiro/cartao: R$ %.2f%n", registro.totalFinal);
        System.out.println("============================================================");
    }



    private static void exibirAtendentesParaCliente() {
        System.out.println("=========================== ATENDENTES ===========================");

        if (atendentes.isEmpty()) {
            System.out.println("Nenhum atendente disponivel no momento.");
            return;
        }

        System.out.println("A cafeteria alterna automaticamente os atendentes a cada atendimento.");
        System.out.println("Equipe no balcao hoje:");
        for (int i = 0; i < atendentes.size(); i++) {
            Atendente atendente = atendentes.get(i);
            System.out.printf("%d - %s%n", i + 1, atendente.getNome());
        }
    }

    private static Atendente proximoAtendente() {
        if (atendentes.isEmpty()) {
            adicionarAtendenteInicial("99988877766", "Atendente Emergencial");
        }

        Atendente atendente = atendentes.get(indiceAtendenteAtual);
        indiceAtendenteAtual = (indiceAtendenteAtual + 1) % atendentes.size();
        return atendente;
    }


    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.nextLine().trim();
    }

    private static String lerTextoObrigatorio(String mensagem) {
        String texto;
        do {
            texto = lerTexto(mensagem);
            if (texto.isEmpty()) {
                System.out.println("Campo obrigatorio.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = entrada.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }

    private static int lerInteiroMinimo(String mensagem, int minimo) {
        int valor;
        do {
            valor = lerInteiro(mensagem);
            if (valor < minimo) {
                System.out.println("Valor minimo: " + minimo);
            }
        } while (valor < minimo);
        return valor;
    }

    private static float lerFloat(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = entrada.nextLine().trim().replace(',', '.');
            try {
                return Float.parseFloat(texto);
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor numerico valido.");
            }
        }
    }

    private static float lerFloatMinimoMaximo(String mensagem, float minimo, float maximo) {
        float valor;
        do {
            valor = lerFloat(mensagem);
            if (valor < minimo || valor > maximo) {
                System.out.printf("Digite um valor entre R$ %.2f e R$ %.2f.%n", minimo, maximo);
            }
        } while (valor < minimo || valor > maximo);
        return valor;
    }

    private static boolean lerSimNao(String mensagem) {
        while (true) {
            String texto = lerTexto(mensagem).toLowerCase(Locale.ROOT);
            if (texto.equals("s") || texto.equals("sim")) {
                return true;
            }
            if (texto.equals("n") || texto.equals("nao") || texto.equals("não")) {
                return false;
            }
            System.out.println("Responda com s/n.");
        }
    }

    private static String lerCpfValido(String mensagem) throws CpfInvalidoException {
        String cpf = lerTextoObrigatorio(mensagem);
        return VerificadorCpf.verficadorCpf(cpf);
    }

    private static void pausar() {
        System.out.print("Pressione ENTER para continuar...");
        entrada.nextLine();
    }

    private static void limparTelaVisual() {
        System.out.println();
        System.out.println();
    }

    private static String simNao(boolean valor) {
        return valor ? "Sim" : "Nao";
    }



    private static class ItemCarrinho {
        private final Produto produto;
        private int quantidade;

        private ItemCarrinho(Produto produto, int quantidade) {
            this.produto = produto;
            this.quantidade = quantidade;
        }
    }

    private static class ItemRegistro {
        private final String nome;
        private final int quantidade;
        private final float precoUnitario;
        private final float subtotal;

        private ItemRegistro(Produto produto, int quantidade) {
            this.nome = produto.getNomeProduto();
            this.quantidade = quantidade;
            this.precoUnitario = produto.getPrecoBase();
            this.subtotal = produto.getPrecoBase() * quantidade;
        }
    }

    private static class PagamentoXp {
        private final boolean usouXp;
        private final int pontosUsados;
        private final float descontoEmReais;
        private final String descricao;

        private PagamentoXp(boolean usouXp, int pontosUsados, float descontoEmReais, String descricao) {
            this.usouXp = usouXp;
            this.pontosUsados = pontosUsados;
            this.descontoEmReais = descontoEmReais;
            this.descricao = descricao;
        }

        private static PagamentoXp semXp() {
            return new PagamentoXp(false, 0, 0.0f, "Sem uso de XP");
        }
    }

    private static class RegistroVenda {
        private final int comanda;
        private final String nomeAtendente;
        private final String nomeCliente;
        private final String cpfCliente;
        private final boolean diaEventoGeek;
        private final boolean pagamentoComXp;
        private final int pontosXpUsados;
        private final float descontoXp;
        private final String descricaoPagamentoXp;
        private final float totalBruto;
        private final float descontoEventoGeek;
        private final float totalSemXp;
        private final float totalFinal;
        private final ArrayList<ItemRegistro> itens;

        private RegistroVenda(Venda venda, float totalSemXp, float totalFinal, boolean diaEventoGeek,
                              PagamentoXp pagamentoXp, Map<Integer, ItemCarrinho> carrinho) {
            this.comanda = venda.getPedido().getComanda();
            this.nomeAtendente = venda.getAtendente() == null ? "Sem atendente" : venda.getAtendente().getNome();
            this.nomeCliente = venda.getCliente() == null ? "Cliente casual" : venda.getCliente().getNome();
            this.cpfCliente = venda.getCliente() == null ? null : venda.getCliente().getCpf();
            this.diaEventoGeek = diaEventoGeek;
            this.pagamentoComXp = pagamentoXp.usouXp;
            this.pontosXpUsados = pagamentoXp.pontosUsados;
            this.descontoXp = pagamentoXp.descontoEmReais;
            this.descricaoPagamentoXp = pagamentoXp.descricao;
            this.totalBruto = calcularTotalCarrinho(carrinho);
            this.descontoEventoGeek = Math.max(0.0f, this.totalBruto - totalSemXp);
            this.totalSemXp = totalSemXp;
            this.totalFinal = totalFinal;
            this.itens = new ArrayList<>();

            for (ItemCarrinho item : carrinho.values()) {
                this.itens.add(new ItemRegistro(item.produto, item.quantidade));
            }
        }
    }

}