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

# Modelo de Banco de Dados - Sistema de Floricultura

## 📊 Diagrama de Relacionamentos

```
┌─────────────────┐
│    CLIENTES     │
│─────────────────│
│ id (PK)         │
│ nome            │
│ cpf (UNIQUE)    │
│ telefone        │
│ email           │
│ endereco        │
│ data_cadastro   │
│ vip             │
│ gasto_total     │
│ ativo           │
└────────┬────────┘
         │ 1
         │
         │ N
┌────────▼────────┐
│     VENDAS     │
│────────────────│
│ id (PK)        │
│ cliente_id (FK)│──┐
│ funcionario_id │  │
│ data_venda     │  │
│ valor_total    │  │
│ desconto       │  │
│ valor_final    │  │
│ status         │  │
│ forma_pagamento│ │
└────────┬───────┘  │
         │ 1        │
         │          │
         │ N        │
┌────────▼────────┐ │
│  ITENS_VENDA   │ │
│────────────────│ │
│ id (PK)        │ │
│ venda_id (FK)  │◄┘
│ flor_id (FK)   │──┐
│ quantidade     │  │
│ preco_unitario │  │
│ subtotal       │  │
└────────────────┘  │
                    │
┌─────────────────┐ │
│    FUNCIONARIOS │ │
│─────────────────│ │
│ id (PK)         │ │
│ nome            │ │
│ cpf (UNIQUE)    │ │
│ cargo           │ │
│ salario         │ │
│ comissao_%      │ │
│ data_admissao   │ │
│ ativo           │ │
└────────┬────────┘ │
         │ 1        │
         │          │
         │ N        │
┌────────▼────────┐ │
│     VENDAS      │ │
│ (já mostrada)  │ │
└─────────────────┘ │
                    │
┌─────────────────┐ │
│     FLORES      │ │
│─────────────────│ │
│ id (PK)         │ │
│ nome            │ │
│ especie         │ │
│ cor             │ │
│ preco           │ │
│ estoque         │ │
│ estoque_minimo  │ │
│ fornecedor_id   │──┐
│ ativo           │  │
└────────┬────────┘  │
         │ N         │
         │           │
         │ 1         │
┌────────▼────────┐  │
│  FORNECEDORES   │  │
│─────────────────│  │
│ id (PK)         │  │
│ nome            │  │
│ cnpj (UNIQUE)   │  │
│ telefone        │  │
│ email           │  │
│ contato_resp    │  │
│ ativo           │  │
└─────────────────┘  │
                     │
┌─────────────────┐  │
│    ARRANJOS     │  │
│─────────────────│  │
│ id (PK)         │  │
│ nome            │  │
│ descricao       │  │
│ preco           │  │
│ ativo           │  │
└────────┬────────┘  │
         │ 1         │
         │           │
         │ N         │
┌────────▼────────────┐
│  ARRANJOS_FLORES   │
│  (Tabela N:M)      │
│────────────────────│
│ id (PK)            │
│ arranjo_id (FK)    │
│ flor_id (FK)       │◄┘
│ quantidade         │
└────────────────────┘

┌─────────────────┐
│    USUARIOS     │
│─────────────────│
│ id (PK)         │
│ funcionario_id  │──┐ (opcional)
│ username        │  │
│ email           │  │
│ senha_hash      │  │
│ role            │  │
│ ativo           │  │
└─────────────────┘  │
                     │
┌─────────────────┐  │
│  FUNCIONARIOS   │  │
│ (já mostrada)   │  │
└─────────────────┘  │

┌─────────────────┐
│ HISTORICO_ESTOQUE│
│─────────────────│
│ id (PK)         │
│ flor_id (FK)    │──┐
│ tipo_movimentacao│  │
│ quantidade      │  │
│ quantidade_ant  │  │
│ quantidade_atual│  │
│ motivo          │  │
│ usuario_id (FK) │  │
│ venda_id (FK)   │  │
└─────────────────┘  │
                     │
┌─────────────────┐  │
│     FLORES      │  │
│ (já mostrada)   │  │
└─────────────────┘  │
```

## 📋 Resumo das Tabelas

### Tabelas Principais

1. **clientes** - Cadastro de clientes
   - Relacionamento: 1:N com `vendas`
   - Campos únicos: `cpf`
   - Campos calculados: `gasto_total` (atualizado via trigger)

2. **funcionarios** - Cadastro de funcionários
   - Relacionamento: 1:N com `vendas`, 1:1 opcional com `usuarios`
   - Campos únicos: `cpf`
   - Campos especiais: `comissao_percentual`

3. **fornecedores** - Cadastro de fornecedores
   - Relacionamento: 1:N com `flores`
   - Campos únicos: `cnpj`

4. **flores** - Catálogo de flores
   - Relacionamento: N:1 com `fornecedores`, 1:N com `itens_venda`, N:M com `arranjos`
   - Campos especiais: `estoque`, `estoque_minimo`

5. **vendas** - Registro de vendas
   - Relacionamento: N:1 com `clientes`, N:1 com `funcionarios`, 1:N com `itens_venda`
   - Campos calculados: `valor_total`, `valor_final`
   - Status: `pendente`, `finalizada`, `cancelada`

6. **itens_venda** - Itens de cada venda
   - Relacionamento: N:1 com `vendas`, N:1 com `flores`
   - Campos calculados: `subtotal`

