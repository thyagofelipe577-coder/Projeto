package com.floricultura.model.services;

import com.floricultura.model.entities.Funcionario;
import com.floricultura.model.interfaces.IFuncionarioRepositorio;
import com.floricultura.model.interfaces.IFuncionarioServico;
import java.math.BigDecimal;
import java.util.List;

/**
 * Implementação do serviço de funcionários com regras de negócio.
 */
public class FuncionarioServico implements IFuncionarioServico {
    private IFuncionarioRepositorio repositorio;

    public FuncionarioServico(IFuncionarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public boolean cadastrar(Funcionario entidade) throws Exception {
        if (!validar(entidade)) {
            throw new Exception("Dados do funcionário inválidos");
        }
        
        if (repositorio.existe(entidade.getId())) {
            throw new Exception("Funcionário com ID " + entidade.getId() + " já existe");
        }
        
        if (repositorio.existePorCpf(entidade.getCpf())) {
            throw new Exception("Funcionário com CPF " + entidade.getCpf() + " já cadastrado");
        }
        
        return repositorio.cadastrar(entidade);
    }

    @Override
    public boolean remover(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        if (!repositorio.existe(id)) {
            throw new Exception("Funcionário com ID " + id + " não encontrado");
        }
        
        return repositorio.remover(id);
    }

    @Override
    public boolean atualizar(Funcionario entidade) throws Exception {
        if (!validar(entidade)) {
            throw new Exception("Dados do funcionário inválidos");
        }
        
        if (!repositorio.existe(entidade.getId())) {
            throw new Exception("Funcionário com ID " + entidade.getId() + " não encontrado");
        }
        
        // Verificar se o CPF já existe em outro funcionário
        Funcionario funcionarioExistente = repositorio.pesquisarPorCpf(entidade.getCpf());
        if (funcionarioExistente != null && funcionarioExistente.getId() != entidade.getId()) {
            throw new Exception("CPF " + entidade.getCpf() + " já cadastrado para outro funcionário");
        }
        
        return repositorio.atualizar(entidade);
    }

    @Override
    public Funcionario pesquisarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        return repositorio.pesquisarPorId(id);
    }

    @Override
    public List<Funcionario> listarTodos() throws Exception {
        return repositorio.listarTodos();
    }

    @Override
    public boolean validar(Funcionario entidade) {
        if (entidade == null) {
            return false;
        }
        
        return entidade.validarDados() &&
               entidade.getCpf() != null && !entidade.getCpf().trim().isEmpty() &&
               validarCpf(entidade.getCpf()) &&
               entidade.getSalario() != null && entidade.getSalario().compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public Funcionario pesquisarPorCpf(String cpf) throws Exception {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new Exception("CPF não pode ser vazio");
        }
        
        if (!validarCpf(cpf)) {
            throw new Exception("CPF inválido");
        }
        
        return repositorio.pesquisarPorCpf(cpf);
    }

    @Override
    public List<Funcionario> pesquisarPorNome(String nome) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("Nome não pode ser vazio");
        }
        
        return repositorio.pesquisarPorNome(nome);
    }

    @Override
    public List<Funcionario> listarAtivos() throws Exception {
        return repositorio.listarAtivos();
    }

    @Override
    public List<Funcionario> listarInativos() throws Exception {
        return repositorio.listarInativos();
    }

    @Override
    public List<Funcionario> listarPorCargo(String cargo) throws Exception {
        if (cargo == null || cargo.trim().isEmpty()) {
            throw new Exception("Cargo não pode ser vazio");
        }
        
        return repositorio.listarPorCargo(cargo);
    }

    @Override
    public List<Funcionario> listarVendedores() throws Exception {
        return repositorio.listarVendedores();
    }

    @Override
    public boolean existePorCpf(String cpf) throws Exception {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new Exception("CPF não pode ser vazio");
        }
        
        if (!validarCpf(cpf)) {
            throw new Exception("CPF inválido");
        }
        
        return repositorio.existePorCpf(cpf);
    }

    @Override
    public boolean ativar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        Funcionario funcionario = repositorio.pesquisarPorId(id);
        if (funcionario == null) {
            throw new Exception("Funcionário com ID " + id + " não encontrado");
        }
        
        funcionario.setAtivo(true);
        return repositorio.atualizar(funcionario);
    }

    @Override
    public boolean desativar(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        Funcionario funcionario = repositorio.pesquisarPorId(id);
        if (funcionario == null) {
            throw new Exception("Funcionário com ID " + id + " não encontrado");
        }
        
        funcionario.setAtivo(false);
        return repositorio.atualizar(funcionario);
    }

    @Override
    public double calcularComissao(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        Funcionario funcionario = repositorio.pesquisarPorId(id);
        if (funcionario == null) {
            throw new Exception("Funcionário com ID " + id + " não encontrado");
        }
        
        return funcionario.getComissaoTotal();
    }

    /**
     * Valida o formato do CPF (simplificado).
     * @param cpf CPF a ser validado
     * @return true se válido, false caso contrário
     */
    private boolean validarCpf(String cpf) {
        if (cpf == null) {
            return false;
        }
        
        // Remove caracteres não numéricos
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        
        // Verifica se tem 11 dígitos
        if (cpfLimpo.length() != 11) {
            return false;
        }
        
        // Verifica se não são todos os dígitos iguais
        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        return true;
    }
}
