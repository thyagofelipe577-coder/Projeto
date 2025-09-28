package com.floricultura.model.repositories;

import com.floricultura.model.entities.Flor;
import com.floricultura.model.interfaces.IFlorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do repositório de flores usando ArrayList.
 */
public class FlorRepositorio extends RepositorioBase<Flor> implements IFlorRepositorio {

    @Override
    protected int obterId(Flor entidade) {
        return entidade.getId();
    }

    @Override
    protected void definirId(Flor entidade) {
        entidade.setId(proximoId);
    }

    @Override
    public List<Flor> pesquisarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        return entidades.stream()
                .filter(flor -> flor.getNome() != null && 
                               flor.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flor> pesquisarPorEspecie(String especie) {
        if (especie == null || especie.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        return entidades.stream()
                .filter(flor -> flor.getEspecie() != null && 
                               flor.getEspecie().toLowerCase().contains(especie.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flor> pesquisarPorCor(String cor) {
        if (cor == null || cor.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        return entidades.stream()
                .filter(flor -> flor.getCor() != null && 
                               flor.getCor().toLowerCase().contains(cor.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flor> listarDisponiveis() {
        return entidades.stream()
                .filter(Flor::estaDisponivelParaVenda)
                .collect(Collectors.toList());
    }

    @Override
    public List<Flor> listarComEstoqueBaixo(int limite) {
        return entidades.stream()
                .filter(flor -> flor.getQuantidadeEstoque() <= limite)
                .collect(Collectors.toList());
    }

    @Override
    public List<Flor> pesquisarPorFaixaPreco(double precoMinimo, double precoMaximo) {
        if (precoMinimo < 0 || precoMaximo < 0 || precoMinimo > precoMaximo) {
            return new ArrayList<>();
        }
        
        return entidades.stream()
                .filter(flor -> flor.getPreco() >= precoMinimo && flor.getPreco() <= precoMaximo)
                .collect(Collectors.toList());
    }
}
