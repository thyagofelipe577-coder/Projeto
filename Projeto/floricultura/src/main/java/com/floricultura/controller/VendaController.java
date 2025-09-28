package com.floricultura.controller;

import com.floricultura.model.entities.*;
import com.floricultura.model.interfaces.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador para gerenciar operações relacionadas a vendas.
 */
public class VendaController {
    private IVendaServico vendaServico;
    private IFlorServico florServico;
    private IClienteServico clienteServico;
    private IFuncionarioServico funcionarioServico;
    private Scanner scanner;

    public VendaController(IVendaServico vendaServico, IFlorServico florServico, 
                          IClienteServico clienteServico, IFuncionarioServico funcionarioServico) {
        this.vendaServico = vendaServico;
        this.florServico = florServico;
        this.clienteServico = clienteServico;
        this.funcionarioServico = funcionarioServico;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Exibe o menu de operações para vendas.
     */
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU DE VENDAS ===");
            System.out.println("1. Nova Venda");
            System.out.println("2. Listar Todas as Vendas");
            System.out.println("3. Pesquisar Venda por ID");
            System.out.println("4. Listar Vendas por Cliente");
            System.out.println("5. Listar Vendas por Funcionário");
            System.out.println("6. Listar Vendas por Período");
            System.out.println("7. Listar Vendas por Data");
            System.out.println("8. Listar Vendas Finalizadas");
            System.out.println("9. Listar Vendas Pendentes");
            System.out.println("10. Adicionar Item à Venda");
            System.out.println("11. Remover Item da Venda");
            System.out.println("12. Finalizar Venda");
            System.out.println("13. Calcular Total de Vendas");
            System.out.println("14. Atualizar Venda");
            System.out.println("15. Remover Venda");
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
                novaVenda();
                break;
            case 2:
                listarTodasVendas();
                break;
            case 3:
                pesquisarVendaPorId();
                break;
            case 4:
                listarVendasPorCliente();
                break;
            case 5:
                listarVendasPorFuncionario();
                break;
            case 6:
                listarVendasPorPeriodo();
                break;
            case 7:
                listarVendasPorData();
                break;
            case 8:
                listarVendasFinalizadas();
                break;
            case 9:
                listarVendasPendentes();
                break;
            case 10:
                adicionarItemVenda();
                break;
            case 11:
                removerItemVenda();
                break;
            case 12:
                finalizarVenda();
                break;
            case 13:
                calcularTotalVendas();
                break;
            case 14:
                atualizarVenda();
                break;
            case 15:
                removerVenda();
                break;
            case 0:
                System.out.println("Retornando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void novaVenda() throws Exception {
        System.out.println("\n=== NOVA VENDA ===");
        
        System.out.print("ID do cliente: ");
        int clienteId = Integer.parseInt(scanner.nextLine());
        Cliente cliente = clienteServico.pesquisarPorId(clienteId);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        
        System.out.print("ID do funcionário: ");
        int funcionarioId = Integer.parseInt(scanner.nextLine());
        Funcionario funcionario = funcionarioServico.pesquisarPorId(funcionarioId);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }
        
        Venda venda = new Venda(0, cliente, funcionario, LocalDateTime.now());
        
        if (vendaServico.cadastrar(venda)) {
            System.out.println("Venda criada com sucesso! ID: " + venda.getId());
            System.out.println("Use a opção 'Adicionar Item à Venda' para adicionar produtos.");
        } else {
            System.out.println("Erro ao criar venda.");
        }
    }

