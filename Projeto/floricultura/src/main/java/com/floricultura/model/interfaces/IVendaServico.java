package com.floricultura.model.interfaces;

import com.floricultura.model.entities.Venda;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface específica para serviço de vendas.
 */
public interface IVendaServico extends IServico<Venda> {
    
    /**
     * Lista vendas por cliente.
     * @param clienteId ID do cliente
     * @return Lista de vendas do cliente especificado
     * @throws Exception Se houver erro na listagem
     */
    List<Venda> listarPorCliente(int clienteId) throws Exception;
    
    /**
     * Lista vendas por funcionário.
     * @param funcionarioId ID do funcionário
     * @return Lista de vendas do funcionário especificado
     * @throws Exception Se houver erro na listagem
     */
    List<Venda> listarPorFuncionario(int funcionarioId) throws Exception;
    
    /**
     * Lista vendas por período.
     * @param dataInicio Data de início do período
     * @param dataFim Data de fim do período
     * @return Lista de vendas no período especificado
     * @throws Exception Se houver erro na listagem
     */
    List<Venda> listarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) throws Exception;
    
    /**
     * Lista vendas por data.
     * @param data Data das vendas
     * @return Lista de vendas da data especificada
     * @throws Exception Se houver erro na listagem
     */
    List<Venda> listarPorData(LocalDate data) throws Exception;
    
    /**
     * Lista vendas finalizadas.
     * @return Lista de vendas finalizadas
     * @throws Exception Se houver erro na listagem
     */
    List<Venda> listarFinalizadas() throws Exception;
    
    /**
     * Lista vendas pendentes.
     * @return Lista de vendas não finalizadas
     * @throws Exception Se houver erro na listagem
     */
    List<Venda> listarPendentes() throws Exception;
    
    /**
     * Calcula o total de vendas em um período.
     * @param dataInicio Data de início do período
     * @param dataFim Data de fim do período
     * @return Valor total das vendas no período
     * @throws Exception Se houver erro no cálculo
     */
    double calcularTotalVendas(LocalDateTime dataInicio, LocalDateTime dataFim) throws Exception;
    
    /**
     * Finaliza uma venda.
     * @param id ID da venda
     * @return true se finalizou com sucesso, false caso contrário
     * @throws Exception Se houver erro na finalização
     */
    boolean finalizarVenda(int id) throws Exception;
    
    /**
     * Adiciona item a uma venda.
     * @param vendaId ID da venda
     * @param florId ID da flor
     * @param quantidade Quantidade do item
     * @return true se adicionou com sucesso, false caso contrário
     * @throws Exception Se houver erro na adição
     */
    boolean adicionarItem(int vendaId, int florId, int quantidade) throws Exception;
    
    /**
     * Remove item de uma venda.
     * @param vendaId ID da venda
     * @param florId ID da flor
     * @return true se removeu com sucesso, false caso contrário
     * @throws Exception Se houver erro na remoção
     */
    boolean removerItem(int vendaId, int florId) throws Exception;
}
