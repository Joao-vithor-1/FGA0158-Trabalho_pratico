package br.edu.cafeteria.modelo;

import java.util.ArrayList;

import br.edu.cafeteria.excecao.CpfInvalidoException;
import br.edu.cafeteria.servico.VerificadorCpf;

public class ListaCliente {
    private ArrayList<Cliente> lista;

    public ListaCliente() {
        this.lista = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) throws CpfInvalidoException {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }

        String cpfVerificado = normalizarCpf(cliente.getCpf());

        if (existeCliente(cpfVerificado)) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF.");
        }

        lista.add(cliente);
    }

    public boolean buscarCliente(String cpf) throws CpfInvalidoException {
        return buscarClientePorCpf(cpf) != null;
    }

    public Cliente buscarClientePorCpf(String cpf) throws CpfInvalidoException {
        String cpfVerificado = normalizarCpf(cpf);
        int indice = buscarIndicePorCpfVerificado(cpfVerificado);

        if (indice == -1) {
            return null;
        }

        return lista.get(indice);
    }

    public boolean existeCliente(String cpf) throws CpfInvalidoException {
        String cpfVerificado = normalizarCpf(cpf);
        return buscarIndicePorCpfVerificado(cpfVerificado) != -1;
    }

    public boolean removerCliente(Cliente cliente) {
        if (cliente == null) {
            return false;
        }

        return lista.remove(cliente);
    }

    public boolean removerCliente(String cpf) throws CpfInvalidoException {
        String cpfVerificado = normalizarCpf(cpf);
        int indice = buscarIndicePorCpfVerificado(cpfVerificado);

        if (indice == -1) {
            return false;
        }

        lista.remove(indice);
        return true;
    }

    public boolean buscaClienteVip(String cpf) throws CpfInvalidoException {
        Cliente cliente = buscarClientePorCpf(cpf);
        return cliente instanceof ClienteVip;
    }

    public boolean atualizarCliente(String cpfAntigo, Cliente novoCliente) throws CpfInvalidoException {
        if (novoCliente == null) {
            throw new IllegalArgumentException("Novo cliente não pode ser nulo.");
        }

        String cpfAntigoVerificado = normalizarCpf(cpfAntigo);
        int indice = buscarIndicePorCpfVerificado(cpfAntigoVerificado);

        if (indice == -1) {
            return false;
        }

        String novoCpfVerificado = normalizarCpf(novoCliente.getCpf());

        int indiceNovoCpf = buscarIndicePorCpfVerificado(novoCpfVerificado);

        if (indiceNovoCpf != -1 && indiceNovoCpf != indice) {
            throw new IllegalArgumentException("Já existe outro cliente cadastrado com este CPF.");
        }

        lista.set(indice, novoCliente);
        return true;
    }

    public ArrayList<Cliente> listarClientes() {
        return new ArrayList<>(lista);
    }

    private int buscarIndicePorCpfVerificado(String cpfVerificado) {
        for (int i = 0; i < lista.size(); i++) {
            Cliente cliente = lista.get(i);

            if (cliente != null && cpfVerificado.equals(cliente.getCpf())) {
                return i;
            }
        }

        return -1;
    }

    private String normalizarCpf(String cpf) throws CpfInvalidoException {
        return VerificadorCpf.verficadorCpf(cpf);
    }
}