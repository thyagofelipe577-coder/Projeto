package com.floricultura.model.interfaces;

import com.floricultura.model.entities.Fornecedor;
import java.util.List;

/**
 * Interface específica para serviço de fornecedores.
 */
public interface IFornecedorServico extends IServico<Fornecedor> {
    
    /**
     * Pesquisa fornecedor por CNPJ.
     * @param cnpj CNPJ do fornecedor a ser pesquisado
     * @return Fornecedor encontrado ou null se não encontrado
     * @throws Exception Se houver erro na pesquisa
     */
    Fornecedor pesquisarPorCnpj(String cnpj) throws Exception;
    
    /**
     * Pesquisa fornecedores por nome.
     * @param nome Nome do fornecedor a ser pesquisado
     * @return Lista de fornecedores com o nome especificado
     * @throws Exception Se houver erro na pesquisa
     */
    List<Fornecedor> pesquisarPorNome(String nome) throws Exception;
    
    /**
     * Lista fornecedores ativos.
     * @return Lista de fornecedores ativos
     * @throws Exception Se houver erro na listagem
     */
    List<Fornecedor> listarAtivos() throws Exception;
    
    /**
     * Lista fornecedores inativos.
     * @return Lista de fornecedores inativos
     * @throws Exception Se houver erro na listagem
     */
    List<Fornecedor> listarInativos() throws Exception;
    
    /**
     * Verifica se existe fornecedor com o CNPJ especificado.
     * @param cnpj CNPJ a ser verificado
     * @return true se existe, false caso contrário
     * @throws Exception Se houver erro na verificação
     */
    boolean existePorCnpj(String cnpj) throws Exception;
    
    /**
     * Ativa um fornecedor.
     * @param id ID do fornecedor
     * @return true se ativou com sucesso, false caso contrário
     * @throws Exception Se houver erro na ativação
     */
    boolean ativar(int id) throws Exception;
    
    /**
     * Desativa um fornecedor.
     * @param id ID do fornecedor
     * @return true se desativou com sucesso, false caso contrário
     * @throws Exception Se houver erro na desativação
     */
    boolean desativar(int id) throws Exception;
}
