package com.floricultura.model.interfaces;

import com.floricultura.model.entities.Cliente;
import java.util.List;

/**
 * Interface específica para repositório de clientes.
 */
public interface IClienteRepositorio extends IRepositorio<Cliente> {
    
    /**
     * Pesquisa cliente por CPF.
     * @param cpf CPF do cliente a ser pesquisado
     * @return Cliente encontrado ou null se não encontrado
     */
    Cliente pesquisarPorCpf(String cpf);
    
    /**
     * Pesquisa clientes por nome.
     * @param nome Nome do cliente a ser pesquisado
     * @return Lista de clientes com o nome especificado
     */
    List<Cliente> pesquisarPorNome(String nome);
    
    /**
     * Lista clientes VIP.
     * @return Lista de clientes VIP
     */
    List<Cliente> listarClientesVip();
    
    /**
     * Lista clientes por faixa de gasto.
     * @param gastoMinimo Gasto mínimo
     * @param gastoMaximo Gasto máximo
     * @return Lista de clientes na faixa de gasto especificada
     */
    List<Cliente> listarPorFaixaGasto(double gastoMinimo, double gastoMaximo);
    
    /**
     * Verifica se existe cliente com o CPF especificado.
     * @param cpf CPF a ser verificado
     * @return true se existe, false caso contrário
     */
    boolean existePorCpf(String cpf);
}
