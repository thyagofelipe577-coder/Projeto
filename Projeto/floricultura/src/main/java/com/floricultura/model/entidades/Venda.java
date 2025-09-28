package com.floricultura.model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa uma venda no sistema de floricultura.
 */
public class Venda {
    private int id;
    private Cliente cliente;
    private Funcionario funcionario;
    private LocalDateTime dataVenda;
    private List<ItemVenda> itens;
    private double valorTotal;
    private double desconto;
    private String formaPagamento;
    private String observacoes;
    private boolean finalizada;

    // Construtor padrão
    public Venda() {
        this.itens = new ArrayList<>();
        this.dataVenda = LocalDateTime.now();
        this.valorTotal = 0.0;
        this.desconto = 0.0;
        this.finalizada = false;
    }

    // Construtor com parâmetros
    public Venda(int id, Cliente cliente, Funcionario funcionario, LocalDateTime dataVenda) {
        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.dataVenda = dataVenda;
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
        this.desconto = 0.0;
        this.finalizada = false;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    // Método para adicionar item à venda
    public void adicionarItem(ItemVenda item) {
        if (item != null && !finalizada) {
            itens.add(item);
            calcularValorTotal();
        }
    }

    // Método para remover item da venda
    public boolean removerItem(ItemVenda item) {
        if (!finalizada && itens.remove(item)) {
            calcularValorTotal();
            return true;
        }
        return false;
    }

    // Método para calcular valor total da venda
    public void calcularValorTotal() {
        valorTotal = 0.0;
        for (ItemVenda item : itens) {
            valorTotal += item.getSubtotal();
        }
        // Aplicar desconto se houver
        if (desconto > 0) {
            valorTotal -= desconto;
        }
    }

    // Método para aplicar desconto
    public void aplicarDesconto(double percentualDesconto) {
        if (percentualDesconto > 0 && percentualDesconto <= 100) {
            this.desconto = valorTotal * (percentualDesconto / 100);
            calcularValorTotal();
        }
    }

    // Método para finalizar venda
    public boolean finalizarVenda() {
        if (!itens.isEmpty() && !finalizada) {
            finalizada = true;
            // Adicionar venda ao histórico do cliente
            if (cliente != null) {
                cliente.adicionarCompra(this);
            }
            // Adicionar venda ao histórico do funcionário
            if (funcionario != null) {
                funcionario.adicionarVenda(this);
            }
            return true;
        }
        return false;
    }

    // Método para verificar se a venda está vazia
    public boolean isVazia() {
        return itens.isEmpty();
    }

    // Método para obter quantidade total de itens
    public int getQuantidadeTotalItens() {
        int total = 0;
        for (ItemVenda item : itens) {
            total += item.getQuantidade();
        }
        return total;
    }

    @Override
    public String toString() {
        return "ID: " + id + 
               ", Cliente: " + (cliente != null ? cliente.getNome() : "N/A") + 
               ", Funcionário: " + (funcionario != null ? funcionario.getNome() : "N/A") + 
               ", Data: " + dataVenda + 
               ", Itens: " + itens.size() + 
               ", Valor Total: R$ " + String.format("%.2f", valorTotal) + 
               ", Desconto: R$ " + String.format("%.2f", desconto) + 
               ", Forma Pagamento: " + formaPagamento + 
               ", Finalizada: " + (finalizada ? "Sim" : "Não");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Venda venda = (Venda) obj;
        return id == venda.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
