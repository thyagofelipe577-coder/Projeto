package com.floricultura.model.repositories;

import com.floricultura.model.entities.Venda;
import com.floricultura.model.interfaces.IVendaRepositorio;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do repositório de vendas usando ArrayList.
 */
public class VendaRepositorio extends RepositorioBase<Venda> implements IVendaRepositorio {

    @Override
    protected int obterId(Venda entidade) {
        return entidade.getId();
    }

    @Override
    protected void definirId(Venda entidade) {
        entidade.setId(proximoId);
    }

    @Override
    public List<Venda> listarPorCliente(int clienteId) {
        return entidades.stream()
                .filter(venda -> venda.getCliente() != null && 
                               venda.getCliente().getId() == clienteId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venda> listarPorFuncionario(int funcionarioId) {
        return entidades.stream()
                .filter(venda -> venda.getFuncionario() != null && 
                               venda.getFuncionario().getId() == funcionarioId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venda> listarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        if (dataInicio == null || dataFim == null || dataInicio.isAfter(dataFim)) {
            return new ArrayList<>();
        }
        
        return entidades.stream()
                .filter(venda -> venda.getDataVenda() != null &&
                               !venda.getDataVenda().isBefore(dataInicio) &&
                               !venda.getDataVenda().isAfter(dataFim))
                .collect(Collectors.toList());
    }

    @Override
    public List<Venda> listarPorData(LocalDate data) {
        if (data == null) {
            return new ArrayList<>();
        }
        
        return entidades.stream()
                .filter(venda -> venda.getDataVenda() != null &&
                               venda.getDataVenda().toLocalDate().equals(data))
                .collect(Collectors.toList());
    }

    @Override
    public List<Venda> listarFinalizadas() {
        return entidades.stream()
                .filter(Venda::isFinalizada)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venda> listarPendentes() {
        return entidades.stream()
                .filter(venda -> !venda.isFinalizada())
                .collect(Collectors.toList());
    }

    @Override
    public double calcularTotalVendas(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return listarPorPeriodo(dataInicio, dataFim).stream()
                .mapToDouble(Venda::getValorTotal)
                .sum();
    }
}
