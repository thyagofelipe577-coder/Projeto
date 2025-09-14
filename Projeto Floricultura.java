// Classe abstrata base para produtos
abstract class Produto {
    protected String nome;
    protected double preco;
    protected int estoque;

    public Produto() {}

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = 0;
    }

    public Produto(String nome, double preco, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    // Método abstrato que deve ser implementado pelas classes filhas
    public abstract void exibirDetalhes();
}

// Classe Flor que herda de Produto
class Flor extends Produto {
    private String cor;
    private String especie;

    public Flor() {}

    public Flor(String nome, double preco, String cor) {
        super(nome, preco);
        this.cor = cor;
    }

    public Flor(String nome, double preco, int estoque, String cor, String especie) {
        super(nome, preco, estoque);
        this.cor = cor;
        this.especie = especie;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Flor: " + nome + ", Espécie: " + especie + ", Cor: " + cor + ", Preço: R$" + preco);
    }
}

// Classe VasoDecorado que herda de Produto
class VasoDecorado extends Produto {
    private String material;
    private String estilo;

    public VasoDecorado() {}

    public VasoDecorado(String nome, double preco, String material) {
        super(nome, preco);
        this.material = material;
    }

    public VasoDecorado(String nome, double preco, int estoque, String material, String estilo) {
        super(nome, preco, estoque);
        this.material = material;
        this.estilo = estilo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Vaso: " + nome + ", Material: " + material + ", Estilo: " + estilo + ", Preço: R$" + preco);
    }
}

// Classe Cliente
class Cliente {
    private String nome;
    private String cpf;
    private String telefone;

    public Cliente() {}

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Cliente(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}

// Classe Pedido
class Pedido {
    private int numeroPedido;
    private Cliente cliente;
    private Produto[] produtos;
    private double valorTotal;

    public Pedido() {
        this.produtos = new Produto[10];
    }

    public Pedido(int numeroPedido, Cliente cliente) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.produtos = new Produto[10];
    }

    public Pedido(int numeroPedido, Cliente cliente, Produto[] produtos, double valorTotal) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.produtos = produtos;
        this.valorTotal = valorTotal;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto[] getProdutos() {
        return produtos;
    }

    public void setProdutos(Produto[] produtos) {
        this.produtos = produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}

// Classe principal com método main
public class Floricultura {
    public static void main(String[] args) {
        // Criando alguns objetos
        Flor rosa = new Flor("Rosa Vermelha", 15.90, 50, "Vermelha", "Rosa");
        VasoDecorado vaso = new VasoDecorado("Vaso Vintage", 89.90, 10, "Cerâmica", "Rústico");
        Cliente cliente = new Cliente("Maria Silva", "123.456.789-00", "(11) 99999-9999");
        
        // Criando um pedido
        Pedido pedido = new Pedido(1, cliente);
        
        // Exibindo detalhes dos produtos
        rosa.exibirDetalhes();
        vaso.exibirDetalhes();
        
        System.out.println("\nDetalhes do Cliente:");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Telefone: " + cliente.getTelefone());
    }
}
