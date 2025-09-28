package com.floricultura.controller;

import com.floricultura.model.entities.Flor;
import com.floricultura.model.interfaces.IFlorServico;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador para gerenciar operações relacionadas a flores.
 */
public class FlorController {
    private IFlorServico florServico;
    private Scanner scanner;

    public FlorController(IFlorServico florServico) {
        this.florServico = florServico;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Exibe o menu de operações para flores.
     */
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU DE FLORES ===");
            System.out.println("1. Cadastrar Flor");
            System.out.println("2. Listar Todas as Flores");
            System.out.println("3. Pesquisar Flor por ID");
            System.out.println("4. Pesquisar Flores por Nome");
            System.out.println("5. Pesquisar Flores por Espécie");
            System.out.println("6. Pesquisar Flores por Cor");
            System.out.println("7. Listar Flores Disponíveis");
            System.out.println("8. Listar Flores com Estoque Baixo");
            System.out.println("9. Pesquisar por Faixa de Preço");
            System.out.println("10. Atualizar Estoque");
            System.out.println("11. Atualizar Flor");
            System.out.println("12. Remover Flor");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                processarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                opcao = -1;
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                opcao = -1;
            }
        } while (opcao != 0);
    }

    private void processarOpcao(int opcao) throws Exception {
        switch (opcao) {
            case 1:
                cadastrarFlor();
                break;
            case 2:
                listarTodasFlores();
                break;
            case 3:
                pesquisarFlorPorId();
                break;
            case 4:
                pesquisarFloresPorNome();
                break;
            case 5:
                pesquisarFloresPorEspecie();
                break;
            case 6:
                pesquisarFloresPorCor();
                break;
            case 7:
                listarFloresDisponiveis();
                break;
            case 8:
                listarFloresComEstoqueBaixo();
                break;
            case 9:
                pesquisarPorFaixaPreco();
                break;
            case 10:
                atualizarEstoque();
                break;
            case 11:
                atualizarFlor();
                break;
            case 12:
                removerFlor();
                break;
            case 0:
                System.out.println("Retornando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void cadastrarFlor() throws Exception {
        System.out.println("\n=== CADASTRAR FLOR ===");
        
        System.out.print("Nome da flor: ");
        String nome = scanner.nextLine();
        
        System.out.print("Espécie: ");
        String especie = scanner.nextLine();
        
        System.out.print("Cor: ");
        String cor = scanner.nextLine();
        
        System.out.print("Preço: ");
        double preco = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Quantidade em estoque: ");
        int quantidadeEstoque = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Data de plantio (yyyy-mm-dd): ");
        LocalDate dataPlantio = LocalDate.parse(scanner.nextLine());
        
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        
        Flor flor = new Flor(0, nome, especie, cor, preco, quantidadeEstoque, dataPlantio, descricao);
        
        if (florServico.cadastrar(flor)) {
            System.out.println("Flor cadastrada com sucesso! ID: " + flor.getId());
        } else {
            System.out.println("Erro ao cadastrar flor.");
        }
    }

    private void listarTodasFlores() throws Exception {
        System.out.println("\n=== TODAS AS FLORES ===");
        List<Flor> flores = florServico.listarTodos();
        
        if (flores.isEmpty()) {
            System.out.println("Nenhuma flor cadastrada.");
        } else {
            for (Flor flor : flores) {
                System.out.println(flor);
            }
        }
    }

    private void pesquisarFlorPorId() throws Exception {
        System.out.println("\n=== PESQUISAR FLOR POR ID ===");
        System.out.print("Digite o ID da flor: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Flor flor = florServico.pesquisarPorId(id);
        if (flor != null) {
            System.out.println("Flor encontrada:");
            System.out.println(flor);
        } else {
            System.out.println("Flor não encontrada.");
        }
    }

    private void pesquisarFloresPorNome() throws Exception {
        System.out.println("\n=== PESQUISAR FLORES POR NOME ===");
        System.out.print("Digite o nome da flor: ");
        String nome = scanner.nextLine();
        
        List<Flor> flores = florServico.pesquisarPorNome(nome);
        if (flores.isEmpty()) {
            System.out.println("Nenhuma flor encontrada com esse nome.");
        } else {
            System.out.println("Flores encontradas:");
            for (Flor flor : flores) {
                System.out.println(flor);
            }
        }
    }

    private void pesquisarFloresPorEspecie() throws Exception {
        System.out.println("\n=== PESQUISAR FLORES POR ESPÉCIE ===");
        System.out.print("Digite a espécie: ");
        String especie = scanner.nextLine();
        
        List<Flor> flores = florServico.pesquisarPorEspecie(especie);
        if (flores.isEmpty()) {
            System.out.println("Nenhuma flor encontrada dessa espécie.");
        } else {
            System.out.println("Flores encontradas:");
            for (Flor flor : flores) {
                System.out.println(flor);
            }
        }
    }

    private void pesquisarFloresPorCor() throws Exception {
        System.out.println("\n=== PESQUISAR FLORES POR COR ===");
        System.out.print("Digite a cor: ");
        String cor = scanner.nextLine();
        
        List<Flor> flores = florServico.pesquisarPorCor(cor);
        if (flores.isEmpty()) {
            System.out.println("Nenhuma flor encontrada dessa cor.");
        } else {
            System.out.println("Flores encontradas:");
            for (Flor flor : flores) {
                System.out.println(flor);
            }
        }
    }

    private void listarFloresDisponiveis() throws Exception {
        System.out.println("\n=== FLORES DISPONÍVEIS ===");
        List<Flor> flores = florServico.listarDisponiveis();
        
        if (flores.isEmpty()) {
            System.out.println("Nenhuma flor disponível no momento.");
        } else {
            for (Flor flor : flores) {
                System.out.println(flor);
            }
        }
    }

    private void listarFloresComEstoqueBaixo() throws Exception {
        System.out.println("\n=== FLORES COM ESTOQUE BAIXO ===");
        System.out.print("Digite o limite de estoque baixo: ");
        int limite = Integer.parseInt(scanner.nextLine());
        
        List<Flor> flores = florServico.listarComEstoqueBaixo(limite);
        if (flores.isEmpty()) {
            System.out.println("Nenhuma flor com estoque baixo.");
        } else {
            for (Flor flor : flores) {
                System.out.println(flor);
            }
        }
    }

    private void pesquisarPorFaixaPreco() throws Exception {
        System.out.println("\n=== PESQUISAR POR FAIXA DE PREÇO ===");
        System.out.print("Preço mínimo: ");
        double precoMinimo = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Preço máximo: ");
        double precoMaximo = Double.parseDouble(scanner.nextLine());
        
        List<Flor> flores = florServico.pesquisarPorFaixaPreco(precoMinimo, precoMaximo);
        if (flores.isEmpty()) {
            System.out.println("Nenhuma flor encontrada nessa faixa de preço.");
        } else {
            System.out.println("Flores encontradas:");
            for (Flor flor : flores) {
                System.out.println(flor);
            }
        }
    }

    private void atualizarEstoque() throws Exception {
        System.out.println("\n=== ATUALIZAR ESTOQUE ===");
        System.out.print("ID da flor: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Quantidade a adicionar (negativo para reduzir): ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        
        if (florServico.atualizarEstoque(id, quantidade)) {
            System.out.println("Estoque atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar estoque.");
        }
    }

    private void atualizarFlor() throws Exception {
        System.out.println("\n=== ATUALIZAR FLOR ===");
        System.out.print("ID da flor: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Flor flor = florServico.pesquisarPorId(id);
        if (flor == null) {
            System.out.println("Flor não encontrada.");
            return;
        }
        
        System.out.println("Flor atual: " + flor);
        System.out.println("\nDigite os novos dados (deixe em branco para manter o valor atual):");
        
        System.out.print("Nome [" + flor.getNome() + "]: ");
        String nome = scanner.nextLine();
        if (!nome.trim().isEmpty()) {
            flor.setNome(nome);
        }
        
        System.out.print("Espécie [" + flor.getEspecie() + "]: ");
        String especie = scanner.nextLine();
        if (!especie.trim().isEmpty()) {
            flor.setEspecie(especie);
        }
        
        System.out.print("Cor [" + flor.getCor() + "]: ");
        String cor = scanner.nextLine();
        if (!cor.trim().isEmpty()) {
            flor.setCor(cor);
        }
        
        System.out.print("Preço [" + flor.getPreco() + "]: ");
        String precoStr = scanner.nextLine();
        if (!precoStr.trim().isEmpty()) {
            flor.setPreco(Double.parseDouble(precoStr));
        }
        
        System.out.print("Quantidade em estoque [" + flor.getQuantidadeEstoque() + "]: ");
        String quantidadeStr = scanner.nextLine();
        if (!quantidadeStr.trim().isEmpty()) {
            flor.setQuantidadeEstoque(Integer.parseInt(quantidadeStr));
        }
        
        System.out.print("Descrição [" + flor.getDescricao() + "]: ");
        String descricao = scanner.nextLine();
        if (!descricao.trim().isEmpty()) {
            flor.setDescricao(descricao);
        }
        
        if (florServico.atualizar(flor)) {
            System.out.println("Flor atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar flor.");
        }
    }

    private void removerFlor() throws Exception {
        System.out.println("\n=== REMOVER FLOR ===");
        System.out.print("ID da flor: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Flor flor = florServico.pesquisarPorId(id);
        if (flor == null) {
            System.out.println("Flor não encontrada.");
            return;
        }
        
        System.out.println("Flor a ser removida: " + flor);
        System.out.print("Tem certeza? (s/n): ");
        String confirmacao = scanner.nextLine();
        
        if (confirmacao.toLowerCase().equals("s")) {
            if (florServico.remover(id)) {
                System.out.println("Flor removida com sucesso!");
            } else {
                System.out.println("Erro ao remover flor.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}
