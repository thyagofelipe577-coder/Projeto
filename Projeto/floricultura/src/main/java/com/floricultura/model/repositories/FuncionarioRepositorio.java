package com.floricultura.model.repositories;

import com.floricultura.model.entities.Funcionario;
import com.floricultura.model.interfaces.IFuncionarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do repositório de funcionários usando ArrayList.
 */
public class FuncionarioRepositorio extends RepositorioBase<Funcionario> implements IFuncionarioRepositorio {

    @Override
    protected int obterId(Funcionario entidade) {
        return entidade.getId();
    }

    @Override
    protected void definirId(Funcionario entidade) {
        entidade.setId(proximoId);
    }

    @Override
    public Funcionario pesquisarPorCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return null;
        }
        
        return entidades.stream()
                .filter(funcionario -> funcionario.getCpf() != null && 
                                     funcionario.getCpf().equals(cpf.trim()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Funcionario> pesquisarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        return entidades.stream()
                .filter(funcionario -> funcionario.getNome() != null && 
                                     funcionario.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Funcionario> listarAtivos() {
        return entidades.stream()
                .filter(Funcionario::isAtivo)
                .collect(Collectors.toList());
    }

    @Override
    public List<Funcionario> listarInativos() {
        return entidades.stream()
                .filter(funcionario -> !funcionario.isAtivo())
                .collect(Collectors.toList());
    }

    @Override
    public List<Funcionario> listarPorCargo(String cargo) {
        if (cargo == null || cargo.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        return entidades.stream()
                .filter(funcionario -> funcionario.getCargo() != null && 
                                     funcionario.getCargo().toLowerCase().contains(cargo.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Funcionario> listarVendedores() {
        return entidades.stream()
                .filter(Funcionario::isVendedor)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorCpf(String cpf) {
        return pesquisarPorCpf(cpf) != null;
    }
}
