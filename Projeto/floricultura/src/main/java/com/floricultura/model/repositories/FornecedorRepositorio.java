package com.floricultura.model.repositories;

import com.floricultura.model.entities.Fornecedor;
import com.floricultura.model.interfaces.IFornecedorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do repositório de fornecedores usando ArrayList.
 */
public class FornecedorRepositorio extends RepositorioBase<Fornecedor> implements IFornecedorRepositorio {

    @Override
    protected int obterId(Fornecedor entidade) {
        return entidade.getId();
    }

    @Override
    protected void definirId(Fornecedor entidade) {
        entidade.setId(proximoId);
    }

    @Override
    public Fornecedor pesquisarPorCnpj(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            return null;
        }
        
        return entidades.stream()
                .filter(fornecedor -> fornecedor.getCnpj() != null && 
                                    fornecedor.getCnpj().equals(cnpj.trim()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Fornecedor> pesquisarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        return entidades.stream()
                .filter(fornecedor -> fornecedor.getNome() != null && 
                                    fornecedor.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Fornecedor> listarAtivos() {
        return entidades.stream()
                .filter(Fornecedor::isAtivo)
                .collect(Collectors.toList());
    }

    @Override
    public List<Fornecedor> listarInativos() {
        return entidades.stream()
                .filter(fornecedor -> !fornecedor.isAtivo())
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorCnpj(String cnpj) {
        return pesquisarPorCnpj(cnpj) != null;
    }
}
