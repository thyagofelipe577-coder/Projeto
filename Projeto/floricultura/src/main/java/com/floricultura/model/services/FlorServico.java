package com.floricultura.model.services;

import com.floricultura.model.entities.Flor;
import com.floricultura.model.interfaces.IFlorRepositorio;
import com.floricultura.model.interfaces.IFlorServico;
import java.util.List;

/**
 * Implementação do serviço de flores com regras de negócio.
 */
public class FlorServico implements IFlorServico {
    private IFlorRepositorio repositorio;

    public FlorServico(IFlorRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public boolean cadastrar(Flor entidade) throws Exception {
        if (!validar(entidade)) {
            throw new Exception("Dados da flor inválidos");
        }
        
        if (repositorio.existe(entidade.getId())) {
            throw new Exception("Flor com ID " + entidade.getId() + " já existe");
        }
        
        return repositorio.cadastrar(entidade);
    }

    @Override
    public boolean remover(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        if (!repositorio.existe(id)) {
            throw new Exception("Flor com ID " + id + " não encontrada");
        }
        
        return repositorio.remover(id);
    }

    @Override
    public boolean atualizar(Flor entidade) throws Exception {
        if (!validar(entidade)) {
            throw new Exception("Dados da flor inválidos");
        }
        
        if (!repositorio.existe(entidade.getId())) {
            throw new Exception("Flor com ID " + entidade.getId() + " não encontrada");
        }
        
        return repositorio.atualizar(entidade);
    }

    @Override
    public Flor pesquisarPorId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        return repositorio.pesquisarPorId(id);
    }

    @Override
    public List<Flor> listarTodos() throws Exception {
        return repositorio.listarTodos();
    }

    @Override
    public boolean validar(Flor entidade) {
        if (entidade == null) {
            return false;
        }
        
        return entidade.getNome() != null && !entidade.getNome().trim().isEmpty() &&
               entidade.getEspecie() != null && !entidade.getEspecie().trim().isEmpty() &&
               entidade.getCor() != null && !entidade.getCor().trim().isEmpty() &&
               entidade.getPreco() >= 0 &&
               entidade.getQuantidadeEstoque() >= 0;
    }

    @Override
    public List<Flor> pesquisarPorNome(String nome) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("Nome não pode ser vazio");
        }
        
        return repositorio.pesquisarPorNome(nome);
    }

    @Override
    public List<Flor> pesquisarPorEspecie(String especie) throws Exception {
        if (especie == null || especie.trim().isEmpty()) {
            throw new Exception("Espécie não pode ser vazia");
        }
        
        return repositorio.pesquisarPorEspecie(especie);
    }

    @Override
    public List<Flor> pesquisarPorCor(String cor) throws Exception {
        if (cor == null || cor.trim().isEmpty()) {
            throw new Exception("Cor não pode ser vazia");
        }
        
        return repositorio.pesquisarPorCor(cor);
    }

    @Override
    public List<Flor> listarDisponiveis() throws Exception {
        return repositorio.listarDisponiveis();
    }

    @Override
    public List<Flor> listarComEstoqueBaixo(int limite) throws Exception {
        if (limite < 0) {
            throw new Exception("Limite de estoque deve ser maior ou igual a zero");
        }
        
        return repositorio.listarComEstoqueBaixo(limite);
    }

    @Override
    public List<Flor> pesquisarPorFaixaPreco(double precoMinimo, double precoMaximo) throws Exception {
        if (precoMinimo < 0 || precoMaximo < 0) {
            throw new Exception("Preços devem ser maiores ou iguais a zero");
        }
        
        if (precoMinimo > precoMaximo) {
            throw new Exception("Preço mínimo não pode ser maior que o preço máximo");
        }
        
        return repositorio.pesquisarPorFaixaPreco(precoMinimo, precoMaximo);
    }

    @Override
    public boolean atualizarEstoque(int id, int quantidade) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        Flor flor = repositorio.pesquisarPorId(id);
        if (flor == null) {
            throw new Exception("Flor com ID " + id + " não encontrada");
        }
        
        if (quantidade > 0) {
            flor.adicionarEstoque(quantidade);
        } else if (quantidade < 0) {
            int quantidadeAbsoluta = Math.abs(quantidade);
            if (!flor.reduzirEstoque(quantidadeAbsoluta)) {
                throw new Exception("Estoque insuficiente para reduzir " + quantidadeAbsoluta + " unidades");
            }
        }
        
        return repositorio.atualizar(flor);
    }

    @Override
    public boolean verificarDisponibilidade(int id, int quantidade) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido");
        }
        
        if (quantidade <= 0) {
            throw new Exception("Quantidade deve ser maior que zero");
        }
        
        Flor flor = repositorio.pesquisarPorId(id);
        if (flor == null) {
            throw new Exception("Flor com ID " + id + " não encontrada");
        }
        
        return flor.estaDisponivelParaVenda() && flor.getQuantidadeEstoque() >= quantidade;
    }
}
