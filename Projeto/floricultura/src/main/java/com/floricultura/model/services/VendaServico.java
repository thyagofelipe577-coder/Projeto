package com.floricultura.model.services;

import com.floricultura.model.entities.*;
import com.floricultura.model.interfaces.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementação do serviço de vendas com regras de negócio.
 */
public class VendaServico implements IVendaServico {
    private IVendaRepositorio repositorio;
    private IFlorServico florServico;
    private IClienteServico clienteServico;
    private IFuncionarioServico funcionarioServico;

    public VendaServico(IVendaRepositorio repositorio, IFlorServico florServico, 
                       IClienteServico clienteServico, IFuncionarioServico funcionarioServico) {
        this.repositorio = repositorio;
        this.florServico = florServico;
        this.clienteServico = clienteServico;
        this.funcionarioServico = funcionarioServico;
    }

    @Override
    public boolean cadastrar(Venda entidade) throws Exception {
        if (!validar(entidade)) {
            throw new Exception("Dados da venda inválidos");
        }
        
        if (repositorio.existe(entidade.getId())) {
            throw new Exception("Venda com ID " + entidade.getId() + " já existe");
        }
        
        return repositorio.cadastrar(entidade);
    }

    @Override
    public boolean remover(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        if (!repositorio.existe(id)) {
            throw new Exception("Venda com ID " + id + " não encontrada");
        }
        
        Venda venda = repositorio.pesquisarPorId(id);
        if (venda.isFinalizada()) {
            throw new Exception("Não é possível remover uma venda finalizada");
        }
        
        return repositorio.remover(id);
    }

    @Override
    public boolean atualizar(Venda entidade) throws Exception {
        if (!validar(entidade)) {
            throw new Exception("Dados da venda inválidos");
        }
        
        if (!repositorio.existe(entidade.getId())) {
            throw new Exception("Venda com ID " + entidade.getId() + " não encontrada");
        }
        
        Venda vendaExistente = repositorio.pesquisarPorId(entidade.getId());
        if (vendaExistente.isFinalizada()) {
            throw new Exception("Não é possível atualizar uma venda finalizada");
        }
        
        return repositorio.atualizar(entidade);
    }

