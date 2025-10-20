# Implementação JDBC no Sistema de Floricultura

Este documento descreve a implementação da interação com banco de dados utilizando JDBC e PreparedStatement no sistema de floricultura.

## 📋 Visão Geral

A implementação JDBC foi adicionada ao projeto existente, mantendo a compatibilidade com a arquitetura atual e adicionando persistência de dados em banco de dados.

## 🏗️ Arquitetura Implementada

### Componentes Principais

1. **DatabaseConfig** - Configuração e gerenciamento de conexões
2. **ClienteRepositorioJDBC** - Implementação JDBC do repositório de clientes
3. **IClienteRepositorioJDBC** - Interface específica para operações JDBC
4. **ClienteRepositorioJDBCDemo** - Classe de demonstração e teste

### Estrutura de Arquivos

```
src/main/java/com/floricultura/
├── config/
│   └── DatabaseConfig.java                    # Configuração do banco
├── model/
│   ├── interfaces/
│   │   └── IClienteRepositorioJDBC.java       # Interface JDBC
│   └── repositories/
│       └── ClienteRepositorioJDBC.java        # Implementação JDBC
└── test/
    └── ClienteRepositorioJDBCDemo.java        # Demonstração

src/main/resources/
├── application.properties                     # Configurações do banco
├── logback.xml                               # Configuração de logs
└── database/
    └── init.sql                              # Script de inicialização
```

## 🛠️ Tecnologias Utilizadas

- **JDBC** - API Java para acesso a banco de dados
- **HikariCP** - Pool de conexões de alta performance
- **H2 Database** - Banco em memória para desenvolvimento
- **MySQL/PostgreSQL** - Suporte para bancos de produção
- **SLF4J + Logback** - Sistema de logging
- **Maven** - Gerenciamento de dependências

## 📊 Banco de Dados

### Tabela de Clientes

```sql
CREATE TABLE clientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(255),
    endereco VARCHAR(500),
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_cadastro DATE NOT NULL,
    total_gasto DECIMAL(10,2) DEFAULT 0.0,
    cliente_vip BOOLEAN DEFAULT FALSE
);
```

### Índices para Performance

- `idx_clientes_cpf` - Busca rápida por CPF
- `idx_clientes_nome` - Busca rápida por nome
- `idx_clientes_vip` - Filtro de clientes VIP

## 🔧 Configuração

### application.properties

```properties
# Banco H2 (desenvolvimento)
db.driver=h2
db.url=jdbc:h2:mem:floricultura;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
db.username=sa
db.password=

# Pool de conexões
db.pool.maximum-pool-size=10
db.pool.minimum-idle=5
db.pool.connection-timeout=30000
```

### Dependências Maven

```xml
<dependencies>
    <!-- Driver JDBC para MySQL -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>
    
    <!-- Pool de conexões HikariCP -->
    <dependency>
        <groupId>com.zaxxer</groupId>
        <artifactId>HikariCP</artifactId>
        <version>5.0.1</version>
    </dependency>
</dependencies>
```

## 🚀 Como Usar

### 1. Inicializar o Repositório

```java
ClienteRepositorioJDBC repositorio = new ClienteRepositorioJDBC();
repositorio.inicializarTabela();
```

### 2. Operações CRUD Básicas

```java
// Cadastrar cliente
Cliente cliente = new Cliente();
cliente.setNome("João Silva");
cliente.setCpf("123.456.789-00");
// ... outros campos
boolean sucesso = repositorio.cadastrar(cliente);

// Pesquisar por ID
Cliente encontrado = repositorio.pesquisarPorId(1);

// Listar todos
List<Cliente> clientes = repositorio.listarTodos();

// Atualizar
cliente.setTelefone("(11) 99999-9999");
boolean atualizado = repositorio.atualizar(cliente);

// Remover
boolean removido = repositorio.remover(1);
```

### 3. Consultas Específicas

