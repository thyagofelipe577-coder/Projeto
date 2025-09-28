# Arquitetura do Sistema de Floricultura

## 🏗️ Visão Geral da Arquitetura MVC

O sistema foi desenvolvido seguindo o padrão arquitetural MVC (Model-View-Controller), garantindo separação clara de responsabilidades e facilitando manutenção e evolução.

## 📊 Diagrama de Camadas

```
┌─────────────────────────────────────────────────────────────┐
│                        VIEW LAYER                           │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              MenuPrincipal.java                     │   │
│  │  • Interface do usuário                            │   │
│  │  • Menus e navegação                               │   │
│  │  • Relatórios e estatísticas                       │   │
│  └─────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                    CONTROLLER LAYER                         │
│  ┌─────────────┬─────────────┬─────────────┬─────────────┐ │
│  │FlorController│ClienteCtrl  │FornecedorCtrl│FuncionarioCtrl│ │
│  │• Validação  │• Validação  │• Validação  │• Validação  │ │
│  │• Coordenação│• Coordenação│• Coordenação│• Coordenação│ │
│  └─────────────┴─────────────┴─────────────┴─────────────┘ │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              VendaController                        │   │
│  │  • Coordenação de vendas                           │   │
│  │  • Gestão de itens                                 │   │
│  └─────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                      MODEL LAYER                            │
│  ┌─────────────────────────────────────────────────────┐   │
│  │                   SERVICES                           │   │
│  │  • Regras de negócio                                │   │
│  │  • Validações complexas                             │   │
│  │  │  ┌─────────┬─────────┬─────────┬─────────┐  │   │
│  │  │  │FlorServ │Cliente  │Fornecedor│Funcionario│  │   │
│  │  │  │-ico     │Servico  │Servico  │Servico  │  │   │
│  │  │  └─────────┴─────────┴─────────┴─────────┘  │   │
│  │  └─────────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────────┐   │
│  │                  REPOSITORIES                       │   │
│  │  • Persistência de dados                           │   │
│  │  • Operações CRUD                                  │   │
│  │  │  ┌─────────┬─────────┬─────────┬─────────┐  │   │
│  │  │  │FlorRepo │Cliente  │Fornecedor│Funcionario│  │   │
│  │  │  │-sitorio │Repositorio│Repositorio│Repositorio│  │   │
│  │  │  └─────────┴─────────┴─────────┴─────────┘  │   │
│  │  └─────────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────────┐   │
│  │                    ENTITIES                         │   │
│  │  • Modelos de dados                                 │   │
│  │  • Validações básicas                               │   │
│  │  │  ┌─────────┬─────────┬─────────┬─────────┐  │   │
│  │  │  │  Flor   │ Cliente │Fornecedor│Funcionario│  │   │
│  │  │  │         │(herda)  │         │(herda)  │  │   │
│  │  │  └─────────┴─────────┴─────────┴─────────┘  │   │
│  │  │  ┌─────────┬─────────┬─────────┬─────────┐  │   │
│  │  │  │  Venda  │ItemVenda│  Pessoa │         │  │   │
│  │  │  │         │         │(abstract)│         │  │   │
│  │  │  └─────────┴─────────┴─────────┴─────────┘  │   │
│  │  └─────────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────────┐   │
│  │                   INTERFACES                        │   │
│  │  • Contratos para repositórios e serviços          │   │
│  │  • Facilita manutenção e testes                    │   │
│  │  │  ┌─────────┬─────────┬─────────┬─────────┐  │   │
│  │  │  │IRepositorio│IServico│IFlorRep│IClienteRep│  │   │
│  │  │  │         │         │-ositorio│-ositorio│  │   │
│  │  │  └─────────┴─────────┴─────────┴─────────┘  │   │
│  │  └─────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
```

## 🔄 Fluxo de Dados

### 1. Entrada do Usuário
```
MenuPrincipal → Controller → Service → Repository → Entity
```

