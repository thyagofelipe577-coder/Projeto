package com.floricultura.model.interfaces;

import com.floricultura.model.entities.Cliente;
import java.util.List;

/**
 * Interface específica para serviço de clientes.
 */
public interface IClienteServico extends IServico<Cliente> {
    
    /**
     * Pesquisa cliente por CPF.
     * @param cpf CPF do cliente a ser pesquisado
     * @return Cliente encontrado ou null se não encontrado
     * @throws Exception Se houver erro na pesquisa
     */
    Cliente pesquisarPorCpf(String cpf) throws Exception;
    
    /**
     * Pesquisa clientes por nome.
     * @param nome Nome do cliente a ser pesquisado
     * @return Lista de clientes com o nome especificado
     * @throws Exception Se houver erro na pesquisa
     */
    List<Cliente> pesquisarPorNome(String nome) throws Exception;
    
    /**
     * Lista clientes VIP.
     * @return Lista de clientes VIP
     * @throws Exception Se houver erro na listagem
     */
    List<Cliente> listarClientesVip() throws Exception;
    
    /**
     * Lista clientes por faixa de gasto.
     * @param gastoMinimo Gasto mínimo
     * @param gastoMaximo Gasto máximo
     * @return Lista de clientes na faixa de gasto especificada
     * @throws Exception Se houver erro na listagem
     */
    List<Cliente> listarPorFaixaGasto(double gastoMinimo, double gastoMaximo) throws Exception;
    
    /**
     * Verifica se existe cliente com o CPF especificado.
     * @param cpf CPF a ser verificado
     * @return true se existe, false caso contrário
     * @throws Exception Se houver erro na verificação
     */
    boolean existePorCpf(String cpf) throws Exception;
    
    /**
     * Promove um cliente para VIP.
     * @param id ID do cliente
     * @return true se promoveu com sucesso, false caso contrário
     * @throws Exception Se houver erro na promoção
     */
    boolean promoverParaVip(int id) throws Exception;
    
    /**
     * Calcula desconto VIP para um cliente.
     * @param id ID do cliente
     * @param valor Valor da compra
     * @return Valor do desconto
     * @throws Exception Se houver erro no cálculo
     */
    double calcularDescontoVip(int id, double valor) throws Exception;
}
