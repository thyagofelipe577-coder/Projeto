package com.floricultura.model.entities;

/**
 * Classe que representa um item de venda no sistema de floricultura.
 */
public class ItemVenda {
    private Flor flor;
    private int quantidade;
    private double precoUnitario;
    private double subtotal;

    // Construtor padrão
    public ItemVenda() {
    }

    // Construtor com parâmetros
    public ItemVenda(Flor flor, int quantidade) {
        this.flor = flor;
        this.quantidade = quantidade;
        this.precoUnitario = flor.getPreco();
        this.subtotal = quantidade * precoUnitario;
    }

    // Getters e Setters
    public Flor getFlor() {
        return flor;
    }

    public void setFlor(Flor flor) {
        this.flor = flor;
        if (flor != null) {
            this.precoUnitario = flor.getPreco();
            calcularSubtotal();
        }
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        calcularSubtotal();
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
        calcularSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    // Método para calcular subtotal
    private void calcularSubtotal() {
        this.subtotal = quantidade * precoUnitario;
    }

    // Método para verificar se há estoque suficiente
    public boolean verificarEstoque() {
        return flor != null && flor.getQuantidadeEstoque() >= quantidade;
    }

    // Método para atualizar estoque após venda
    public boolean atualizarEstoque() {
        if (flor != null && verificarEstoque()) {
            return flor.reduzirEstoque(quantidade);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Flor: " + (flor != null ? flor.getNome() : "N/A") + 
               ", Quantidade: " + quantidade + 
               ", Preço Unitário: R$ " + String.format("%.2f", precoUnitario) + 
               ", Subtotal: R$ " + String.format("%.2f", subtotal);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ItemVenda itemVenda = (ItemVenda) obj;
        return flor != null && flor.equals(itemVenda.flor);
    }

    @Override
    public int hashCode() {
        return flor != null ? flor.hashCode() : 0;
    }
}
