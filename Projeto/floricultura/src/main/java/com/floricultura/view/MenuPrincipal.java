package com.floricultura.view;

import com.floricultura.controller.*;
import com.floricultura.model.repositories.*;
import com.floricultura.model.services.*;
import java.util.Scanner;

/**
 * Classe responsável pela interface do usuário e menu principal do sistema.
 */
public class MenuPrincipal {
    private Scanner scanner;
    private FlorController florController;
    private ClienteController clienteController;
    private FornecedorController fornecedorController;
    private FuncionarioController funcionarioController;
    private VendaController vendaController;

    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
        inicializarSistema();
    }

    /**
     * Inicializa o sistema criando as instâncias dos repositórios, serviços e controladores.
     */
    private void inicializarSistema() {
        // Criar repositórios
        FlorRepositorio florRepositorio = new FlorRepositorio();
        ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
        FornecedorRepositorio fornecedorRepositorio = new FornecedorRepositorio();
        FuncionarioRepositorio funcionarioRepositorio = new FuncionarioRepositorio();
        VendaRepositorio vendaRepositorio = new VendaRepositorio();

        // Criar serviços
        FlorServico florServico = new FlorServico(florRepositorio);
        ClienteServico clienteServico = new ClienteServico(clienteRepositorio);
        FornecedorServico fornecedorServico = new FornecedorServico(fornecedorRepositorio);
        FuncionarioServico funcionarioServico = new FuncionarioServico(funcionarioRepositorio);
        VendaServico vendaServico = new VendaServico(vendaRepositorio, florServico, clienteServico, funcionarioServico);

        // Criar controladores
        this.florController = new FlorController(florServico);
        this.clienteController = new ClienteController(clienteServico);
        this.fornecedorController = new FornecedorController(fornecedorServico);
        this.funcionarioController = new FuncionarioController(funcionarioServico);
        this.vendaController = new VendaController(vendaServico, florServico, clienteServico, funcionarioServico);
    }

    /**
     * Exibe o menu principal e gerencia a navegação do usuário.
     */
    public void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("    SISTEMA DE FLORICULTURA - MENU PRINCIPAL");
            System.out.println("=".repeat(50));
            System.out.println("1. Gerenciar Flores");
            System.out.println("2. Gerenciar Clientes");
            System.out.println("3. Gerenciar Fornecedores");
            System.out.println("4. Gerenciar Funcionários");
            System.out.println("5. Gerenciar Vendas");
            System.out.println("6. Relatórios");
            System.out.println("7. Sobre o Sistema");
            System.out.println("0. Sair");
            System.out.println("=".repeat(50));
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                processarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Opção inválida! Digite um número válido.");
                opcao = -1;
            } catch (Exception e) {
                System.out.println("\n❌ Erro inesperado: " + e.getMessage());
                opcao = -1;
            }
        } while (opcao != 0);

        System.out.println("\n👋 Obrigado por usar o Sistema de Floricultura!");
        System.out.println("   Sistema desenvolvido com arquitetura MVC em Java");
    }

    /**
     * Processa a opção selecionada pelo usuário.
     * @param opcao Opção selecionada
     */
    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("\n🌹 Acessando módulo de Flores...");
                florController.exibirMenu();
                break;
            case 2:
                System.out.println("\n👥 Acessando módulo de Clientes...");
                clienteController.exibirMenu();
                break;
            case 3:
                System.out.println("\n🏢 Acessando módulo de Fornecedores...");
                fornecedorController.exibirMenu();
                break;
            case 4:
                System.out.println("\n👨‍💼 Acessando módulo de Funcionários...");
                funcionarioController.exibirMenu();
                break;
            case 5:
                System.out.println("\n💰 Acessando módulo de Vendas...");
                vendaController.exibirMenu();
                break;
            case 6:
                exibirRelatorios();
                break;
            case 7:
                exibirSobreSistema();
                break;
            case 0:
                System.out.println("\n🔄 Encerrando sistema...");
                break;
            default:
                System.out.println("\n❌ Opção inválida! Escolha uma opção de 0 a 7.");
        }
    }

    /**
     * Exibe o menu de relatórios.
     */
    private void exibirRelatorios() {
        int opcao;
        do {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("        RELATÓRIOS DO SISTEMA");
            System.out.println("=".repeat(40));
            System.out.println("1. Resumo Geral do Sistema");
            System.out.println("2. Estatísticas de Vendas");
            System.out.println("3. Relatório de Estoque");
            System.out.println("4. Clientes VIP");
            System.out.println("5. Funcionários Mais Produtivos");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println("=".repeat(40));
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                processarOpcaoRelatorios(opcao);
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Opção inválida! Digite um número válido.");
                opcao = -1;
            } catch (Exception e) {
                System.out.println("\n❌ Erro ao gerar relatório: " + e.getMessage());
                opcao = -1;
            }
        } while (opcao != 0);
    }

    /**
     * Processa as opções do menu de relatórios.
     * @param opcao Opção selecionada
     */
    private void processarOpcaoRelatorios(int opcao) {
        switch (opcao) {
            case 1:
                gerarResumoGeral();
                break;
            case 2:
                gerarEstatisticasVendas();
                break;
            case 3:
                gerarRelatorioEstoque();
                break;
            case 4:
                gerarRelatorioClientesVip();
                break;
            case 5:
                gerarRelatorioFuncionariosProdutivos();
                break;
            case 0:
                System.out.println("\n🔄 Retornando ao menu principal...");
                break;
            default:
                System.out.println("\n❌ Opção inválida! Escolha uma opção de 0 a 5.");
        }
    }

    /**
     * Gera resumo geral do sistema.
     */
    private void gerarResumoGeral() {
        System.out.println("\n📊 RESUMO GERAL DO SISTEMA");
        System.out.println("=".repeat(50));
        try {
            System.out.println("🌹 Total de Flores: " + florController.listarTodos().size());
            System.out.println("👥 Total de Clientes: " + clienteController.listarTodos().size());
            System.out.println("🏢 Total de Fornecedores: " + fornecedorController.listarTodos().size());
            System.out.println("👨‍💼 Total de Funcionários: " + funcionarioController.listarTodos().size());
            System.out.println("💰 Total de Vendas: " + vendaController.listarTodos().size());
        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar resumo: " + e.getMessage());
        }
    }

    /**
     * Gera estatísticas de vendas.
     */
    private void gerarEstatisticasVendas() {
        System.out.println("\n💰 ESTATÍSTICAS DE VENDAS");
        System.out.println("=".repeat(50));
        try {
            int totalVendas = vendaController.listarTodos().size();
            int vendasFinalizadas = vendaController.listarFinalizadas().size();
            int vendasPendentes = vendaController.listarPendentes().size();
            
            System.out.println("📈 Total de Vendas: " + totalVendas);
            System.out.println("✅ Vendas Finalizadas: " + vendasFinalizadas);
            System.out.println("⏳ Vendas Pendentes: " + vendasPendentes);
            
            if (totalVendas > 0) {
                double percentualFinalizadas = (double) vendasFinalizadas / totalVendas * 100;
                System.out.println("📊 Taxa de Finalização: " + String.format("%.1f", percentualFinalizadas) + "%");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar estatísticas: " + e.getMessage());
        }
    }

    /**
     * Gera relatório de estoque.
     */
    private void gerarRelatorioEstoque() {
        System.out.println("\n📦 RELATÓRIO DE ESTOQUE");
        System.out.println("=".repeat(50));
        try {
            int totalFlores = florController.listarTodos().size();
            int floresDisponiveis = florController.listarDisponiveis().size();
            int floresEstoqueBaixo = florController.listarComEstoqueBaixo(5).size();
            
            System.out.println("🌹 Total de Flores: " + totalFlores);
            System.out.println("✅ Flores Disponíveis: " + floresDisponiveis);
            System.out.println("⚠️ Flores com Estoque Baixo (≤5): " + floresEstoqueBaixo);
            
            if (totalFlores > 0) {
                double percentualDisponiveis = (double) floresDisponiveis / totalFlores * 100;
                System.out.println("📊 Taxa de Disponibilidade: " + String.format("%.1f", percentualDisponiveis) + "%");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar relatório de estoque: " + e.getMessage());
        }
    }

    /**
     * Gera relatório de clientes VIP.
     */
    private void gerarRelatorioClientesVip() {
        System.out.println("\n👑 RELATÓRIO DE CLIENTES VIP");
        System.out.println("=".repeat(50));
        try {
            int totalClientes = clienteController.listarTodos().size();
            int clientesVip = clienteController.listarClientesVip().size();
            
            System.out.println("👥 Total de Clientes: " + totalClientes);
            System.out.println("👑 Clientes VIP: " + clientesVip);
            
            if (totalClientes > 0) {
                double percentualVip = (double) clientesVip / totalClientes * 100;
                System.out.println("📊 Taxa de Clientes VIP: " + String.format("%.1f", percentualVip) + "%");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar relatório de clientes VIP: " + e.getMessage());
        }
    }

    /**
     * Gera relatório de funcionários mais produtivos.
     */
    private void gerarRelatorioFuncionariosProdutivos() {
        System.out.println("\n🏆 FUNCIONÁRIOS MAIS PRODUTIVOS");
        System.out.println("=".repeat(50));
        try {
            int totalFuncionarios = funcionarioController.listarTodos().size();
            int vendedores = funcionarioController.listarVendedores().size();
            
            System.out.println("👨‍💼 Total de Funcionários: " + totalFuncionarios);
            System.out.println("💼 Vendedores: " + vendedores);
            
            if (totalFuncionarios > 0) {
                double percentualVendedores = (double) vendedores / totalFuncionarios * 100;
                System.out.println("📊 Taxa de Vendedores: " + String.format("%.1f", percentualVendedores) + "%");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar relatório de funcionários: " + e.getMessage());
        }
    }

    /**
     * Exibe informações sobre o sistema.
     */
    private void exibirSobreSistema() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("              SOBRE O SISTEMA DE FLORICULTURA");
        System.out.println("=".repeat(60));
        System.out.println("🌹 Sistema de Gerenciamento de Floricultura");
        System.out.println("📅 Versão: 1.0.0");
        System.out.println("🏗️ Arquitetura: MVC (Model-View-Controller)");
        System.out.println("☕ Linguagem: Java");
        System.out.println("📦 Estrutura: ArrayList para persistência");
        System.out.println("");
        System.out.println("🔧 Funcionalidades:");
        System.out.println("   • Gerenciamento completo de flores");
        System.out.println("   • Cadastro e controle de clientes");
        System.out.println("   • Gestão de fornecedores");
        System.out.println("   • Administração de funcionários");
        System.out.println("   • Sistema de vendas integrado");
        System.out.println("   • Relatórios e estatísticas");
        System.out.println("");
        System.out.println("🎯 Características Técnicas:");
        System.out.println("   • Herança (Classe Pessoa abstrata)");
        System.out.println("   • Polimorfismo em métodos das classes herdadas");
        System.out.println("   • Encapsulamento com getters/setters");
        System.out.println("   • Interfaces para repositórios e serviços");
        System.out.println("   • Tratamento de exceções");
        System.out.println("   • CRUD completo para todas as entidades");
        System.out.println("");
        System.out.println("👨‍💻 Desenvolvido com foco em boas práticas de POO");
        System.out.println("=".repeat(60));
        
        System.out.print("\nPressione Enter para voltar ao menu principal...");
        scanner.nextLine();
    }
}
