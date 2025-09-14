// Classes do sistema
class Produto {
    constructor(nome, preco, estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }
}

class Flor extends Produto {
    constructor(nome, preco, estoque, cor, especie) {
        super(nome, preco, estoque);
        this.cor = cor;
        this.especie = especie;
        this.tipo = 'flor';
    }
}

class VasoDecorado extends Produto {
    constructor(nome, preco, estoque, material, estilo) {
        super(nome, preco, estoque);
        this.material = material;
        this.estilo = estilo;
        this.tipo = 'vaso';
    }
}

class Cliente {
    constructor(nome, cpf, telefone, codigoDesconto) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.codigoDesconto = codigoDesconto;
    }
}

class Pedido {
    constructor(numeroPedido, cliente) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.produtos = [];
        this.valorTotal = 0;
        this.valorComDesconto = 0;
        this.percentualDesconto = this.calcularDesconto(cliente.codigoDesconto);
    }

    calcularDesconto(codigo) {
        const descontos = {
            'Kr': 100,
            'Myllena': 70,
            'Matheus': 80,
            'Thiago': 80,
            'Priscila': 70
        };

        return descontos[codigo] || 0;
    }

    adicionarProduto(produto) {
        this.produtos.push(produto);
        this.valorTotal += produto.preco;
        this.atualizarValorComDesconto();
    }

    atualizarValorComDesconto() {
        const desconto = this.valorTotal * (this.percentualDesconto / 100);
        this.valorComDesconto = this.valorTotal - desconto;
    }
}

// Dados iniciais
const produtos = [
    new Flor("Rosa Vermelha", 15.90, 50, "Vermelha", "Rosa"),
    new Flor("Orquídea", 45.90, 30, "Rosa", "Phalaenopsis"),
    new Flor("Lírio", 25.90, 40, "Branco", "Asiático"),
    new VasoDecorado("Vaso Vintage", 89.90, 10, "Cerâmica", "Rústico"),
    new VasoDecorado("Vaso Moderno", 129.90, 15, "Vidro", "Contemporâneo"),
    new VasoDecorado("Vaso Clássico", 69.90, 20, "Porcelana", "Tradicional")
];

let pedidoAtual = null;
let numerosPedidos = 0;

// Funções de interface
function exibirProdutos() {
    const container = document.getElementById('produtos-lista');
    container.innerHTML = '';

    produtos.forEach(produto => {
        const card = document.createElement('div');
        card.className = 'produto-card';
        
        let detalhes = '';
        if (produto.tipo === 'flor') {
            detalhes = `<p>Espécie: ${produto.especie}</p>
                       <p>Cor: ${produto.cor}</p>`;
        } else {
            detalhes = `<p>Material: ${produto.material}</p>
                       <p>Estilo: ${produto.estilo}</p>`;
        }

        card.innerHTML = `
            <h3>${produto.nome}</h3>
            ${detalhes}
            <p>Preço: R$ ${produto.preco.toFixed(2)}</p>
            <p>Estoque: ${produto.estoque}</p>
            <button onclick="adicionarAoPedido(${produtos.indexOf(produto)})">
                Adicionar ao Pedido
            </button>
        `;
        container.appendChild(card);
    });
}

function adicionarAoPedido(index) {
    if (!pedidoAtual) {
        alert('Por favor, preencha os dados do cliente primeiro!');
        return;
    }

    const produto = produtos[index];
    if (produto.estoque > 0) {
        pedidoAtual.adicionarProduto(produto);
        produto.estoque--;
        atualizarPedido();
        exibirProdutos();
    } else {
        alert('Produto fora de estoque!');
    }
}

function atualizarPedido() {
    const detalhes = document.getElementById('pedido-detalhes');
    if (!pedidoAtual) {
        detalhes.innerHTML = '<p>Nenhum pedido em andamento</p>';
        return;
    }

    let produtosHtml = pedidoAtual.produtos.map(p => 
        `<li>${p.nome} - R$ ${p.preco.toFixed(2)}</li>`
    ).join('');

    let descontoHtml = '';
    if (pedidoAtual.percentualDesconto > 0) {
        descontoHtml = `
            <p>Desconto aplicado: ${pedidoAtual.percentualDesconto}%</p>
            <p>Valor com desconto: R$ ${pedidoAtual.valorComDesconto.toFixed(2)}</p>
        `;
    }

    detalhes.innerHTML = `
        <h3>Cliente: ${pedidoAtual.cliente.nome}</h3>
        <p>CPF: ${pedidoAtual.cliente.cpf}</p>
        <p>Telefone: ${pedidoAtual.cliente.telefone}</p>
        <h4>Produtos:</h4>
        <ul>${produtosHtml}</ul>
        <p><strong>Subtotal: R$ ${pedidoAtual.valorTotal.toFixed(2)}</strong></p>
        ${descontoHtml}
    `;
}

// Event Listeners
document.getElementById('cliente-form').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const nome = document.getElementById('nome').value;
    const cpf = document.getElementById('cpf').value;
    const telefone = document.getElementById('telefone').value;
    const codigoDesconto = document.getElementById('codigo_desconto').value;

    const cliente = new Cliente(nome, cpf, telefone, codigoDesconto);
    numerosPedidos++;
    pedidoAtual = new Pedido(numerosPedidos, cliente);
    
    atualizarPedido();
    
    if (pedidoAtual.percentualDesconto > 0) {
        alert(`Cliente registrado com sucesso! Desconto de ${pedidoAtual.percentualDesconto}% aplicado.`);
    } else {
        alert('Cliente registrado com sucesso!');
    }
});

// Inicialização
window.onload = function() {
    exibirProdutos();
    atualizarPedido();
}; 