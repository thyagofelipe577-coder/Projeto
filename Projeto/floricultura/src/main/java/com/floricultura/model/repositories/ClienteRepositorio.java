package com.floricultura.model.repositories;

import com.floricultura.model.entities.Cliente;
import com.floricultura.model.interfaces.IClienteRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do repositório de clientes usando ArrayList.
 */
public class ClienteRepositorio extends RepositorioBase<Cliente> implements IClienteRepositorio {

    @Override
    protected int obterId(Cliente entidade) {
        return entidade.getId();
    }

    @Override
    protected void definirId(Cliente entidade) {
        entidade.setId(proximoId);
    }

    @Override
    public Cliente pesquisarPorCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return null;
        }
        
        return entidades.stream()
                .filter(cliente -> cliente.getCpf() != null && 
                                 cliente.getCpf().equals(cpf.trim()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Cliente> pesquisarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        return entidades.stream()
                .filter(cliente -> cliente.getNome() != null && 
                                 cliente.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cliente> listarClientesVip() {
        return entidades.stream()
                .filter(Cliente::isClienteVip)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cliente> listarPorFaixaGasto(double gastoMinimo, double gastoMaximo) {
        if (gastoMinimo < 0 || gastoMaximo < 0 || gastoMinimo > gastoMaximo) {
            return new ArrayList<>();
        }
        
        return entidades.stream()
                .filter(cliente -> cliente.getTotalGasto() >= gastoMinimo && 
                                 cliente.getTotalGasto() <= gastoMaximo)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorCpf(String cpf) {
        return pesquisarPorCpf(cpf) != null;
    }
}