### Tabelas Auxiliares

7. **arranjos** - Arranjos florais compostos
   - Relacionamento: N:M com `flores` (via `arranjos_flores`)

8. **arranjos_flores** - Tabela de relacionamento N:M
   - Relacionamento: N:1 com `arranjos`, N:1 com `flores`

9. **usuarios** - Sistema de autenticação
   - Relacionamento: 1:1 opcional com `funcionarios`
   - Roles: `admin`, `gerente`, `vendedor`, `estoque`

10. **historico_estoque** - Auditoria de movimentações
    - Relacionamento: N:1 com `flores`, N:1 com `usuarios`, N:1 com `vendas`

## 🔑 Chaves Primárias e Estrangeiras

### Chaves Primárias
- Todas as tabelas usam `id INT AUTO_INCREMENT` como PK

### Chaves Estrangeiras Principais
- `vendas.cliente_id` → `clientes.id`
- `vendas.funcionario_id` → `funcionarios.id`
- `itens_venda.venda_id` → `vendas.id`
- `itens_venda.flor_id` → `flores.id`
- `flores.fornecedor_id` → `fornecedores.id`
- `arranjos_flores.arranjo_id` → `arranjos.id`
- `arranjos_flores.flor_id` → `flores.id`
- `usuarios.funcionario_id` → `funcionarios.id`
- `historico_estoque.flor_id` → `flores.id`
- `historico_estoque.usuario_id` → `usuarios.id`
- `historico_estoque.referencia_venda_id` → `vendas.id`

## 🔄 Relacionamentos

### 1:1 (Um para Um)
- `usuarios` ↔ `funcionarios` (opcional - um funcionário pode ter um usuário)

### 1:N (Um para Muitos)
- `clientes` → `vendas` (um cliente pode ter várias vendas)
- `funcionarios` → `vendas` (um funcionário pode realizar várias vendas)
- `fornecedores` → `flores` (um fornecedor pode fornecer várias flores)
- `vendas` → `itens_venda` (uma venda pode ter vários itens)
- `flores` → `itens_venda` (uma flor pode estar em vários itens de venda)
- `flores` → `historico_estoque` (uma flor pode ter várias movimentações)

### N:M (Muitos para Muitos)
- `arranjos` ↔ `flores` (via `arranjos_flores`)

## 📈 Índices Criados

### Índices Simples
- CPF/CNPJ (campos únicos)
- Nomes (busca rápida)
- Status/Ativo (filtros frequentes)
- Datas (relatórios temporais)

### Índices Compostos
- `vendas(cliente_id, data_venda)` - Histórico de compras
- `vendas(funcionario_id, data_venda)` - Vendas por funcionário
- `flores(ativo, estoque)` - Consultas de estoque

## ⚙️ Triggers Implementados

1. **trg_atualizar_estoque_inserir_item**
   - Atualiza estoque ao adicionar item de venda
   - Registra movimentação no histórico

2. **trg_atualizar_valor_venda_inserir**
   - Calcula valor total da venda ao inserir item
   - Atualiza `valor_final` considerando desconto

3. **trg_atualizar_gasto_cliente**
   - Atualiza `gasto_total` do cliente ao finalizar venda
   - Permite promoção automática para VIP

## 👁️ Views Criadas

1. **vw_vendas_por_cliente** - Resumo de compras por cliente
2. **vw_vendas_por_funcionario** - Resumo de vendas e comissões
3. **vw_estoque_baixo** - Alertas de estoque mínimo
4. **vw_vendas_mes_atual** - Vendas do mês corrente

## 🚀 Como Usar

### MySQL
```bash
mysql -u root -p < schema_floricultura.sql
```

### PostgreSQL (requer ajustes)
1. Substituir `AUTO_INCREMENT` por `SERIAL`
2. Ajustar sintaxe de triggers
3. Substituir `ENUM` por `CHECK` constraints
4. Ajustar `CURRENT_TIMESTAMP` para `NOW()`

## 📝 Observações Importantes

1. **Senha do Admin**: O hash da senha padrão deve ser alterado em produção
2. **Estoque**: Controlado via triggers e histórico
3. **Valores Monetários**: Usar `DECIMAL(10,2)` para precisão
4. **Timestamps**: `created_at` e `updated_at` automáticos
5. **Soft Delete**: Usar campo `ativo` ao invés de DELETE físico
6. **Integridade**: Foreign keys com `ON DELETE RESTRICT` para proteger dados

## 🔒 Segurança

- Senhas devem usar hash (bcrypt recomendado)
- Campos sensíveis (CPF, CNPJ) com índices únicos
- Roles de usuário para controle de acesso
- Histórico de estoque para auditoria

## 📊 Métricas e Relatórios

O modelo suporta:
- Relatórios de vendas por período
- Análise de clientes VIP
- Controle de comissões de funcionários
- Gestão de estoque com alertas
- Auditoria completa de movimentações

## 👨‍💻 Desenvolvido por: 
- Kaick Ramos de Melo Silva;
- Thyago Felipe Albuquerque de Santana;
- Priscila Gomes da Costa;
- Ingred Myllena Duarte do Carmo;
- Matheus do Nascimento Costa;
- Biehof Flávio da Silva Dimas;


Sistema desenvolvido seguindo as melhores práticas de Programação Orientada a Objetos e arquitetura MVC em Java.

