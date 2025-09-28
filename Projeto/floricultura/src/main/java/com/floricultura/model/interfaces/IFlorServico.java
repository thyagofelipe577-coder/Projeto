package com.floricultura.model.interfaces;

import com.floricultura.model.entities.Flor;
import java.util.List;

/**
 * Interface específica para serviço de flores.
 */
public interface IFlorServico extends IServico<Flor> {
    
    /**
     * Pesquisa flores por nome.
     * @param nome Nome da flor a ser pesquisada
     * @return Lista de flores com o nome especificado
     * @throws Exception Se houver erro na pesquisa
     */
    List<Flor> pesquisarPorNome(String nome) throws Exception;
    
    /**
     * Pesquisa flores por espécie.
     * @param especie Espécie da flor a ser pesquisada
     * @return Lista de flores da espécie especificada
     * @throws Exception Se houver erro na pesquisa
     */
    List<Flor> pesquisarPorEspecie(String especie) throws Exception;
    
    /**
     * Pesquisa flores por cor.
     * @param cor Cor da flor a ser pesquisada
     * @return Lista de flores da cor especificada
     * @throws Exception Se houver erro na pesquisa
     */
    List<Flor> pesquisarPorCor(String cor) throws Exception;
    
    /**
     * Lista flores disponíveis para venda.
     * @return Lista de flores disponíveis
     * @throws Exception Se houver erro na listagem
     */
    List<Flor> listarDisponiveis() throws Exception;
    
    /**
     * Lista flores com estoque baixo.
     * @param limite Limite de estoque baixo
     * @return Lista de flores com estoque baixo
     * @throws Exception Se houver erro na listagem
     */
    List<Flor> listarComEstoqueBaixo(int limite) throws Exception;
    
    /**
     * Pesquisa flores por faixa de preço.
     * @param precoMinimo Preço mínimo
     * @param precoMaximo Preço máximo
     * @return Lista de flores na faixa de preço especificada
     * @throws Exception Se houver erro na pesquisa
     */
    List<Flor> pesquisarPorFaixaPreco(double precoMinimo, double precoMaximo) throws Exception;
    
    /**
     * Atualiza o estoque de uma flor.
     * @param id ID da flor
     * @param quantidade Quantidade a ser adicionada (pode ser negativa para reduzir)
     * @return true se atualizou com sucesso, false caso contrário
     * @throws Exception Se houver erro na atualização
     */
    boolean atualizarEstoque(int id, int quantidade) throws Exception;
    
    /**
     * Verifica se uma flor está disponível para venda.
     * @param id ID da flor
     * @param quantidade Quantidade desejada
     * @return true se disponível, false caso contrário
     * @throws Exception Se houver erro na verificação
     */
    boolean verificarDisponibilidade(int id, int quantidade) throws Exception;
}