```java
// Pesquisar por CPF
Cliente cliente = repositorio.pesquisarPorCpfJDBC("123.456.789-00");

// Pesquisar por nome
List<Cliente> clientes = repositorio.pesquisarPorNomeJDBC("João");

// Listar clientes VIP
List<Cliente> vips = repositorio.listarClientesVipJDBC();

// Listar por faixa de gasto
List<Cliente> clientes = repositorio.listarPorFaixaGastoJDBC(500.0, 1500.0);
```

### 4. Atualizações Específicas

```java
// Atualizar total gasto
repositorio.atualizarTotalGasto(1, 1000.0);

// Atualizar status VIP
repositorio.atualizarStatusVip(1, true);
```

## 🔒 Segurança

### PreparedStatement

Todas as consultas utilizam PreparedStatement para prevenir SQL Injection:

```java
String sql = "SELECT * FROM clientes WHERE cpf = ?";
PreparedStatement statement = connection.prepareStatement(sql);
statement.setString(1, cpf);
```

### Validação de Dados

```java
if (cliente == null || !cliente.validarDados()) {
    logger.warn("Tentativa de cadastrar cliente inválido");
    return false;
}
```

## 📈 Performance

### Pool de Conexões

- **HikariCP** para gerenciamento eficiente de conexões
- Configuração otimizada para diferentes ambientes
- Monitoramento de conexões ativas e ociosas

### Índices de Banco

- Índices criados para campos frequentemente consultados
- Otimização de consultas por CPF, nome e status VIP

### Logging

- Logs detalhados para monitoramento
- Diferentes níveis de log (DEBUG, INFO, ERROR)
- Arquivo de log com rotação automática

## 🧪 Testes

### Executar Demonstração

```bash
# Compilar o projeto
mvn compile

# Executar a demonstração
mvn exec:java -Dexec.mainClass="com.floricultura.test.ClienteRepositorioJDBCDemo"
```

### Classe de Teste

A classe `ClienteRepositorioJDBCDemo` demonstra:
- Inicialização da tabela
- Operações CRUD completas
- Consultas específicas com PreparedStatement
- Atualizações de campos específicos
- Tratamento de erros e logging

## 🔄 Migração

### Do Repositório em Memória para JDBC

1. **Mantém compatibilidade** - A interface `IClienteRepositorio` é preservada
2. **Adiciona funcionalidades** - Nova interface `IClienteRepositorioJDBC`
3. **Configuração flexível** - Suporte a múltiplos bancos de dados
4. **Logs detalhados** - Monitoramento completo das operações

### Exemplo de Migração

```java
// Antes (em memória)
ClienteRepositorio repositorioMemoria = new ClienteRepositorio();

// Depois (JDBC)
ClienteRepositorioJDBC repositorioJDBC = new ClienteRepositorioJDBC();
repositorioJDBC.inicializarTabela();
```

## 📝 Próximos Passos

1. **Implementar outros repositórios** - Flor, Fornecedor, Funcionário, Venda
2. **Adicionar transações** - Controle de transações para operações complexas
3. **Implementar cache** - Cache de consultas frequentes
4. **Adicionar testes unitários** - Cobertura completa de testes
5. **Configurar CI/CD** - Pipeline de integração contínua

## 🐛 Troubleshooting

### Problemas Comuns

1. **Erro de conexão** - Verificar configurações do banco
2. **Tabela não encontrada** - Executar `inicializarTabela()`
3. **Pool esgotado** - Ajustar configurações do HikariCP
4. **Logs excessivos** - Ajustar níveis de log no logback.xml

### Logs Úteis

```bash
# Verificar status do pool
DatabaseConfig.getPoolInfo()

# Verificar saúde do banco
DatabaseConfig.isHealthy()
```

## 📚 Referências

- [Documentação JDBC Oracle](https://docs.oracle.com/javase/tutorial/jdbc/)
- [HikariCP GitHub](https://github.com/brettwooldridge/HikariCP)
- [H2 Database](https://www.h2database.com/)
- [SLF4J Manual](http://www.slf4j.org/manual.html)