    private void listarTodasVendas() throws Exception {
        System.out.println("\n=== TODAS AS VENDAS ===");
        List<Venda> vendas = vendaServico.listarTodos();
        
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda cadastrada.");
        } else {
            for (Venda venda : vendas) {
                System.out.println(venda);
            }
        }
    }

    private void pesquisarVendaPorId() throws Exception {
        System.out.println("\n=== PESQUISAR VENDA POR ID ===");
        System.out.print("Digite o ID da venda: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Venda venda = vendaServico.pesquisarPorId(id);
        if (venda != null) {
            System.out.println("Venda encontrada:");
            System.out.println(venda);
            if (!venda.getItens().isEmpty()) {
                System.out.println("\nItens da venda:");
                for (ItemVenda item : venda.getItens()) {
                    System.out.println("  - " + item);
                }
            }
        } else {
            System.out.println("Venda não encontrada.");
        }
    }

    private void listarVendasPorCliente() throws Exception {
        System.out.println("\n=== VENDAS POR CLIENTE ===");
        System.out.print("ID do cliente: ");
        int clienteId = Integer.parseInt(scanner.nextLine());
        
        List<Venda> vendas = vendaServico.listarPorCliente(clienteId);
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda encontrada para este cliente.");
        } else {
            for (Venda venda : vendas) {
                System.out.println(venda);
            }
        }
    }

    private void listarVendasPorFuncionario() throws Exception {
        System.out.println("\n=== VENDAS POR FUNCIONÁRIO ===");
        System.out.print("ID do funcionário: ");
        int funcionarioId = Integer.parseInt(scanner.nextLine());
        
        List<Venda> vendas = vendaServico.listarPorFuncionario(funcionarioId);
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda encontrada para este funcionário.");
        } else {
            for (Venda venda : vendas) {
                System.out.println(venda);
            }
        }
    }

    private void listarVendasPorPeriodo() throws Exception {
        System.out.println("\n=== VENDAS POR PERÍODO ===");
        System.out.print("Data de início (yyyy-mm-dd hh:mm): ");
        LocalDateTime dataInicio = LocalDateTime.parse(scanner.nextLine());
        
        System.out.print("Data de fim (yyyy-mm-dd hh:mm): ");
        LocalDateTime dataFim = LocalDateTime.parse(scanner.nextLine());
        
        List<Venda> vendas = vendaServico.listarPorPeriodo(dataInicio, dataFim);
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda encontrada no período especificado.");
        } else {
            for (Venda venda : vendas) {
                System.out.println(venda);
            }
        }
    }

    private void listarVendasPorData() throws Exception {
        System.out.println("\n=== VENDAS POR DATA ===");
        System.out.print("Data (yyyy-mm-dd): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        
        List<Venda> vendas = vendaServico.listarPorData(data);
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda encontrada para esta data.");
        } else {
            for (Venda venda : vendas) {
                System.out.println(venda);
            }
        }
    }

    private void listarVendasFinalizadas() throws Exception {
        System.out.println("\n=== VENDAS FINALIZADAS ===");
        List<Venda> vendas = vendaServico.listarFinalizadas();
        
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda finalizada.");
        } else {
            for (Venda venda : vendas) {
                System.out.println(venda);
            }
        }
    }

    private void listarVendasPendentes() throws Exception {
        System.out.println("\n=== VENDAS PENDENTES ===");
        List<Venda> vendas = vendaServico.listarPendentes();
        
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda pendente.");
        } else {
            for (Venda venda : vendas) {
                System.out.println(venda);
            }
        }
    }

    private void adicionarItemVenda() throws Exception {
        System.out.println("\n=== ADICIONAR ITEM À VENDA ===");
        System.out.print("ID da venda: ");
        int vendaId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("ID da flor: ");
        int florId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        
        if (vendaServico.adicionarItem(vendaId, florId, quantidade)) {
            System.out.println("Item adicionado à venda com sucesso!");
        } else {
            System.out.println("Erro ao adicionar item à venda.");
        }
    }

    private void removerItemVenda() throws Exception {
        System.out.println("\n=== REMOVER ITEM DA VENDA ===");
        System.out.print("ID da venda: ");
        int vendaId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("ID da flor: ");
        int florId = Integer.parseInt(scanner.nextLine());
        
        if (vendaServico.removerItem(vendaId, florId)) {
            System.out.println("Item removido da venda com sucesso!");
        } else {
            System.out.println("Erro ao remover item da venda.");
        }
    }

    private void finalizarVenda() throws Exception {
        System.out.println("\n=== FINALIZAR VENDA ===");
        System.out.print("ID da venda: ");
        int vendaId = Integer.parseInt(scanner.nextLine());
        
        Venda venda = vendaServico.pesquisarPorId(vendaId);
        if (venda == null) {
            System.out.println("Venda não encontrada.");
            return;
        }
        
        if (venda.isVazia()) {
            System.out.println("Não é possível finalizar uma venda vazia.");
            return;
        }
        
        System.out.print("Forma de pagamento: ");
        String formaPagamento = scanner.nextLine();
        venda.setFormaPagamento(formaPagamento);
        
        System.out.print("Observações (opcional): ");
        String observacoes = scanner.nextLine();
        venda.setObservacoes(observacoes);
        
        if (vendaServico.finalizarVenda(vendaId)) {
            System.out.println("Venda finalizada com sucesso!");
            System.out.println("Valor total: R$ " + String.format("%.2f", venda.getValorTotal()));
        } else {
            System.out.println("Erro ao finalizar venda.");
        }
    }

    private void calcularTotalVendas() throws Exception {
        System.out.println("\n=== CALCULAR TOTAL DE VENDAS ===");
        System.out.print("Data de início (yyyy-mm-dd hh:mm): ");
        LocalDateTime dataInicio = LocalDateTime.parse(scanner.nextLine());
        
        System.out.print("Data de fim (yyyy-mm-dd hh:mm): ");
        LocalDateTime dataFim = LocalDateTime.parse(scanner.nextLine());
        
        double total = vendaServico.calcularTotalVendas(dataInicio, dataFim);
        System.out.println("Total de vendas no período: R$ " + String.format("%.2f", total));
    }

    private void atualizarVenda() throws Exception {
        System.out.println("\n=== ATUALIZAR VENDA ===");
        System.out.print("ID da venda: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Venda venda = vendaServico.pesquisarPorId(id);
        if (venda == null) {
            System.out.println("Venda não encontrada.");
            return;
        }
        
        if (venda.isFinalizada()) {
            System.out.println("Não é possível atualizar uma venda finalizada.");
            return;
        }
        
        System.out.println("Venda atual: " + venda);
        System.out.println("\nDigite os novos dados (deixe em branco para manter o valor atual):");
        
        System.out.print("Forma de pagamento [" + venda.getFormaPagamento() + "]: ");
        String formaPagamento = scanner.nextLine();
        if (!formaPagamento.trim().isEmpty()) {
            venda.setFormaPagamento(formaPagamento);
        }
        
        System.out.print("Observações [" + venda.getObservacoes() + "]: ");
        String observacoes = scanner.nextLine();
        if (!observacoes.trim().isEmpty()) {
            venda.setObservacoes(observacoes);
        }
        
        if (vendaServico.atualizar(venda)) {
            System.out.println("Venda atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar venda.");
        }
    }

    private void removerVenda() throws Exception {
        System.out.println("\n=== REMOVER VENDA ===");
        System.out.print("ID da venda: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Venda venda = vendaServico.pesquisarPorId(id);
        if (venda == null) {
            System.out.println("Venda não encontrada.");
            return;
        }
        
        if (venda.isFinalizada()) {
            System.out.println("Não é possível remover uma venda finalizada.");
            return;
        }
        
        System.out.println("Venda a ser removida: " + venda);
        System.out.print("Tem certeza? (s/n): ");
        String confirmacao = scanner.nextLine();
        
        if (confirmacao.toLowerCase().equals("s")) {
            if (vendaServico.remover(id)) {
                System.out.println("Venda removida com sucesso!");
            } else {
                System.out.println("Erro ao remover venda.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}
