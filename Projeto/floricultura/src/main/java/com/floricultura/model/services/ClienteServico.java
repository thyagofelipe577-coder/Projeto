package com.floricultura.model.services;

import com.floricultura.model.entities.Cliente;
import com.floricultura.model.interfaces.IClienteRepositorio;
import com.floricultura.model.interfaces.IClienteServico;
import java.util.List;

/**
 * Implementação do serviço de clientes com regras de negócio.
 */
public class ClienteServico implements IClienteServico {
    private IClienteRepositorio repositorio;

    public ClienteServico(IClienteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public boolean cadastrar(Cliente entidade) throws Exception {
        if (!validar(entidade)) {
            throw new Exception("Dados do cliente inválidos");
        }
        
        if (repositorio.existe(entidade.getId())) {
            throw new Exception("Cliente com ID " + entidade.getId() + " já existe");
        }
        
        if (repositorio.existePorCpf(entidade.getCpf())) {
            throw new Exception("Cliente com CPF " + entidade.getCpf() + " já cadastrado");
        }
        
        return repositorio.cadastrar(entidade);
    }

    @Override
    public boolean remover(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        if (!repositorio.existe(id)) {
            throw new Exception("Cliente com ID " + id + " não encontrado");
        }
        
        return repositorio.remover(id);
    }

    @Override
    public boolean atualizar(Cliente entidade) throws Exception {
        if (!validar(entidade)) {
            throw new Exception("Dados do cliente inválidos");
        }
        
        if (!repositorio.existe(entidade.getId())) {
            throw new Exception("Cliente com ID " + entidade.getId() + " não encontrado");
        }
        
        // Verificar se o CPF já existe em outro cliente
        Cliente clienteExistente = repositorio.pesquisarPorCpf(entidade.getCpf());
        if (clienteExistente != null && clienteExistente.getId() != entidade.getId()) {
            throw new Exception("CPF " + entidade.getCpf() + " já cadastrado para outro cliente");
        }
        
        return repositorio.atualizar(entidade);
    }

    @Override
    public Cliente pesquisarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        return repositorio.pesquisarPorId(id);
    }

    @Override
    public List<Cliente> listarTodos() throws Exception {
        return repositorio.listarTodos();
    }

    @Override
    public boolean validar(Cliente entidade) {
        if (entidade == null) {
            return false;
        }
        
        return entidade.validarDados() &&
               entidade.getCpf() != null && !entidade.getCpf().trim().isEmpty() &&
               validarCpf(entidade.getCpf());
    }

    @Override
    public Cliente pesquisarPorCpf(String cpf) throws Exception {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new Exception("CPF não pode ser vazio");
        }
        
        if (!validarCpf(cpf)) {
            throw new Exception("CPF inválido");
        }
        
        return repositorio.pesquisarPorCpf(cpf);
    }

    @Override
    public List<Cliente> pesquisarPorNome(String nome) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("Nome não pode ser vazio");
        }
        
        return repositorio.pesquisarPorNome(nome);
    }

    @Override
    public List<Cliente> listarClientesVip() throws Exception {
        return repositorio.listarClientesVip();
    }

    @Override
    public List<Cliente> listarPorFaixaGasto(double gastoMinimo, double gastoMaximo) throws Exception {
        if (gastoMinimo < 0 || gastoMaximo < 0) {
            throw new Exception("Valores de gasto devem ser maiores ou iguais a zero");
        }
        
        if (gastoMinimo > gastoMaximo) {
            throw new Exception("Gasto mínimo não pode ser maior que o gasto máximo");
        }
        
        return repositorio.listarPorFaixaGasto(gastoMinimo, gastoMaximo);
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
    public boolean promoverParaVip(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        Cliente cliente = repositorio.pesquisarPorId(id);
        if (cliente == null) {
            throw new Exception("Cliente com ID " + id + " não encontrado");
        }
        
        cliente.setClienteVip(true);
        return repositorio.atualizar(cliente);
    }

    @Override
    public double calcularDescontoVip(int id, double valor) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        if (valor < 0) {
            throw new Exception("Valor deve ser maior ou igual a zero");
        }
        
        Cliente cliente = repositorio.pesquisarPorId(id);
        if (cliente == null) {
            throw new Exception("Cliente com ID " + id + " não encontrado");
        }
        
        return cliente.calcularDescontoVip(valor);
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
