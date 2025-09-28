package com.floricultura.controller;

import com.floricultura.model.entities.Funcionario;
import com.floricultura.model.interfaces.IFuncionarioServico;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador para gerenciar operações relacionadas a funcionários.
 */
public class FuncionarioController {
    private IFuncionarioServico funcionarioServico;
    private Scanner scanner;

    public FuncionarioController(IFuncionarioServico funcionarioServico) {
        this.funcionarioServico = funcionarioServico;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Exibe o menu de operações para funcionários.
     */
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU DE FUNCIONÁRIOS ===");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Listar Todos os Funcionários");
            System.out.println("3. Pesquisar Funcionário por ID");
            System.out.println("4. Pesquisar Funcionário por CPF");
            System.out.println("5. Pesquisar Funcionários por Nome");
            System.out.println("6. Listar Funcionários Ativos");
            System.out.println("7. Listar Funcionários Inativos");
            System.out.println("8. Listar por Cargo");
            System.out.println("9. Listar Vendedores");
            System.out.println("10. Ativar Funcionário");
            System.out.println("11. Desativar Funcionário");
            System.out.println("12. Calcular Comissão");
            System.out.println("13. Atualizar Funcionário");
            System.out.println("14. Remover Funcionário");
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
                cadastrarFuncionario();
                break;
            case 2:
                listarTodosFuncionarios();
                break;
            case 3:
                pesquisarFuncionarioPorId();
                break;
            case 4:
                pesquisarFuncionarioPorCpf();
                break;
            case 5:
                pesquisarFuncionariosPorNome();
                break;
            case 6:
                listarFuncionariosAtivos();
                break;
            case 7:
                listarFuncionariosInativos();
                break;
            case 8:
                listarPorCargo();
                break;
            case 9:
                listarVendedores();
                break;
            case 10:
                ativarFuncionario();
                break;
            case 11:
                desativarFuncionario();
                break;
            case 12:
                calcularComissao();
                break;
            case 13:
                atualizarFuncionario();
                break;
            case 14:
                removerFuncionario();
                break;
            case 0:
                System.out.println("Retornando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void cadastrarFuncionario() throws Exception {
        System.out.println("\n=== CADASTRAR FUNCIONÁRIO ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        
        System.out.print("Salário: ");
        BigDecimal salario = new BigDecimal(scanner.nextLine());
        
        System.out.print("Data de admissão (yyyy-mm-dd): ");
        LocalDate dataAdmissao = LocalDate.parse(scanner.nextLine());
        
        System.out.print("Data de nascimento (yyyy-mm-dd): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());
        
        Funcionario funcionario = new Funcionario(0, nome, telefone, email, endereco, 
                                                cpf, cargo, salario, dataAdmissao, dataNascimento);
        
        if (funcionarioServico.cadastrar(funcionario)) {
            System.out.println("Funcionário cadastrado com sucesso! ID: " + funcionario.getId());
        } else {
            System.out.println("Erro ao cadastrar funcionário.");
        }
    }

    private void listarTodosFuncionarios() throws Exception {
        System.out.println("\n=== TODOS OS FUNCIONÁRIOS ===");
        List<Funcionario> funcionarios = funcionarioServico.listarTodos();
        
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }
    }

    private void pesquisarFuncionarioPorId() throws Exception {
        System.out.println("\n=== PESQUISAR FUNCIONÁRIO POR ID ===");
        System.out.print("Digite o ID do funcionário: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Funcionario funcionario = funcionarioServico.pesquisarPorId(id);
        if (funcionario != null) {
            System.out.println("Funcionário encontrado:");
            System.out.println(funcionario);
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private void pesquisarFuncionarioPorCpf() throws Exception {
        System.out.println("\n=== PESQUISAR FUNCIONÁRIO POR CPF ===");
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        
        Funcionario funcionario = funcionarioServico.pesquisarPorCpf(cpf);
        if (funcionario != null) {
            System.out.println("Funcionário encontrado:");
            System.out.println(funcionario);
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private void pesquisarFuncionariosPorNome() throws Exception {
        System.out.println("\n=== PESQUISAR FUNCIONÁRIOS POR NOME ===");
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        
        List<Funcionario> funcionarios = funcionarioServico.pesquisarPorNome(nome);
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário encontrado com esse nome.");
        } else {
            System.out.println("Funcionários encontrados:");
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }
    }

    private void listarFuncionariosAtivos() throws Exception {
        System.out.println("\n=== FUNCIONÁRIOS ATIVOS ===");
        List<Funcionario> funcionarios = funcionarioServico.listarAtivos();
        
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário ativo.");
        } else {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }
    }

    private void listarFuncionariosInativos() throws Exception {
        System.out.println("\n=== FUNCIONÁRIOS INATIVOS ===");
        List<Funcionario> funcionarios = funcionarioServico.listarInativos();
        
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário inativo.");
        } else {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }
    }

    private void listarPorCargo() throws Exception {
        System.out.println("\n=== LISTAR POR CARGO ===");
        System.out.print("Digite o cargo: ");
        String cargo = scanner.nextLine();
        
        List<Funcionario> funcionarios = funcionarioServico.listarPorCargo(cargo);
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário encontrado com esse cargo.");
        } else {
            System.out.println("Funcionários encontrados:");
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }
    }

    private void listarVendedores() throws Exception {
        System.out.println("\n=== VENDEDORES ===");
        List<Funcionario> funcionarios = funcionarioServico.listarVendedores();
        
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado.");
        } else {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }
    }

    private void ativarFuncionario() throws Exception {
        System.out.println("\n=== ATIVAR FUNCIONÁRIO ===");
        System.out.print("ID do funcionário: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        if (funcionarioServico.ativar(id)) {
            System.out.println("Funcionário ativado com sucesso!");
        } else {
            System.out.println("Erro ao ativar funcionário.");
        }
    }

    private void desativarFuncionario() throws Exception {
        System.out.println("\n=== DESATIVAR FUNCIONÁRIO ===");
        System.out.print("ID do funcionário: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        if (funcionarioServico.desativar(id)) {
            System.out.println("Funcionário desativado com sucesso!");
        } else {
            System.out.println("Erro ao desativar funcionário.");
        }
    }

    private void calcularComissao() throws Exception {
        System.out.println("\n=== CALCULAR COMISSÃO ===");
        System.out.print("ID do funcionário: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        double comissao = funcionarioServico.calcularComissao(id);
        System.out.println("Comissão total: R$ " + String.format("%.2f", comissao));
    }

    private void atualizarFuncionario() throws Exception {
        System.out.println("\n=== ATUALIZAR FUNCIONÁRIO ===");
        System.out.print("ID do funcionário: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Funcionario funcionario = funcionarioServico.pesquisarPorId(id);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }
        
        System.out.println("Funcionário atual: " + funcionario);
        System.out.println("\nDigite os novos dados (deixe em branco para manter o valor atual):");
        
        System.out.print("Nome [" + funcionario.getNome() + "]: ");
        String nome = scanner.nextLine();
        if (!nome.trim().isEmpty()) {
            funcionario.setNome(nome);
        }
        
        System.out.print("CPF [" + funcionario.getCpf() + "]: ");
        String cpf = scanner.nextLine();
        if (!cpf.trim().isEmpty()) {
            funcionario.setCpf(cpf);
        }
        
        System.out.print("Telefone [" + funcionario.getTelefone() + "]: ");
        String telefone = scanner.nextLine();
        if (!telefone.trim().isEmpty()) {
            funcionario.setTelefone(telefone);
        }
        
        System.out.print("Email [" + funcionario.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (!email.trim().isEmpty()) {
            funcionario.setEmail(email);
        }
        
        System.out.print("Endereço [" + funcionario.getEndereco() + "]: ");
        String endereco = scanner.nextLine();
        if (!endereco.trim().isEmpty()) {
            funcionario.setEndereco(endereco);
        }
        
        System.out.print("Cargo [" + funcionario.getCargo() + "]: ");
        String cargo = scanner.nextLine();
        if (!cargo.trim().isEmpty()) {
            funcionario.setCargo(cargo);
        }
        
        System.out.print("Salário [" + funcionario.getSalario() + "]: ");
        String salarioStr = scanner.nextLine();
        if (!salarioStr.trim().isEmpty()) {
            funcionario.setSalario(new BigDecimal(salarioStr));
        }
        
        if (funcionarioServico.atualizar(funcionario)) {
            System.out.println("Funcionário atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar funcionário.");
        }
    }

    private void removerFuncionario() throws Exception {
        System.out.println("\n=== REMOVER FUNCIONÁRIO ===");
        System.out.print("ID do funcionário: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Funcionario funcionario = funcionarioServico.pesquisarPorId(id);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }
        
        System.out.println("Funcionário a ser removido: " + funcionario);
        System.out.print("Tem certeza? (s/n): ");
        String confirmacao = scanner.nextLine();
        
        if (confirmacao.toLowerCase().equals("s")) {
            if (funcionarioServico.remover(id)) {
                System.out.println("Funcionário removido com sucesso!");
            } else {
                System.out.println("Erro ao remover funcionário.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}
