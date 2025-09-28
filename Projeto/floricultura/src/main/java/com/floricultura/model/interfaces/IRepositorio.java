package com.floricultura.model.interfaces;

import java.util.List;

/**
 * Interface genérica para repositórios com operações CRUD básicas.
 * @param <T> Tipo da entidade
 */
public interface IRepositorio<T> {
    
    /**
     * Cadastra uma nova entidade no repositório.
     * @param entidade Entidade a ser cadastrada
     * @return true se cadastrou com sucesso, false caso contrário
     */
    boolean cadastrar(T entidade);
    
    /**
     * Remove uma entidade do repositório pelo ID.
     * @param id ID da entidade a ser removida
     * @return true se removeu com sucesso, false caso contrário
     */
    boolean remover(int id);
    
    /**
     * Atualiza uma entidade existente no repositório.
     * @param entidade Entidade com os dados atualizados
     * @return true se atualizou com sucesso, false caso contrário
     */
    boolean atualizar(T entidade);
    
    /**
     * Pesquisa uma entidade pelo ID.
     * @param id ID da entidade a ser pesquisada
     * @return Entidade encontrada ou null se não encontrada
     */
    T pesquisarPorId(int id);
    
    /**
     * Lista todas as entidades do repositório.
     * @return Lista com todas as entidades
     */
    List<T> listarTodos();
    
    /**
     * Verifica se existe uma entidade com o ID especificado.
     * @param id ID a ser verificado
     * @return true se existe, false caso contrário
     */
    boolean existe(int id);
    
    /**
     * Retorna a quantidade de entidades no repositório.
     * @return Número de entidades cadastradas
     */
    int contar();
}