    @Override
    public Venda pesquisarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        return repositorio.pesquisarPorId(id);
    }

    @Override
    public List<Venda> listarTodos() throws Exception {
        return repositorio.listarTodos();
    }

    @Override
    public boolean validar(Venda entidade) {
        if (entidade == null) {
            return false;
        }
        
        return entidade.getCliente() != null &&
               entidade.getFuncionario() != null &&
               entidade.getDataVenda() != null &&
               entidade.getItens() != null &&
               !entidade.getItens().isEmpty();
    }

    @Override
    public List<Venda> listarPorCliente(int clienteId) throws Exception {
        if (clienteId <= 0) {
            throw new Exception("ID do cliente inválido");
        }
        
        return repositorio.listarPorCliente(clienteId);
    }

    @Override
    public List<Venda> listarPorFuncionario(int funcionarioId) throws Exception {
        if (funcionarioId <= 0) {
            throw new Exception("ID do funcionário inválido");
        }
        
        return repositorio.listarPorFuncionario(funcionarioId);
    }

    @Override
    public List<Venda> listarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) throws Exception {
        if (dataInicio == null || dataFim == null) {
            throw new Exception("Datas não podem ser nulas");
        }
        
        if (dataInicio.isAfter(dataFim)) {
            throw new Exception("Data de início não pode ser posterior à data de fim");
        }
        
        return repositorio.listarPorPeriodo(dataInicio, dataFim);
    }

    @Override
    public List<Venda> listarPorData(LocalDate data) throws Exception {
        if (data == null) {
            throw new Exception("Data não pode ser nula");
        }
        
        return repositorio.listarPorData(data);
    }

    @Override
    public List<Venda> listarFinalizadas() throws Exception {
        return repositorio.listarFinalizadas();
    }

    @Override
    public List<Venda> listarPendentes() throws Exception {
        return repositorio.listarPendentes();
    }

    @Override
    public double calcularTotalVendas(LocalDateTime dataInicio, LocalDateTime dataFim) throws Exception {
        if (dataInicio == null || dataFim == null) {
            throw new Exception("Datas não podem ser nulas");
        }
        
        if (dataInicio.isAfter(dataFim)) {
            throw new Exception("Data de início não pode ser posterior à data de fim");
        }
        
        return repositorio.calcularTotalVendas(dataInicio, dataFim);
    }

    @Override
    public boolean finalizarVenda(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        Venda venda = repositorio.pesquisarPorId(id);
        if (venda == null) {
            throw new Exception("Venda com ID " + id + " não encontrada");
        }
        
        if (venda.isFinalizada()) {
            throw new Exception("Venda já está finalizada");
        }
        
        if (venda.isVazia()) {
            throw new Exception("Não é possível finalizar uma venda vazia");
        }
        
        // Verificar disponibilidade de estoque para todos os itens
        for (ItemVenda item : venda.getItens()) {
            if (!item.verificarEstoque()) {
                throw new Exception("Estoque insuficiente para o item: " + item.getFlor().getNome());
            }
        }
        
        // Atualizar estoque
        for (ItemVenda item : venda.getItens()) {
            if (!item.atualizarEstoque()) {
                throw new Exception("Erro ao atualizar estoque para o item: " + item.getFlor().getNome());
            }
        }
        
        // Finalizar venda
        boolean sucesso = venda.finalizarVenda();
        if (sucesso) {
            repositorio.atualizar(venda);
        }
        
        return sucesso;
    }

    @Override
    public boolean adicionarItem(int vendaId, int florId, int quantidade) throws Exception {
        if (vendaId <= 0) {
            throw new Exception("ID da venda inválido");
        }
        
        if (florId <= 0) {
            throw new Exception("ID da flor inválido");
        }
        
        if (quantidade <= 0) {
            throw new Exception("Quantidade deve ser maior que zero");
        }
        
        Venda venda = repositorio.pesquisarPorId(vendaId);
        if (venda == null) {
            throw new Exception("Venda com ID " + vendaId + " não encontrada");
        }
        
        if (venda.isFinalizada()) {
            throw new Exception("Não é possível adicionar itens a uma venda finalizada");
        }
        
        Flor flor = florServico.pesquisarPorId(florId);
        if (flor == null) {
            throw new Exception("Flor com ID " + florId + " não encontrada");
        }
        
        if (!florServico.verificarDisponibilidade(florId, quantidade)) {
            throw new Exception("Estoque insuficiente para a flor: " + flor.getNome());
        }
        
        ItemVenda item = new ItemVenda(flor, quantidade);
        venda.adicionarItem(item);
        
        return repositorio.atualizar(venda);
    }

    @Override
    public boolean removerItem(int vendaId, int florId) throws Exception {
        if (vendaId <= 0) {
            throw new Exception("ID da venda inválido");
        }
        
        if (florId <= 0) {
            throw new Exception("ID da flor inválido");
        }
        
        Venda venda = repositorio.pesquisarPorId(vendaId);
        if (venda == null) {
            throw new Exception("Venda com ID " + vendaId + " não encontrada");
        }
        
        if (venda.isFinalizada()) {
            throw new Exception("Não é possível remover itens de uma venda finalizada");
        }
        
        ItemVenda itemParaRemover = null;
        for (ItemVenda item : venda.getItens()) {
            if (item.getFlor().getId() == florId) {
                itemParaRemover = item;
                break;
            }
        }
        
        if (itemParaRemover == null) {
            throw new Exception("Item com flor ID " + florId + " não encontrado na venda");
        }
        
        boolean sucesso = venda.removerItem(itemParaRemover);
        if (sucesso) {
            repositorio.atualizar(venda);
        }
        
        return sucesso;
    }
}
