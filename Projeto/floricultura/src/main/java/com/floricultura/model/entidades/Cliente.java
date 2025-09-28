package com.floricultura.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa um cliente no sistema de floricultura.
 * Herda da classe abstrata Pessoa.
 */
public class Cliente extends Pessoa {
    private LocalDate dataCadastro;
    private String cpf;
    private List<Venda> historicoCompras;
    private double totalGasto;
    private boolean clienteVip;

    // Construtor padrão
    public Cliente() {
        super();
        this.historicoCompras = new ArrayList<>();
        this.totalGasto = 0.0;
        this.clienteVip = false;
        this.dataCadastro = LocalDate.now();
    }

    // Construtor com parâmetros
    public Cliente(int id, String nome, String telefone, String email, String endereco, 
                   String cpf, LocalDate dataCadastro) {
        super(id, nome, telefone, email, endereco);
        this.cpf = cpf;
        this.dataCadastro = dataCadastro;
        this.historicoCompras = new ArrayList<>();
        this.totalGasto = 0.0;
        this.clienteVip = false;
    }

    // Getters e Setters
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Venda> getHistoricoCompras() {
        return historicoCompras;
    }

    public void setHistoricoCompras(List<Venda> historicoCompras) {
        this.historicoCompras = historicoCompras;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }

    public boolean isClienteVip() {
        return clienteVip;
    }

    public void setClienteVip(boolean clienteVip) {
        this.clienteVip = clienteVip;
    }

    // Método para adicionar uma compra ao histórico
    public void adicionarCompra(Venda venda) {
        if (venda != null) {
            historicoCompras.add(venda);
            totalGasto += venda.getValorTotal();
            verificarStatusVip();
        }
    }

    // Método para verificar se o cliente deve ser VIP (gastou mais de R$ 1000)
    private void verificarStatusVip() {
        this.clienteVip = totalGasto >= 1000.0;
    }

    // Método para calcular desconto VIP
    public double calcularDescontoVip(double valor) {
        if (clienteVip) {
            return valor * 0.1; // 10% de desconto para clientes VIP
        }
        return 0.0;
    }

    // Implementação do método abstrato da classe pai
    @Override
    public boolean validarDados() {
        return getNome() != null && !getNome().trim().isEmpty() &&
               getCpf() != null && !getCpf().trim().isEmpty() &&
               getEmail() != null && !getEmail().trim().isEmpty() &&
               getTelefone() != null && !getTelefone().trim().isEmpty();
    }

    // Implementação do método abstrato da classe pai
    @Override
    public String getInformacoesEspecificas() {
        return "CPF: " + cpf + 
               ", Data Cadastro: " + dataCadastro + 
               ", Total Gasto: R$ " + String.format("%.2f", totalGasto) + 
               ", Status: " + (clienteVip ? "VIP" : "Regular") + 
               ", Compras: " + historicoCompras.size();
    }

    @Override
    public String toString() {
        return super.toString() + ", " + getInformacoesEspecificas();
    }
}
