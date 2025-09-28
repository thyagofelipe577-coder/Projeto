package com.floricultura.model.interfaces;

import com.floricultura.model.entities.Fornecedor;
import java.util.List;

/**
 * Interface específica para repositório de fornecedores.
 */
public interface IFornecedorRepositorio extends IRepositorio<Fornecedor> {
    
    /**
     * Pesquisa fornecedor por CNPJ.
     * @param cnpj CNPJ do fornecedor a ser pesquisado
     * @return Fornecedor encontrado ou null se não encontrado
     */
    Fornecedor pesquisarPorCnpj(String cnpj);
    
    /**
     * Pesquisa fornecedores por nome.
     * @param nome Nome do fornecedor a ser pesquisado
     * @return Lista de fornecedores com o nome especificado
     */
    List<Fornecedor> pesquisarPorNome(String nome);
    
    /**
     * Lista fornecedores ativos.
     * @return Lista de fornecedores ativos
     */
    List<Fornecedor> listarAtivos();
    
    /**
     * Lista fornecedores inativos.
     * @return Lista de fornecedores inativos
     */
    List<Fornecedor> listarInativos();
    
    /**
     * Verifica se existe fornecedor com o CNPJ especificado.
     * @param cnpj CNPJ a ser verificado
     * @return true se existe, false caso contrário
     */
    boolean existePorCnpj(String cnpj);
}
