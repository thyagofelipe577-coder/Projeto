# Instruções para Execução do Sistema de Floricultura

## 📋 Pré-requisitos

Para executar o sistema, você precisa ter instalado:

1. **Java Development Kit (JDK) 8 ou superior**
   - Download: https://www.oracle.com/java/technologies/downloads/
   - Ou use OpenJDK: https://openjdk.org/

2. **Verificar instalação:**
   ```bash
   java -version
   javac -version
   ```

## 🚀 Como Executar

### Passo 1: Compilar o Projeto

Abra o terminal/prompt de comando na pasta do projeto e execute:

```bash
# Navegar para a pasta do projeto
cd C:\Users\User\.cursor\floricultura

# Compilar todos os arquivos Java
javac -d . src/main/java/com/floricultura/*.java src/main/java/com/floricultura/**/*.java
```

### Passo 2: Executar o Sistema

```bash
# Executar o sistema
java com.floricultura.Main
```

## 🔧 Alternativa: Usando IDE

Se preferir usar uma IDE como Eclipse, IntelliJ IDEA ou VS Code:

1. **Importar o projeto** como projeto Java
2. **Configurar o source folder** para `src/main/java`
3. **Executar a classe** `com.floricultura.Main`

## 📁 Estrutura Após Compilação

Após a compilação, a estrutura ficará assim:

```
floricultura/
├── com/floricultura/
│   ├── Main.class
│   ├── model/
│   │   ├── entities/
│   │   ├── repositories/
│   │   ├── services/
│   │   ├── interfaces/
│   │   └── exceptions/
│   ├── controller/
│   └── view/
├── src/
└── README.md
```

## 🎯 Funcionalidades do Sistema

### Menu Principal
- **1. Gerenciar Flores** - CRUD completo de flores
- **2. Gerenciar Clientes** - Cadastro e gestão de clientes
- **3. Gerenciar Fornecedores** - Gestão de fornecedores
- **4. Gerenciar Funcionários** - Administração de funcionários
- **5. Gerenciar Vendas** - Sistema de vendas integrado
- **6. Relatórios** - Estatísticas e relatórios
- **7. Sobre o Sistema** - Informações técnicas

### Características Técnicas
- ✅ Arquitetura MVC implementada
- ✅ Herança (Classe Pessoa abstrata)
- ✅ Polimorfismo em métodos das classes herdadas
- ✅ Encapsulamento com getters/setters
- ✅ Interfaces para repositórios e serviços
- ✅ Tratamento de exceções customizadas
- ✅ CRUD completo para todas as entidades
- ✅ Persistência em ArrayList (memória)

## 🐛 Solução de Problemas

### Erro: "javac não é reconhecido"
- Instale o JDK (não apenas o JRE)
- Adicione o JDK ao PATH do sistema
- Reinicie o terminal após instalação

### Erro: "Não foi possível localizar a classe principal"
- Certifique-se de que a compilação foi bem-sucedida
- Verifique se está na pasta correta
- Execute: `java -cp . com.floricultura.Main`

### Erro de Compilação
- Verifique se todos os arquivos estão presentes
- Certifique-se de que está usando Java 8 ou superior
- Verifique se não há caracteres especiais no caminho

## 📞 Suporte

Se encontrar problemas:
1. Verifique se o JDK está instalado corretamente
2. Confirme que está na pasta correta do projeto
3. Execute os comandos exatamente como mostrado
4. Verifique se não há erros de compilação

## 🎉 Pronto!

Após seguir estes passos, o sistema estará funcionando e você poderá:
- Cadastrar flores, clientes, fornecedores e funcionários
- Realizar vendas com controle de estoque
- Gerar relatórios e estatísticas
- Gerenciar todo o negócio da floricultura

**Bom uso do sistema! 🌹**
