package com.floricultura;

import com.floricultura.view.MenuPrincipal;

/**
 * Classe principal do Sistema de Floricultura.
 * Ponto de entrada da aplicação.
 */
public class Main {
    
    /**
     * Método principal que inicia o sistema.
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        try {
            // Exibir banner de inicialização
            exibirBannerInicial();
            
            // Criar e exibir menu principal
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            menuPrincipal.exibirMenuPrincipal();
            
        } catch (Exception e) {
            System.err.println("❌ Erro crítico ao inicializar o sistema: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * Exibe o banner inicial do sistema.
     */
    private static void exibirBannerInicial() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    🌹 SISTEMA DE FLORICULTURA 🌹");
        System.out.println("=".repeat(70));
        System.out.println("  Sistema de Gerenciamento Completo para Floriculturas");
        System.out.println("  Desenvolvido com Arquitetura MVC em Java");
        System.out.println("  Versão 1.0.0 - 2024");
        System.out.println("=".repeat(70));
        System.out.println("  🚀 Inicializando sistema...");
        System.out.println("  ✅ Sistema carregado com sucesso!");
        System.out.println("  📋 Bem-vindo ao menu principal!");
        System.out.println("=".repeat(70));
    }
}
