package com.floricultura.model.services;

import com.floricultura.model.entities.Fornecedor;
import com.floricultura.model.interfaces.IFornecedorRepositorio;
import com.floricultura.model.interfaces.IFornecedorServico;
import java.util.List;

/**
 * Implementação do serviço de fornecedores com regras de negócio.
 */
public class FornecedorServico implements IFornecedorServico {
    private IFornecedorRepositorio repositorio;

    public FornecedorServico(IFornecedorRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public boolean cadastrar(Fornecedor entidade) throws Exception {
        if (!validar(entidade)) {
            throw new Exception("Dados do fornecedor inválidos");
        }
        
        if (repositorio.existe(entidade.getId())) {
            throw new Exception("Fornecedor com ID " + entidade.getId() + " já existe");
        }
        
        if (repositorio.existePorCnpj(entidade.getCnpj())) {
            throw new Exception("Fornecedor com CNPJ " + entidade.getCnpj() + " já cadastrado");
        }
        
        return repositorio.cadastrar(entidade);
    }

    @Override
    public boolean remover(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        if (!repositorio.existe(id)) {
            throw new Exception("Fornecedor com ID " + id + " não encontrado");
        }
        
        return repositorio.remover(id);
    }

    @Override
    public boolean atualizar(Fornecedor entidade) throws Exception {
        if (!validar(entidade)) {
            throw new Exception("Dados do fornecedor inválidos");
        }
        
        if (!repositorio.existe(entidade.getId())) {
            throw new Exception("Fornecedor com ID " + entidade.getId() + " não encontrado");
        }
        
        // Verificar se o CNPJ já existe em outro fornecedor
        Fornecedor fornecedorExistente = repositorio.pesquisarPorCnpj(entidade.getCnpj());
        if (fornecedorExistente != null && fornecedorExistente.getId() != entidade.getId()) {
            throw new Exception("CNPJ " + entidade.getCnpj() + " já cadastrado para outro fornecedor");
        }
        
        return repositorio.atualizar(entidade);
    }

    @Override
    public Fornecedor pesquisarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        return repositorio.pesquisarPorId(id);
    }

    @Override
    public List<Fornecedor> listarTodos() throws Exception {
        return repositorio.listarTodos();
    }

    @Override
    public boolean validar(Fornecedor entidade) {
        if (entidade == null) {
            return false;
        }
        
        return entidade.validarDados() &&
               entidade.getCnpj() != null && !entidade.getCnpj().trim().isEmpty() &&
               validarCnpj(entidade.getCnpj());
    }

    @Override
    public Fornecedor pesquisarPorCnpj(String cnpj) throws Exception {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new Exception("CNPJ não pode ser vazio");
        }
        
        if (!validarCnpj(cnpj)) {
            throw new Exception("CNPJ inválido");
        }
        
        return repositorio.pesquisarPorCnpj(cnpj);
    }

    @Override
    public List<Fornecedor> pesquisarPorNome(String nome) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("Nome não pode ser vazio");
        }
        
        return repositorio.pesquisarPorNome(nome);
    }

    @Override
    public List<Fornecedor> listarAtivos() throws Exception {
        return repositorio.listarAtivos();
    }

    @Override
    public List<Fornecedor> listarInativos() throws Exception {
        return repositorio.listarInativos();
    }

    @Override
    public boolean existePorCnpj(String cnpj) throws Exception {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new Exception("CNPJ não pode ser vazio");
        }
        
        if (!validarCnpj(cnpj)) {
            throw new Exception("CNPJ inválido");
        }
        
        return repositorio.existePorCnpj(cnpj);
    }

    @Override
    public boolean ativar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        Fornecedor fornecedor = repositorio.pesquisarPorId(id);
        if (fornecedor == null) {
            throw new Exception("Fornecedor com ID " + id + " não encontrado");
        }
        
        fornecedor.setAtivo(true);
        return repositorio.atualizar(fornecedor);
    }

    @Override
    public boolean desativar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        Fornecedor fornecedor = repositorio.pesquisarPorId(id);
        if (fornecedor == null) {
            throw new Exception("Fornecedor com ID " + id + " não encontrado");
        }
        
        fornecedor.setAtivo(false);
        return repositorio.atualizar(fornecedor);
    }

    /**
     * Valida o formato do CNPJ (simplificado).
     * @param cnpj CNPJ a ser validado
     * @return true se válido, false caso contrário
     */
    private boolean validarCnpj(String cnpj) {
        if (cnpj == null) {
            return false;
        }
        
        // Remove caracteres não numéricos
        String cnpjLimpo = cnpj.replaceAll("[^0-9]", "");
        
        // Verifica se tem 14 dígitos
        if (cnpjLimpo.length() != 14) {
            return false;
        }
        
        // Verifica se não são todos os dígitos iguais
        if (cnpjLimpo.matches("(\\d)\\1{13}")) {
            return false;
        }
        
        return true;
    }
}
