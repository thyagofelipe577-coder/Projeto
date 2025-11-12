-- ============================================================================
-- SISTEMA DE FLORICULTURA - MODELO DE BANCO DE DADOS (PostgreSQL)
-- ============================================================================
-- Banco de Dados: PostgreSQL 12+
-- Descrição: Modelo relacional completo para sistema de gerenciamento de floricultura
-- Autor: Baseado no projeto https://github.com/thyagofelipe577-coder/Projeto
-- Data: 2025
-- ============================================================================

-- Remover banco de dados existente (cuidado em produção!)
-- DROP DATABASE IF EXISTS floricultura_db;

-- Criar banco de dados
CREATE DATABASE floricultura_db
    WITH ENCODING = 'UTF8'
    LC_COLLATE = 'pt_BR.UTF-8'
    LC_CTYPE = 'pt_BR.UTF-8';

\c floricultura_db;

-- Criar extensões necessárias
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Criar tipos customizados
CREATE TYPE status_venda AS ENUM ('pendente', 'finalizada', 'cancelada');
CREATE TYPE forma_pagamento AS ENUM ('dinheiro', 'cartao_debito', 'cartao_credito', 'pix', 'boleto');
CREATE TYPE tipo_movimentacao AS ENUM ('entrada', 'saida', 'ajuste', 'perda');
CREATE TYPE role_usuario AS ENUM ('admin', 'gerente', 'vendedor', 'estoque');

-- ============================================================================
-- TABELA: clientes
-- ============================================================================
CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(255),
    data_cadastro DATE NOT NULL DEFAULT CURRENT_DATE,
    vip BOOLEAN NOT NULL DEFAULT FALSE,
    gasto_total DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE clientes IS 'Tabela de clientes da floricultura';
COMMENT ON COLUMN clientes.id IS 'Identificador único do cliente';
COMMENT ON COLUMN clientes.cpf IS 'CPF do cliente (formato: 000.000.000-00)';
COMMENT ON COLUMN clientes.vip IS 'Indica se o cliente é VIP (recebe descontos)';
COMMENT ON COLUMN clientes.gasto_total IS 'Total acumulado de gastos do cliente';

CREATE INDEX idx_clientes_cpf ON clientes(cpf);
CREATE INDEX idx_clientes_nome ON clientes(nome);
CREATE INDEX idx_clientes_vip ON clientes(vip);
CREATE INDEX idx_clientes_ativo ON clientes(ativo);
CREATE INDEX idx_clientes_data_cadastro ON clientes(data_cadastro);

-- ============================================================================
-- TABELA: funcionarios
-- ============================================================================
CREATE TABLE funcionarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(255),
    cargo VARCHAR(50) NOT NULL,
    salario DECIMAL(10, 2),
    data_admissao DATE NOT NULL,
    data_cadastro DATE NOT NULL DEFAULT CURRENT_DATE,
    comissao_percentual DECIMAL(5, 2) DEFAULT 0.00,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE funcionarios IS 'Tabela de funcionários da floricultura';
COMMENT ON COLUMN funcionarios.comissao_percentual IS 'Percentual de comissão sobre vendas';

CREATE INDEX idx_funcionarios_cpf ON funcionarios(cpf);
CREATE INDEX idx_funcionarios_nome ON funcionarios(nome);
CREATE INDEX idx_funcionarios_cargo ON funcionarios(cargo);
CREATE INDEX idx_funcionarios_ativo ON funcionarios(ativo);

-- ============================================================================
-- TABELA: fornecedores
-- ============================================================================
CREATE TABLE fornecedores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(255),
    contato_responsavel VARCHAR(100),
    data_cadastro DATE NOT NULL DEFAULT CURRENT_DATE,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE fornecedores IS 'Tabela de fornecedores de flores';
COMMENT ON COLUMN fornecedores.cnpj IS 'CNPJ do fornecedor (formato: 00.000.000/0000-00)';

CREATE INDEX idx_fornecedores_cnpj ON fornecedores(cnpj);
CREATE INDEX idx_fornecedores_nome ON fornecedores(nome);
CREATE INDEX idx_fornecedores_ativo ON fornecedores(ativo);

