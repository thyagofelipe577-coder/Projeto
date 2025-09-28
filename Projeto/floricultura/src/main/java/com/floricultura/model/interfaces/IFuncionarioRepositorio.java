package com.floricultura.model.interfaces;

import com.floricultura.model.entities.Funcionario;
import java.util.List;

/**
 * Interface específica para repositório de funcionários.
 */
public interface IFuncionarioRepositorio extends IRepositorio<Funcionario> {
    
    /**
     * Pesquisa funcionário por CPF.
     * @param cpf CPF do funcionário a ser pesquisado
     * @return Funcionário encontrado ou null se não encontrado
     */
    Funcionario pesquisarPorCpf(String cpf);
    
    /**
     * Pesquisa funcionários por nome.
     * @param nome Nome do funcionário a ser pesquisado
     * @return Lista de funcionários com o nome especificado
     */
    List<Funcionario> pesquisarPorNome(String nome);
    
    /**
     * Lista funcionários ativos.
     * @return Lista de funcionários ativos
     */
    List<Funcionario> listarAtivos();
    
    /**
     * Lista funcionários inativos.
     * @return Lista de funcionários inativos
     */
    List<Funcionario> listarInativos();
    
    /**
     * Lista funcionários por cargo.
     * @param cargo Cargo dos funcionários a serem listados
     * @return Lista de funcionários do cargo especificado
     */
    List<Funcionario> listarPorCargo(String cargo);
    
    /**
     * Lista vendedores.
     * @return Lista de funcionários vendedores
     */
    List<Funcionario> listarVendedores();
    
    /**
     * Verifica se existe funcionário com o CPF especificado.
     * @param cpf CPF a ser verificado
     * @return true se existe, false caso contrário
     */
    boolean existePorCpf(String cpf);
}
