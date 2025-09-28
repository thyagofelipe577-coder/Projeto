package com.floricultura.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa um fornecedor no sistema de floricultura.
 */
public class Fornecedor {
    private int id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private String endereco;
    private String contatoResponsavel;
    private LocalDate dataCadastro;
    private List<Flor> produtosFornecidos;
    private boolean ativo;

    // Construtor padrão
    public Fornecedor() {
        this.produtosFornecidos = new ArrayList<>();
        this.ativo = true;
        this.dataCadastro = LocalDate.now();
    }

    // Construtor com parâmetros
    public Fornecedor(int id, String nome, String cnpj, String telefone, String email, 
                      String endereco, String contatoResponsavel, LocalDate dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.contatoResponsavel = contatoResponsavel;
        this.dataCadastro = dataCadastro;
        this.produtosFornecidos = new ArrayList<>();
        this.ativo = true;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContatoResponsavel() {
        return contatoResponsavel;
    }

    public void setContatoResponsavel(String contatoResponsavel) {
        this.contatoResponsavel = contatoResponsavel;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Flor> getProdutosFornecidos() {
        return produtosFornecidos;
    }

    public void setProdutosFornecidos(List<Flor> produtosFornecidos) {
        this.produtosFornecidos = produtosFornecidos;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // Método para adicionar um produto fornecido
    public void adicionarProduto(Flor flor) {
        if (flor != null && !produtosFornecidos.contains(flor)) {
            produtosFornecidos.add(flor);
        }
    }

    // Método para remover um produto fornecido
    public boolean removerProduto(Flor flor) {
        return produtosFornecidos.remove(flor);
    }

    // Método para verificar se fornece um produto específico
    public boolean forneceProduto(Flor flor) {
        return produtosFornecidos.contains(flor);
    }

    // Método para validar dados do fornecedor
    public boolean validarDados() {
        return nome != null && !nome.trim().isEmpty() &&
               cnpj != null && !cnpj.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               telefone != null && !telefone.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "ID: " + id + 
               ", Nome: " + nome + 
               ", CNPJ: " + cnpj + 
               ", Telefone: " + telefone + 
               ", Email: " + email + 
               ", Endereço: " + endereco + 
               ", Contato: " + contatoResponsavel + 
               ", Data Cadastro: " + dataCadastro + 
               ", Ativo: " + (ativo ? "Sim" : "Não") + 
               ", Produtos: " + produtosFornecidos.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fornecedor fornecedor = (Fornecedor) obj;
        return id == fornecedor.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