### 2. Processamento
```
Entity ← Repository ← Service ← Controller ← MenuPrincipal
```

### 3. Saída
```
MenuPrincipal ← Controller ← Service ← Repository ← Entity
```

## 🎯 Princípios de POO Implementados

### 1. Herança
- **Classe Abstrata**: `Pessoa`
- **Classes Filhas**: `Cliente` e `Funcionario`
- **Métodos Abstratos**: `validarDados()` e `getInformacoesEspecificas()`

### 2. Polimorfismo
- **Sobrescrita**: Métodos das classes filhas implementam comportamentos específicos
- **Sobrecarga**: Construtores com diferentes parâmetros
- **Runtime Polymorphism**: Chamadas de métodos resolvidas em tempo de execução

### 3. Encapsulamento
- **Atributos Privados**: Todos os campos são private
- **Getters/Setters**: Controle de acesso aos dados
- **Validações**: Métodos setters incluem validações

### 4. Abstração
- **Interfaces**: Contratos bem definidos
- **Classes Abstratas**: `Pessoa` e `RepositorioBase`
- **Ocultação de Complexidade**: Detalhes de implementação escondidos

## 📦 Estrutura de Pacotes

```
com.floricultura/
├── Main.java                    # Ponto de entrada
├── model/
│   ├── entities/                # Entidades do domínio
│   ├── repositories/            # Camada de persistência
│   ├── services/                # Regras de negócio
│   ├── interfaces/              # Contratos
│   └── exceptions/              # Exceções customizadas
├── controller/                  # Controladores MVC
└── view/                        # Interface do usuário
```

## 🔧 Padrões de Design Utilizados

### 1. MVC (Model-View-Controller)
- **Separação de Responsabilidades**
- **Baixo Acoplamento**
- **Alta Coesão**

### 2. Repository Pattern
- **Abstração da Persistência**
- **Facilita Testes**
- **Permite Mudança de Implementação**

### 3. Service Layer Pattern
- **Regras de Negócio Centralizadas**
- **Reutilização de Código**
- **Facilita Manutenção**

### 4. Interface Segregation
- **Interfaces Específicas**
- **Contratos Bem Definidos**
- **Facilita Implementação**

## 🚀 Vantagens da Arquitetura

### 1. Manutenibilidade
- Código organizado e bem estruturado
- Fácil localização de funcionalidades
- Modificações isoladas por camada

### 2. Escalabilidade
- Fácil adição de novas funcionalidades
- Possibilidade de mudança de implementação
- Suporte a crescimento do sistema

### 3. Testabilidade
- Cada camada pode ser testada independentemente
- Mocks e stubs facilitam testes unitários
- Isolamento de responsabilidades

### 4. Reutilização
- Código reutilizável entre diferentes partes
- Interfaces permitem múltiplas implementações
- Padrões consistentes em todo o sistema

## 🔄 Fluxo de Execução Típico

1. **Usuário** interage com o menu
2. **View** captura a entrada
3. **Controller** processa a requisição
4. **Service** aplica regras de negócio
5. **Repository** acessa os dados
6. **Entity** representa o modelo
7. **Resposta** retorna pela mesma cadeia

## 📈 Extensibilidade

A arquitetura permite facilmente:
- Adicionar novas entidades
- Implementar novos repositórios
- Criar novos serviços
- Adicionar novas funcionalidades
- Integrar com banco de dados
- Implementar APIs REST
- Adicionar interface web

## 🎯 Conclusão

A arquitetura MVC implementada no Sistema de Floricultura segue as melhores práticas de desenvolvimento, garantindo:

- **Código Limpo e Organizado**
- **Fácil Manutenção**
- **Alta Escalabilidade**
- **Boa Testabilidade**
- **Reutilização de Código**
- **Separação de Responsabilidades**

Esta estrutura serve como base sólida para futuras expansões e melhorias do sistema.
