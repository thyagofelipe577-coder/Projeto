package com.floricultura.model.interfaces;

import com.floricultura.model.entities.Funcionario;
import java.util.List;

/**
 * Interface específica para serviço de funcionários.
 */
public interface IFuncionarioServico extends IServico<Funcionario> {
    
    /**
     * Pesquisa funcionário por CPF.
     * @param cpf CPF do funcionário a ser pesquisado
     * @return Funcionário encontrado ou null se não encontrado
     * @throws Exception Se houver erro na pesquisa
     */
    Funcionario pesquisarPorCpf(String cpf) throws Exception;
    
    /**
     * Pesquisa funcionários por nome.
     * @param nome Nome do funcionário a ser pesquisado
     * @return Lista de funcionários com o nome especificado
     * @throws Exception Se houver erro na pesquisa
     */
    List<Funcionario> pesquisarPorNome(String nome) throws Exception;
    
    /**
     * Lista funcionários ativos.
     * @return Lista de funcionários ativos
     * @throws Exception Se houver erro na listagem
     */
    List<Funcionario> listarAtivos() throws Exception;
    
    /**
     * Lista funcionários inativos.
     * @return Lista de funcionários inativos
     * @throws Exception Se houver erro na listagem
     */
    List<Funcionario> listarInativos() throws Exception;
    
    /**
     * Lista funcionários por cargo.
     * @param cargo Cargo dos funcionários a serem listados
     * @return Lista de funcionários do cargo especificado
     * @throws Exception Se houver erro na listagem
     */
    List<Funcionario> listarPorCargo(String cargo) throws Exception;
    
    /**
     * Lista vendedores.
     * @return Lista de funcionários vendedores
     * @throws Exception Se houver erro na listagem
     */
    List<Funcionario> listarVendedores() throws Exception;
    
    /**
     * Verifica se existe funcionário com o CPF especificado.
     * @param cpf CPF a ser verificado
     * @return true se existe, false caso contrário
     * @throws Exception Se houver erro na verificação
     */
    boolean existePorCpf(String cpf) throws Exception;
    
    /**
     * Ativa um funcionário.
     * @param id ID do funcionário
     * @return true se ativou com sucesso, false caso contrário
     * @throws Exception Se houver erro na ativação
     */
    boolean ativar(int id) throws Exception;
    
    /**
     * Desativa um funcionário.
     * @param id ID do funcionário
     * @return true se desativou com sucesso, false caso contrário
     * @throws Exception Se houver erro na desativação
     */
    boolean desativar(int id) throws Exception;
    
    /**
     * Calcula comissão de um funcionário.
     * @param id ID do funcionário
     * @return Valor da comissão total
     * @throws Exception Se houver erro no cálculo
     */
    double calcularComissao(int id) throws Exception;
}
