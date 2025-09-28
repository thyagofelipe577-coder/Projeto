package com.floricultura.controller;

import com.floricultura.model.entities.Cliente;
import com.floricultura.model.interfaces.IClienteServico;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador para gerenciar operações relacionadas a clientes.
 */
public class ClienteController {
    private IClienteServico clienteServico;
    private Scanner scanner;

    public ClienteController(IClienteServico clienteServico) {
        this.clienteServico = clienteServico;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Exibe o menu de operações para clientes.
     */
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU DE CLIENTES ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Todos os Clientes");
            System.out.println("3. Pesquisar Cliente por ID");
            System.out.println("4. Pesquisar Cliente por CPF");
            System.out.println("5. Pesquisar Clientes por Nome");
            System.out.println("6. Listar Clientes VIP");
            System.out.println("7. Listar por Faixa de Gasto");
            System.out.println("8. Promover Cliente para VIP");
            System.out.println("9. Calcular Desconto VIP");
            System.out.println("10. Atualizar Cliente");
            System.out.println("11. Remover Cliente");
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
                cadastrarCliente();
                break;
            case 2:
                listarTodosClientes();
                break;
            case 3:
                pesquisarClientePorId();
                break;
            case 4:
                pesquisarClientePorCpf();
                break;
            case 5:
                pesquisarClientesPorNome();
                break;
            case 6:
                listarClientesVip();
                break;
            case 7:
                listarPorFaixaGasto();
                break;
            case 8:
                promoverParaVip();
                break;
            case 9:
                calcularDescontoVip();
                break;
            case 10:
                atualizarCliente();
                break;
            case 11:
                removerCliente();
                break;
            case 0:
                System.out.println("Retornando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void cadastrarCliente() throws Exception {
        System.out.println("\n=== CADASTRAR CLIENTE ===");
        
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
        
        System.out.print("Data de cadastro (yyyy-mm-dd): ");
        LocalDate dataCadastro = LocalDate.parse(scanner.nextLine());
        
        Cliente cliente = new Cliente(0, nome, telefone, email, endereco, cpf, dataCadastro);
        
        if (clienteServico.cadastrar(cliente)) {
            System.out.println("Cliente cadastrado com sucesso! ID: " + cliente.getId());
        } else {
            System.out.println("Erro ao cadastrar cliente.");
        }
    }

    private void listarTodosClientes() throws Exception {
        System.out.println("\n=== TODOS OS CLIENTES ===");
        List<Cliente> clientes = clienteServico.listarTodos();
        
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private void pesquisarClientePorId() throws Exception {
        System.out.println("\n=== PESQUISAR CLIENTE POR ID ===");
        System.out.print("Digite o ID do cliente: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Cliente cliente = clienteServico.pesquisarPorId(id);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void pesquisarClientePorCpf() throws Exception {
        System.out.println("\n=== PESQUISAR CLIENTE POR CPF ===");
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        
        Cliente cliente = clienteServico.pesquisarPorCpf(cpf);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void pesquisarClientesPorNome() throws Exception {
        System.out.println("\n=== PESQUISAR CLIENTES POR NOME ===");
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        
        List<Cliente> clientes = clienteServico.pesquisarPorNome(nome);
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado com esse nome.");
        } else {
            System.out.println("Clientes encontrados:");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private void listarClientesVip() throws Exception {
        System.out.println("\n=== CLIENTES VIP ===");
        List<Cliente> clientes = clienteServico.listarClientesVip();
        
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente VIP cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private void listarPorFaixaGasto() throws Exception {
        System.out.println("\n=== LISTAR POR FAIXA DE GASTO ===");
        System.out.print("Gasto mínimo: ");
        double gastoMinimo = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Gasto máximo: ");
        double gastoMaximo = Double.parseDouble(scanner.nextLine());
        
        List<Cliente> clientes = clienteServico.listarPorFaixaGasto(gastoMinimo, gastoMaximo);
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado nessa faixa de gasto.");
        } else {
            System.out.println("Clientes encontrados:");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private void promoverParaVip() throws Exception {
        System.out.println("\n=== PROMOVER CLIENTE PARA VIP ===");
        System.out.print("ID do cliente: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        if (clienteServico.promoverParaVip(id)) {
            System.out.println("Cliente promovido para VIP com sucesso!");
        } else {
            System.out.println("Erro ao promover cliente para VIP.");
        }
    }

    private void calcularDescontoVip() throws Exception {
        System.out.println("\n=== CALCULAR DESCONTO VIP ===");
        System.out.print("ID do cliente: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Valor da compra: ");
        double valor = Double.parseDouble(scanner.nextLine());
        
        double desconto = clienteServico.calcularDescontoVip(id, valor);
        System.out.println("Desconto VIP: R$ " + String.format("%.2f", desconto));
    }

    private void atualizarCliente() throws Exception {
        System.out.println("\n=== ATUALIZAR CLIENTE ===");
        System.out.print("ID do cliente: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Cliente cliente = clienteServico.pesquisarPorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        
        System.out.println("Cliente atual: " + cliente);
        System.out.println("\nDigite os novos dados (deixe em branco para manter o valor atual):");
        
        System.out.print("Nome [" + cliente.getNome() + "]: ");
        String nome = scanner.nextLine();
        if (!nome.trim().isEmpty()) {
            cliente.setNome(nome);
        }
        
        System.out.print("CPF [" + cliente.getCpf() + "]: ");
        String cpf = scanner.nextLine();
        if (!cpf.trim().isEmpty()) {
            cliente.setCpf(cpf);
        }
        
        System.out.print("Telefone [" + cliente.getTelefone() + "]: ");
        String telefone = scanner.nextLine();
        if (!telefone.trim().isEmpty()) {
            cliente.setTelefone(telefone);
        }
        
        System.out.print("Email [" + cliente.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (!email.trim().isEmpty()) {
            cliente.setEmail(email);
        }
        
        System.out.print("Endereço [" + cliente.getEndereco() + "]: ");
        String endereco = scanner.nextLine();
        if (!endereco.trim().isEmpty()) {
            cliente.setEndereco(endereco);
        }
        
        if (clienteServico.atualizar(cliente)) {
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar cliente.");
        }
    }

    private void removerCliente() throws Exception {
        System.out.println("\n=== REMOVER CLIENTE ===");
        System.out.print("ID do cliente: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Cliente cliente = clienteServico.pesquisarPorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        
        System.out.println("Cliente a ser removido: " + cliente);
        System.out.print("Tem certeza? (s/n): ");
        String confirmacao = scanner.nextLine();
        
        if (confirmacao.toLowerCase().equals("s")) {
            if (clienteServico.remover(id)) {
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Erro ao remover cliente.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}
