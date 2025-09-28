# Sistema de Floricultura

Sistema de gerenciamento completo para floriculturas desenvolvido em Java com arquitetura MVC.

## 🌹 Características

- **Arquitetura MVC**: Separação clara entre Model, View e Controller
- **Herança**: Classe abstrata `Pessoa` herdada por `Cliente` e `Funcionario`
- **Polimorfismo**: Métodos polimórficos nas classes herdadas
- **Encapsulamento**: Todos os atributos privados com getters/setters
- **Interfaces**: Interfaces para repositórios e serviços
- **Tratamento de Erros**: Exceções customizadas e validações
- **CRUD Completo**: Operações de Create, Read, Update e Delete para todas as entidades

## 📁 Estrutura do Projeto

```
floricultura/
├── src/main/java/com/floricultura/
│   ├── Main.java                           # Classe principal
│   ├── model/
│   │   ├── entities/                       # Entidades do sistema
│   │   │   ├── Pessoa.java                 # Classe abstrata base
│   │   │   ├── Cliente.java                # Herda de Pessoa
│   │   │   ├── Funcionario.java            # Herda de Pessoa
│   │   │   ├── Flor.java                   # Entidade flor
│   │   │   ├── Fornecedor.java             # Entidade fornecedor
│   │   │   ├── Venda.java                  # Entidade venda
│   │   │   └── ItemVenda.java              # Item de venda
│   │   ├── repositories/                   # Repositórios (ArrayList)
│   │   │   ├── RepositorioBase.java        # Classe base abstrata
│   │   │   ├── FlorRepositorio.java
│   │   │   ├── ClienteRepositorio.java
│   │   │   ├── FornecedorRepositorio.java
│   │   │   ├── FuncionarioRepositorio.java
│   │   │   └── VendaRepositorio.java
│   │   ├── services/                       # Serviços com regras de negócio
│   │   │   ├── FlorServico.java
│   │   │   ├── ClienteServico.java
│   │   │   ├── FornecedorServico.java
│   │   │   ├── FuncionarioServico.java
│   │   │   └── VendaServico.java
│   │   ├── interfaces/                     # Interfaces
│   │   │   ├── IRepositorio.java           # Interface genérica
│   │   │   ├── IFlorRepositorio.java
│   │   │   ├── IClienteRepositorio.java
│   │   │   ├── IFornecedorRepositorio.java
│   │   │   ├── IFuncionarioRepositorio.java
│   │   │   ├── IVendaRepositorio.java
│   │   │   ├── IServico.java               # Interface genérica
│   │   │   ├── IFlorServico.java
│   │   │   ├── IClienteServico.java
│   │   │   ├── IFornecedorServico.java
│   │   │   ├── IFuncionarioServico.java
│   │   │   └── IVendaServico.java
│   │   └── exceptions/                     # Exceções customizadas
│   │       ├── FloriculturaException.java
│   │       ├── EntidadeNaoEncontradaException.java
│   │       ├── DadosInvalidosException.java
│   │       └── EstoqueInsuficienteException.java
│   ├── controller/                         # Controladores
│   │   ├── FlorController.java
│   │   ├── ClienteController.java
│   │   ├── FornecedorController.java
│   │   ├── FuncionarioController.java
│   │   └── VendaController.java
│   └── view/                               # Interface do usuário
│       └── MenuPrincipal.java
└── README.md
```

## 🚀 Como Executar

1. **Compilar o projeto:**
   ```bash
   cd floricultura
   javac -d . src/main/java/com/floricultura/*.java src/main/java/com/floricultura/**/*.java
   ```

2. **Executar o sistema:**
   ```bash
   java com.floricultura.Main
   ```

## 📋 Funcionalidades

### 🌹 Gestão de Flores
- Cadastro, listagem, pesquisa e atualização de flores
- Controle de estoque
- Pesquisa por nome, espécie, cor e faixa de preço
- Listagem de flores disponíveis e com estoque baixo

### 👥 Gestão de Clientes
- Cadastro e gerenciamento de clientes
- Sistema de clientes VIP com desconto
- Pesquisa por CPF, nome e faixa de gasto
- Histórico de compras

### 🏢 Gestão de Fornecedores
- Cadastro de fornecedores com CNPJ
- Controle de status (ativo/inativo)
- Gestão de produtos fornecidos

### 👨‍💼 Gestão de Funcionários
- Cadastro de funcionários com dados completos
- Controle de comissões
- Listagem por cargo e status
- Cálculo de tempo de empresa

### 💰 Gestão de Vendas
- Criação e gerenciamento de vendas
- Adição/remoção de itens
- Finalização de vendas com controle de estoque
- Relatórios por período, cliente e funcionário

### 📊 Relatórios
- Resumo geral do sistema
- Estatísticas de vendas
- Relatório de estoque
- Clientes VIP
- Funcionários mais produtivos

## 🎯 Conceitos de POO Implementados

### Herança
- Classe abstrata `Pessoa` com métodos abstratos
- `Cliente` e `Funcionario` herdam de `Pessoa`
- `RepositorioBase` como classe base para repositórios

### Polimorfismo
- Métodos `validarDados()` e `getInformacoesEspecificas()` polimórficos
- Sobrescrita de métodos `toString()`, `equals()` e `hashCode()`

### Encapsulamento
- Todos os atributos são privados
- Acesso controlado através de getters e setters
- Validações nos métodos setters

### Interfaces
- Interfaces para todos os repositórios e serviços
- Contratos bem definidos para cada camada
- Facilita manutenção e testes

### Tratamento de Exceções
- Exceções customizadas para diferentes tipos de erro
- Validações robustas em todas as operações
- Mensagens de erro claras para o usuário

## 🔧 Tecnologias Utilizadas

- **Java**: Linguagem de programação
- **ArrayList**: Estrutura de dados para persistência
- **Scanner**: Interface de entrada do usuário
- **LocalDate/LocalDateTime**: Manipulação de datas
- **BigDecimal**: Precisão decimal para valores monetários

## 📝 Observações

- O sistema utiliza ArrayList para persistência em memória
- Dados são perdidos ao encerrar a aplicação
- Para persistência permanente, seria necessário implementar banco de dados
- Validações de CPF e CNPJ são simplificadas
- Sistema desenvolvido para fins educacionais

## 👨‍💻 Desenvolvido por: 
- Kaick Ramos de Melo Silva;
- Thyago Felipe Albuquerque de Santana;
- Priscila Gomes da Costa;
- Ingred Myllena Duarte do Carmo;
- Matheus do Nascimento Costa;
- Biehof Flávio da Silva Dimas;


Sistema desenvolvido seguindo as melhores práticas de Programação Orientada a Objetos e arquitetura MVC em Java.

