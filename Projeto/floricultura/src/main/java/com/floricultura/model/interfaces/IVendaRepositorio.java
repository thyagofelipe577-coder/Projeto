package com.floricultura.model.interfaces;

import com.floricultura.model.entities.Venda;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface específica para repositório de vendas.
 */
public interface IVendaRepositorio extends IRepositorio<Venda> {
    
    /**
     * Lista vendas por cliente.
     * @param clienteId ID do cliente
     * @return Lista de vendas do cliente especificado
     */
    List<Venda> listarPorCliente(int clienteId);
    
    /**
     * Lista vendas por funcionário.
     * @param funcionarioId ID do funcionário
     * @return Lista de vendas do funcionário especificado
     */
    List<Venda> listarPorFuncionario(int funcionarioId);
    
    /**
     * Lista vendas por período.
     * @param dataInicio Data de início do período
     * @param dataFim Data de fim do período
     * @return Lista de vendas no período especificado
     */
    List<Venda> listarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim);
    
    /**
     * Lista vendas por data.
     * @param data Data das vendas
     * @return Lista de vendas da data especificada
     */
    List<Venda> listarPorData(LocalDate data);
    
    /**
     * Lista vendas finalizadas.
     * @return Lista de vendas finalizadas
     */
    List<Venda> listarFinalizadas();
    
    /**
     * Lista vendas pendentes.
     * @return Lista de vendas não finalizadas
     */
    List<Venda> listarPendentes();
    
    /**
     * Calcula o total de vendas em um período.
     * @param dataInicio Data de início do período
     * @param dataFim Data de fim do período
     * @return Valor total das vendas no período
     */
    double calcularTotalVendas(LocalDateTime dataInicio, LocalDateTime dataFim);
}
