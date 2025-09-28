package com.floricultura.model.repositories;

import com.floricultura.model.interfaces.IRepositorio;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe base abstrata para repositórios com implementação comum de operações CRUD.
 * @param <T> Tipo da entidade
 */
public abstract class RepositorioBase<T> implements IRepositorio<T> {
    protected List<T> entidades;
    protected int proximoId;

    public RepositorioBase() {
        this.entidades = new ArrayList<>();
        this.proximoId = 1;
    }

    @Override
    public boolean cadastrar(T entidade) {
        if (entidade != null) {
            // Define o ID da entidade se necessário
            definirId(entidade);
            entidades.add(entidade);
            proximoId++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remover(int id) {
        T entidade = pesquisarPorId(id);
        if (entidade != null) {
            return entidades.remove(entidade);
        }
        return false;
    }

    @Override
    public boolean atualizar(T entidade) {
        if (entidade != null) {
            int id = obterId(entidade);
            T entidadeExistente = pesquisarPorId(id);
            if (entidadeExistente != null) {
                int index = entidades.indexOf(entidadeExistente);
                entidades.set(index, entidade);
                return true;
            }
        }
        return false;
    }

    @Override
    public T pesquisarPorId(int id) {
        for (T entidade : entidades) {
            if (obterId(entidade) == id) {
                return entidade;
            }
        }
        return null;
    }

    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(entidades);
    }

    @Override
    public boolean existe(int id) {
        return pesquisarPorId(id) != null;
    }

    @Override
    public int contar() {
        return entidades.size();
    }

    /**
     * Método abstrato para obter o ID de uma entidade.
     * @param entidade Entidade
     * @return ID da entidade
     */
    protected abstract int obterId(T entidade);

    /**
     * Método abstrato para definir o ID de uma entidade.
     * @param entidade Entidade
     */
    protected abstract void definirId(T entidade);
}
