package com.floricultura.model.entities;

import java.time.LocalDate;

/**
 * Entidade que representa uma flor no sistema de floricultura.
 */
public class Flor {
    private int id;
    private String nome;
    private String especie;
    private String cor;
    private double preco;
    private int quantidadeEstoque;
    private LocalDate dataPlantio;
    private String descricao;
    private boolean disponivel;

    // Construtor padrão
    public Flor() {
        this.disponivel = true;
    }

    // Construtor com parâmetros
    public Flor(int id, String nome, String especie, String cor, double preco, 
                int quantidadeEstoque, LocalDate dataPlantio, String descricao) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.cor = cor;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.dataPlantio = dataPlantio;
        this.descricao = descricao;
        this.disponivel = true;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public LocalDate getDataPlantio() {
        return dataPlantio;
    }

    public void setDataPlantio(LocalDate dataPlantio) {
        this.dataPlantio = dataPlantio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // Método para verificar se a flor está disponível para venda
    public boolean estaDisponivelParaVenda() {
        return disponivel && quantidadeEstoque > 0;
    }

    // Método para reduzir estoque
    public boolean reduzirEstoque(int quantidade) {
        if (quantidade > 0 && quantidade <= quantidadeEstoque) {
            quantidadeEstoque -= quantidade;
            if (quantidadeEstoque == 0) {
                disponivel = false;
            }
            return true;
        }
        return false;
    }

    // Método para adicionar estoque
    public void adicionarEstoque(int quantidade) {
        if (quantidade > 0) {
            quantidadeEstoque += quantidade;
            if (quantidadeEstoque > 0) {
                disponivel = true;
            }
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + 
               ", Nome: " + nome + 
               ", Espécie: " + especie + 
               ", Cor: " + cor + 
               ", Preço: R$ " + String.format("%.2f", preco) + 
               ", Estoque: " + quantidadeEstoque + 
               ", Data Plantio: " + dataPlantio + 
               ", Disponível: " + (disponivel ? "Sim" : "Não");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Flor flor = (Flor) obj;
        return id == flor.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