-- ============================================================================
-- TABELA: flores
-- ============================================================================
CREATE TABLE flores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    nome_cientifico VARCHAR(150),
    especie VARCHAR(100),
    cor VARCHAR(50),
    preco DECIMAL(10, 2) NOT NULL,
    estoque INT NOT NULL DEFAULT 0,
    estoque_minimo INT NOT NULL DEFAULT 5,
    fornecedor_id INT REFERENCES fornecedores(id) ON DELETE SET NULL ON UPDATE CASCADE,
    descricao TEXT,
    imagem_url VARCHAR(255),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE flores IS 'Tabela de flores disponíveis na floricultura';
COMMENT ON COLUMN flores.estoque_minimo IS 'Quantidade mínima para alerta de estoque';

CREATE INDEX idx_flores_nome ON flores(nome);
CREATE INDEX idx_flores_especie ON flores(especie);
CREATE INDEX idx_flores_cor ON flores(cor);
CREATE INDEX idx_flores_estoque ON flores(estoque);
CREATE INDEX idx_flores_ativo ON flores(ativo);
CREATE INDEX idx_flores_fornecedor ON flores(fornecedor_id);
CREATE INDEX idx_flores_ativo_estoque ON flores(ativo, estoque);

-- ============================================================================
-- TABELA: vendas
-- ============================================================================
CREATE TABLE vendas (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL REFERENCES clientes(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    funcionario_id INT NOT NULL REFERENCES funcionarios(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    data_venda TIMESTAMP NOT NULL DEFAULT NOW(),
    valor_total DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    desconto DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    valor_final DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    status status_venda NOT NULL DEFAULT 'pendente',
    forma_pagamento forma_pagamento,
    observacoes TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE vendas IS 'Tabela de vendas realizadas';

CREATE INDEX idx_vendas_cliente ON vendas(cliente_id);
CREATE INDEX idx_vendas_funcionario ON vendas(funcionario_id);
CREATE INDEX idx_vendas_data_venda ON vendas(data_venda);
CREATE INDEX idx_vendas_status ON vendas(status);
CREATE INDEX idx_vendas_valor_total ON vendas(valor_total);
CREATE INDEX idx_vendas_cliente_data ON vendas(cliente_id, data_venda);
CREATE INDEX idx_vendas_funcionario_data ON vendas(funcionario_id, data_venda);

-- ============================================================================
-- TABELA: itens_venda
-- ============================================================================
CREATE TABLE itens_venda (
    id SERIAL PRIMARY KEY,
    venda_id INT NOT NULL REFERENCES vendas(id) ON DELETE CASCADE ON UPDATE CASCADE,
    flor_id INT NOT NULL REFERENCES flores(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    quantidade INT NOT NULL DEFAULT 1,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    UNIQUE (venda_id, flor_id)
);

COMMENT ON TABLE itens_venda IS 'Tabela de itens (flores) de cada venda';
COMMENT ON CONSTRAINT itens_venda_venda_id_flor_id_key ON itens_venda IS 'Evita duplicação de mesma flor na mesma venda';

CREATE INDEX idx_itens_venda_venda ON itens_venda(venda_id);
CREATE INDEX idx_itens_venda_flor ON itens_venda(flor_id);

-- ============================================================================
-- TABELA: arranjos
-- ============================================================================
CREATE TABLE arranjos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    imagem_url VARCHAR(255),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE arranjos IS 'Tabela de arranjos florais compostos';

CREATE INDEX idx_arranjos_nome ON arranjos(nome);
CREATE INDEX idx_arranjos_ativo ON arranjos(ativo);

-- ============================================================================
-- TABELA: arranjos_flores
-- ============================================================================
CREATE TABLE arranjos_flores (
    id SERIAL PRIMARY KEY,
    arranjo_id INT NOT NULL REFERENCES arranjos(id) ON DELETE CASCADE ON UPDATE CASCADE,
    flor_id INT NOT NULL REFERENCES flores(id) ON DELETE CASCADE ON UPDATE CASCADE,
    quantidade INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    UNIQUE (arranjo_id, flor_id)
);

COMMENT ON TABLE arranjos_flores IS 'Tabela de relacionamento entre arranjos e flores (N:M)';

CREATE INDEX idx_arranjos_flores_arranjo ON arranjos_flores(arranjo_id);
CREATE INDEX idx_arranjos_flores_flor ON arranjos_flores(flor_id);

-- ============================================================================
-- TABELA: usuarios
-- ============================================================================
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    funcionario_id INT UNIQUE REFERENCES funcionarios(id) ON DELETE SET NULL ON UPDATE CASCADE,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    role role_usuario NOT NULL DEFAULT 'vendedor',
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    ultimo_login TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE usuarios IS 'Tabela de usuários do sistema (autenticação)';
COMMENT ON COLUMN usuarios.senha_hash IS 'Hash da senha (usar bcrypt ou similar)';

CREATE INDEX idx_usuarios_username ON usuarios(username);
CREATE INDEX idx_usuarios_email ON usuarios(email);
CREATE INDEX idx_usuarios_role ON usuarios(role);
CREATE INDEX idx_usuarios_ativo ON usuarios(ativo);

-- ============================================================================
-- TABELA: historico_estoque
-- ============================================================================
CREATE TABLE historico_estoque (
    id SERIAL PRIMARY KEY,
    flor_id INT NOT NULL REFERENCES flores(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    tipo_movimentacao tipo_movimentacao NOT NULL,
    quantidade INT NOT NULL,
    quantidade_anterior INT NOT NULL,
    quantidade_atual INT NOT NULL,
    motivo VARCHAR(255),
    usuario_id INT REFERENCES usuarios(id) ON DELETE SET NULL ON UPDATE CASCADE,
    referencia_venda_id INT REFERENCES vendas(id) ON DELETE SET NULL ON UPDATE CASCADE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE historico_estoque IS 'Histórico de movimentações de estoque (auditoria)';

CREATE INDEX idx_historico_estoque_flor ON historico_estoque(flor_id);
CREATE INDEX idx_historico_estoque_tipo ON historico_estoque(tipo_movimentacao);
CREATE INDEX idx_historico_estoque_data ON historico_estoque(created_at);
CREATE INDEX idx_historico_estoque_usuario ON historico_estoque(usuario_id);

-- ============================================================================
-- FUNÇÃO: Atualizar updated_at automaticamente
-- ============================================================================
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Aplicar trigger de updated_at em todas as tabelas relevantes
CREATE TRIGGER update_clientes_updated_at BEFORE UPDATE ON clientes
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_funcionarios_updated_at BEFORE UPDATE ON funcionarios
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_fornecedores_updated_at BEFORE UPDATE ON fornecedores
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_flores_updated_at BEFORE UPDATE ON flores
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_vendas_updated_at BEFORE UPDATE ON vendas
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_arranjos_updated_at BEFORE UPDATE ON arranjos
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_usuarios_updated_at BEFORE UPDATE ON usuarios
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- ============================================================================
-- TRIGGERS PARA AUTOMAÇÃO
-- ============================================================================

-- Trigger: Atualizar estoque ao adicionar item de venda
CREATE OR REPLACE FUNCTION atualizar_estoque_inserir_item()
RETURNS TRIGGER AS $$
DECLARE
    estoque_anterior INT;
    estoque_atual INT;
BEGIN
    -- Atualizar estoque
    UPDATE flores 
    SET estoque = estoque - NEW.quantidade,
        updated_at = NOW()
    WHERE id = NEW.flor_id
    RETURNING estoque INTO estoque_atual;
    
    -- Obter estoque anterior
    SELECT estoque + NEW.quantidade INTO estoque_anterior FROM flores WHERE id = NEW.flor_id;
    
    -- Registrar no histórico
    INSERT INTO historico_estoque (
        flor_id, 
        tipo_movimentacao, 
        quantidade, 
        quantidade_anterior, 
        quantidade_atual,
        referencia_venda_id
    ) VALUES (
        NEW.flor_id,
        'saida',
        -NEW.quantidade,
        estoque_anterior,
        estoque_atual,
        NEW.venda_id
    );
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_atualizar_estoque_inserir_item
AFTER INSERT ON itens_venda
FOR EACH ROW EXECUTE FUNCTION atualizar_estoque_inserir_item();

-- Trigger: Atualizar valor total da venda
CREATE OR REPLACE FUNCTION atualizar_valor_venda_inserir()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE vendas
    SET valor_total = (
        SELECT COALESCE(SUM(subtotal), 0)
        FROM itens_venda 
        WHERE venda_id = NEW.venda_id
    ),
    valor_final = valor_total - desconto,
    updated_at = NOW()
    WHERE id = NEW.venda_id;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_atualizar_valor_venda_inserir
AFTER INSERT ON itens_venda
FOR EACH ROW EXECUTE FUNCTION atualizar_valor_venda_inserir();

-- Trigger: Atualizar gasto total do cliente
CREATE OR REPLACE FUNCTION atualizar_gasto_cliente()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.status = 'finalizada' AND (OLD.status IS NULL OR OLD.status != 'finalizada') THEN
        UPDATE clientes
        SET gasto_total = gasto_total + NEW.valor_final,
            updated_at = NOW()
        WHERE id = NEW.cliente_id;
    END IF;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_atualizar_gasto_cliente
AFTER UPDATE ON vendas
FOR EACH ROW EXECUTE FUNCTION atualizar_gasto_cliente();

-- ============================================================================
-- VIEWS ÚTEIS
-- ============================================================================

CREATE OR REPLACE VIEW vw_vendas_por_cliente AS
SELECT 
    c.id AS cliente_id,
    c.nome AS cliente_nome,
    c.cpf,
    c.vip,
    COUNT(v.id) AS total_vendas,
    COALESCE(SUM(v.valor_final), 0) AS total_gasto,
    MAX(v.data_venda) AS ultima_compra
FROM clientes c
LEFT JOIN vendas v ON c.id = v.cliente_id AND v.status = 'finalizada'
GROUP BY c.id, c.nome, c.cpf, c.vip;

CREATE OR REPLACE VIEW vw_vendas_por_funcionario AS
SELECT 
    f.id AS funcionario_id,
    f.nome AS funcionario_nome,
    f.cargo,
    COUNT(v.id) AS total_vendas,
    COALESCE(SUM(v.valor_final), 0) AS total_vendido,
    COALESCE(SUM(v.valor_final * f.comissao_percentual / 100), 0) AS total_comissao
FROM funcionarios f
LEFT JOIN vendas v ON f.id = v.funcionario_id AND v.status = 'finalizada'
GROUP BY f.id, f.nome, f.cargo, f.comissao_percentual;

CREATE OR REPLACE VIEW vw_estoque_baixo AS
SELECT 
    id,
    nome,
    especie,
    estoque,
    estoque_minimo,
    (estoque_minimo - estoque) AS quantidade_faltante
FROM flores
WHERE estoque <= estoque_minimo AND ativo = TRUE
ORDER BY quantidade_faltante DESC;

CREATE OR REPLACE VIEW vw_vendas_mes_atual AS
SELECT 
    v.id,
    v.data_venda,
    c.nome AS cliente_nome,
    f.nome AS funcionario_nome,
    v.valor_final,
    v.status
FROM vendas v
INNER JOIN clientes c ON v.cliente_id = c.id
INNER JOIN funcionarios f ON v.funcionario_id = f.id
WHERE DATE_TRUNC('month', v.data_venda) = DATE_TRUNC('month', CURRENT_DATE)
ORDER BY v.data_venda DESC;

-- ============================================================================
-- DADOS INICIAIS
-- ============================================================================

-- Usuário administrador padrão
-- Senha: 'admin123' (hash bcrypt - DEVE SER ALTERADO!)
INSERT INTO usuarios (username, email, senha_hash, role, ativo) VALUES
('admin', 'admin@floricultura.com', '$2a$10$rK8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X', 'admin', TRUE);

-- ============================================================================
-- FIM DO SCRIPT
-- ============================================================================

