package com.floricultura.model.entities;

/**
 * Classe abstrata base para representar uma pessoa no sistema de floricultura.
 * Implementa herança para Cliente e Funcionario.
 */
public abstract class Pessoa {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;

    // Construtor padrão
    public Pessoa() {
    }

    // Construtor com parâmetros
    public Pessoa(int id, String nome, String telefone, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
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

    // Método abstrato para validação específica de cada tipo de pessoa
    public abstract boolean validarDados();

    // Método polimórfico para obter informações específicas
    public abstract String getInformacoesEspecificas();

    @Override
    public String toString() {
        return "ID: " + id + 
               ", Nome: " + nome + 
               ", Telefone: " + telefone + 
               ", Email: " + email + 
               ", Endereço: " + endereco;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pessoa pessoa = (Pessoa) obj;
        return id == pessoa.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
