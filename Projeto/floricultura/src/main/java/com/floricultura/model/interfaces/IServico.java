package com.floricultura.model.interfaces;

import java.util.List;

/**
 * Interface genérica para serviços com operações de negócio.
 * @param <T> Tipo da entidade
 */
public interface IServico<T> {
    
    /**
     * Cadastra uma nova entidade.
     * @param entidade Entidade a ser cadastrada
     * @return true se cadastrou com sucesso, false caso contrário
     * @throws Exception Se houver erro na validação ou cadastro
     */
    boolean cadastrar(T entidade) throws Exception;
    
    /**
     * Remove uma entidade pelo ID.
     * @param id ID da entidade a ser removida
     * @return true se removeu com sucesso, false caso contrário
     * @throws Exception Se houver erro na remoção
     */
    boolean remover(int id) throws Exception;
    
    /**
     * Atualiza uma entidade existente.
     * @param entidade Entidade com os dados atualizados
     * @return true se atualizou com sucesso, false caso contrário
     * @throws Exception Se houver erro na validação ou atualização
     */
    boolean atualizar(T entidade) throws Exception;
    
    /**
     * Pesquisa uma entidade pelo ID.
     * @param id ID da entidade a ser pesquisada
     * @return Entidade encontrada ou null se não encontrada
     * @throws Exception Se houver erro na pesquisa
     */
    T pesquisarPorId(int id) throws Exception;
    
    /**
     * Lista todas as entidades.
     * @return Lista com todas as entidades
     * @throws Exception Se houver erro na listagem
     */
    List<T> listarTodos() throws Exception;
    
    /**
     * Valida os dados de uma entidade.
     * @param entidade Entidade a ser validada
     * @return true se válida, false caso contrário
     */
    boolean validar(T entidade);
}
