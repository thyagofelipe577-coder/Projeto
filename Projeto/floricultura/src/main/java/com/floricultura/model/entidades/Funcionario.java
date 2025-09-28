package com.floricultura.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa um funcionário no sistema de floricultura.
 * Herda da classe abstrata Pessoa.
 */
public class Funcionario extends Pessoa {
    private String cpf;
    private String cargo;
    private BigDecimal salario;
    private LocalDate dataAdmissao;
    private LocalDate dataNascimento;
    private List<Venda> vendasRealizadas;
    private double comissaoTotal;
    private boolean ativo;

    // Construtor padrão
    public Funcionario() {
        super();
        this.vendasRealizadas = new ArrayList<>();
        this.comissaoTotal = 0.0;
        this.ativo = true;
    }

    // Construtor com parâmetros
    public Funcionario(int id, String nome, String telefone, String email, String endereco,
                       String cpf, String cargo, BigDecimal salario, LocalDate dataAdmissao, 
                       LocalDate dataNascimento) {
        super(id, nome, telefone, email, endereco);
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
        this.dataNascimento = dataNascimento;
        this.vendasRealizadas = new ArrayList<>();
        this.comissaoTotal = 0.0;
        this.ativo = true;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Venda> getVendasRealizadas() {
        return vendasRealizadas;
    }

    public void setVendasRealizadas(List<Venda> vendasRealizadas) {
        this.vendasRealizadas = vendasRealizadas;
    }

    public double getComissaoTotal() {
        return comissaoTotal;
    }

    public void setComissaoTotal(double comissaoTotal) {
        this.comissaoTotal = comissaoTotal;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // Método para adicionar uma venda realizada
    public void adicionarVenda(Venda venda) {
        if (venda != null) {
            vendasRealizadas.add(venda);
            calcularComissao(venda);
        }
    }

    // Método para calcular comissão (5% do valor da venda)
    private void calcularComissao(Venda venda) {
        double comissao = venda.getValorTotal() * 0.05;
        comissaoTotal += comissao;
    }

    // Método para calcular tempo de empresa em anos
    public int calcularTempoEmpresa() {
        if (dataAdmissao != null) {
            return LocalDate.now().getYear() - dataAdmissao.getYear();
        }
        return 0;
    }

    // Método para calcular idade
    public int calcularIdade() {
        if (dataNascimento != null) {
            return LocalDate.now().getYear() - dataNascimento.getYear();
        }
        return 0;
    }

    // Método para verificar se é vendedor
    public boolean isVendedor() {
        return cargo != null && cargo.toLowerCase().contains("vendedor");
    }

    // Implementação do método abstrato da classe pai
    @Override
    public boolean validarDados() {
        return getNome() != null && !getNome().trim().isEmpty() &&
               cpf != null && !cpf.trim().isEmpty() &&
               getEmail() != null && !getEmail().trim().isEmpty() &&
               getTelefone() != null && !getTelefone().trim().isEmpty() &&
               cargo != null && !cargo.trim().isEmpty() &&
               salario != null && salario.compareTo(BigDecimal.ZERO) > 0;
    }

    // Implementação do método abstrato da classe pai
    @Override
    public String getInformacoesEspecificas() {
        return "CPF: " + cpf + 
               ", Cargo: " + cargo + 
               ", Salário: R$ " + String.format("%.2f", salario) + 
               ", Data Admissão: " + dataAdmissao + 
               ", Idade: " + calcularIdade() + 
               ", Tempo Empresa: " + calcularTempoEmpresa() + " anos" +
               ", Vendas: " + vendasRealizadas.size() + 
               ", Comissão Total: R$ " + String.format("%.2f", comissaoTotal) +
               ", Ativo: " + (ativo ? "Sim" : "Não");
    }

    @Override
    public String toString() {
        return super.toString() + ", " + getInformacoesEspecificas();
    }
}
