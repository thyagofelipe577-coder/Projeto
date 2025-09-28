package com.floricultura.controller;

import com.floricultura.model.entities.Fornecedor;
import com.floricultura.model.interfaces.IFornecedorServico;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador para gerenciar operações relacionadas a fornecedores.
 */
public class FornecedorController {
    private IFornecedorServico fornecedorServico;
    private Scanner scanner;

    public FornecedorController(IFornecedorServico fornecedorServico) {
        this.fornecedorServico = fornecedorServico;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Exibe o menu de operações para fornecedores.
     */
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU DE FORNECEDORES ===");
            System.out.println("1. Cadastrar Fornecedor");
            System.out.println("2. Listar Todos os Fornecedores");
            System.out.println("3. Pesquisar Fornecedor por ID");
            System.out.println("4. Pesquisar Fornecedor por CNPJ");
            System.out.println("5. Pesquisar Fornecedores por Nome");
            System.out.println("6. Listar Fornecedores Ativos");
            System.out.println("7. Listar Fornecedores Inativos");
            System.out.println("8. Ativar Fornecedor");
            System.out.println("9. Desativar Fornecedor");
            System.out.println("10. Atualizar Fornecedor");
            System.out.println("11. Remover Fornecedor");
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
                cadastrarFornecedor();
                break;
            case 2:
                listarTodosFornecedores();
                break;
            case 3:
                pesquisarFornecedorPorId();
                break;
            case 4:
                pesquisarFornecedorPorCnpj();
                break;
            case 5:
                pesquisarFornecedoresPorNome();
                break;
            case 6:
                listarFornecedoresAtivos();
                break;
            case 7:
                listarFornecedoresInativos();
                break;
            case 8:
                ativarFornecedor();
                break;
            case 9:
                desativarFornecedor();
                break;
            case 10:
                atualizarFornecedor();
                break;
            case 11:
                removerFornecedor();
                break;
            case 0:
                System.out.println("Retornando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void cadastrarFornecedor() throws Exception {
        System.out.println("\n=== CADASTRAR FORNECEDOR ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        System.out.print("Contato Responsável: ");
        String contatoResponsavel = scanner.nextLine();
        
        System.out.print("Data de cadastro (yyyy-mm-dd): ");
        LocalDate dataCadastro = LocalDate.parse(scanner.nextLine());
        
        Fornecedor fornecedor = new Fornecedor(0, nome, cnpj, telefone, email, endereco, contatoResponsavel, dataCadastro);
        
        if (fornecedorServico.cadastrar(fornecedor)) {
            System.out.println("Fornecedor cadastrado com sucesso! ID: " + fornecedor.getId());
        } else {
            System.out.println("Erro ao cadastrar fornecedor.");
        }
    }

    private void listarTodosFornecedores() throws Exception {
        System.out.println("\n=== TODOS OS FORNECEDORES ===");
        List<Fornecedor> fornecedores = fornecedorServico.listarTodos();
        
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
        } else {
            for (Fornecedor fornecedor : fornecedores) {
                System.out.println(fornecedor);
            }
        }
    }

    private void pesquisarFornecedorPorId() throws Exception {
        System.out.println("\n=== PESQUISAR FORNECEDOR POR ID ===");
        System.out.print("Digite o ID do fornecedor: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Fornecedor fornecedor = fornecedorServico.pesquisarPorId(id);
        if (fornecedor != null) {
            System.out.println("Fornecedor encontrado:");
            System.out.println(fornecedor);
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    private void pesquisarFornecedorPorCnpj() throws Exception {
        System.out.println("\n=== PESQUISAR FORNECEDOR POR CNPJ ===");
        System.out.print("Digite o CNPJ: ");
        String cnpj = scanner.nextLine();
        
        Fornecedor fornecedor = fornecedorServico.pesquisarPorCnpj(cnpj);
        if (fornecedor != null) {
            System.out.println("Fornecedor encontrado:");
            System.out.println(fornecedor);
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    private void pesquisarFornecedoresPorNome() throws Exception {
        System.out.println("\n=== PESQUISAR FORNECEDORES POR NOME ===");
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        
        List<Fornecedor> fornecedores = fornecedorServico.pesquisarPorNome(nome);
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor encontrado com esse nome.");
        } else {
            System.out.println("Fornecedores encontrados:");
            for (Fornecedor fornecedor : fornecedores) {
                System.out.println(fornecedor);
            }
        }
    }

    private void listarFornecedoresAtivos() throws Exception {
        System.out.println("\n=== FORNECEDORES ATIVOS ===");
        List<Fornecedor> fornecedores = fornecedorServico.listarAtivos();
        
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor ativo.");
        } else {
            for (Fornecedor fornecedor : fornecedores) {
                System.out.println(fornecedor);
            }
        }
    }

    private void listarFornecedoresInativos() throws Exception {
        System.out.println("\n=== FORNECEDORES INATIVOS ===");
        List<Fornecedor> fornecedores = fornecedorServico.listarInativos();
        
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor inativo.");
        } else {
            for (Fornecedor fornecedor : fornecedores) {
                System.out.println(fornecedor);
            }
        }
    }

    private void ativarFornecedor() throws Exception {
        System.out.println("\n=== ATIVAR FORNECEDOR ===");
        System.out.print("ID do fornecedor: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        if (fornecedorServico.ativar(id)) {
            System.out.println("Fornecedor ativado com sucesso!");
        } else {
            System.out.println("Erro ao ativar fornecedor.");
        }
    }

    private void desativarFornecedor() throws Exception {
        System.out.println("\n=== DESATIVAR FORNECEDOR ===");
        System.out.print("ID do fornecedor: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        if (fornecedorServico.desativar(id)) {
            System.out.println("Fornecedor desativado com sucesso!");
        } else {
            System.out.println("Erro ao desativar fornecedor.");
        }
    }

    private void atualizarFornecedor() throws Exception {
        System.out.println("\n=== ATUALIZAR FORNECEDOR ===");
        System.out.print("ID do fornecedor: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Fornecedor fornecedor = fornecedorServico.pesquisarPorId(id);
        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }
        
        System.out.println("Fornecedor atual: " + fornecedor);
        System.out.println("\nDigite os novos dados (deixe em branco para manter o valor atual):");
        
        System.out.print("Nome [" + fornecedor.getNome() + "]: ");
        String nome = scanner.nextLine();
        if (!nome.trim().isEmpty()) {
            fornecedor.setNome(nome);
        }
        
        System.out.print("CNPJ [" + fornecedor.getCnpj() + "]: ");
        String cnpj = scanner.nextLine();
        if (!cnpj.trim().isEmpty()) {
            fornecedor.setCnpj(cnpj);
        }
        
        System.out.print("Telefone [" + fornecedor.getTelefone() + "]: ");
        String telefone = scanner.nextLine();
        if (!telefone.trim().isEmpty()) {
            fornecedor.setTelefone(telefone);
        }
        
        System.out.print("Email [" + fornecedor.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (!email.trim().isEmpty()) {
            fornecedor.setEmail(email);
        }
        
        System.out.print("Endereço [" + fornecedor.getEndereco() + "]: ");
        String endereco = scanner.nextLine();
        if (!endereco.trim().isEmpty()) {
            fornecedor.setEndereco(endereco);
        }
        
        System.out.print("Contato Responsável [" + fornecedor.getContatoResponsavel() + "]: ");
        String contatoResponsavel = scanner.nextLine();
        if (!contatoResponsavel.trim().isEmpty()) {
            fornecedor.setContatoResponsavel(contatoResponsavel);
        }
        
        if (fornecedorServico.atualizar(fornecedor)) {
            System.out.println("Fornecedor atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar fornecedor.");
        }
    }

    private void removerFornecedor() throws Exception {
        System.out.println("\n=== REMOVER FORNECEDOR ===");
        System.out.print("ID do fornecedor: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Fornecedor fornecedor = fornecedorServico.pesquisarPorId(id);
        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }
        
        System.out.println("Fornecedor a ser removido: " + fornecedor);
        System.out.print("Tem certeza? (s/n): ");
        String confirmacao = scanner.nextLine();
        
        if (confirmacao.toLowerCase().equals("s")) {
            if (fornecedorServico.remover(id)) {
                System.out.println("Fornecedor removido com sucesso!");
            } else {
                System.out.println("Erro ao remover fornecedor.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}
