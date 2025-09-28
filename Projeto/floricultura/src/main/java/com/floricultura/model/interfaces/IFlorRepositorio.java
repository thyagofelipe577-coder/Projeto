package com.floricultura.model.interfaces;

import com.floricultura.model.entities.Flor;
import java.util.List;

/**
 * Interface específica para repositório de flores.
 */
public interface IFlorRepositorio extends IRepositorio<Flor> {
    
    /**
     * Pesquisa flores por nome.
     * @param nome Nome da flor a ser pesquisada
     * @return Lista de flores com o nome especificado
     */
    List<Flor> pesquisarPorNome(String nome);
    
    /**
     * Pesquisa flores por espécie.
     * @param especie Espécie da flor a ser pesquisada
     * @return Lista de flores da espécie especificada
     */
    List<Flor> pesquisarPorEspecie(String especie);
    
    /**
     * Pesquisa flores por cor.
     * @param cor Cor da flor a ser pesquisada
     * @return Lista de flores da cor especificada
     */
    List<Flor> pesquisarPorCor(String cor);
    
    /**
     * Lista flores disponíveis para venda.
     * @return Lista de flores com estoque > 0 e disponível = true
     */
    List<Flor> listarDisponiveis();
    
    /**
     * Lista flores com estoque baixo (quantidade <= limite).
     * @param limite Limite de estoque baixo
     * @return Lista de flores com estoque baixo
     */
    List<Flor> listarComEstoqueBaixo(int limite);
    
    /**
     * Pesquisa flores por faixa de preço.
     * @param precoMinimo Preço mínimo
     * @param precoMaximo Preço máximo
     * @return Lista de flores na faixa de preço especificada
     */
    List<Flor> pesquisarPorFaixaPreco(double precoMinimo, double precoMaximo);
}
