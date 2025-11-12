
-- Criar banco de dados
CREATE DATABASE IF NOT EXISTS floricultura_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE floricultura_db;

-- ============================================================================
-- TABELA: clientes
-- Descrição: Armazena informações dos clientes da floricultura
-- Relacionamentos: 1:N com vendas (um cliente pode ter várias vendas)
-- ============================================================================
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único do cliente',
    nome VARCHAR(150) NOT NULL COMMENT 'Nome completo do cliente',
    cpf VARCHAR(14) NOT NULL UNIQUE COMMENT 'CPF do cliente (formato: 000.000.000-00)',
    telefone VARCHAR(20) COMMENT 'Telefone de contato',
    email VARCHAR(100) COMMENT 'E-mail do cliente',
    endereco VARCHAR(255) COMMENT 'Endereço completo',
    data_cadastro DATE NOT NULL DEFAULT (CURRENT_DATE) COMMENT 'Data de cadastro do cliente',
    vip BOOLEAN NOT NULL DEFAULT FALSE COMMENT 'Indica se o cliente é VIP (recebe descontos)',
    gasto_total DECIMAL(10, 2) NOT NULL DEFAULT 0.00 COMMENT 'Total acumulado de gastos do cliente',
    ativo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica se o cliente está ativo no sistema',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp de criação do registro',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp da última atualização',
    
    INDEX idx_cpf (cpf),
    INDEX idx_nome (nome),
    INDEX idx_vip (vip),
    INDEX idx_ativo (ativo),
    INDEX idx_data_cadastro (data_cadastro)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabela de clientes da floricultura';

-- ============================================================================
-- TABELA: funcionarios
-- Descrição: Armazena informações dos funcionários da floricultura
-- Relacionamentos: 1:N com vendas (um funcionário pode realizar várias vendas)
-- ============================================================================
CREATE TABLE funcionarios (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único do funcionário',
    nome VARCHAR(150) NOT NULL COMMENT 'Nome completo do funcionário',
    cpf VARCHAR(14) NOT NULL UNIQUE COMMENT 'CPF do funcionário',
    telefone VARCHAR(20) COMMENT 'Telefone de contato',
    email VARCHAR(100) COMMENT 'E-mail do funcionário',
    endereco VARCHAR(255) COMMENT 'Endereço completo',
    cargo VARCHAR(50) NOT NULL COMMENT 'Cargo do funcionário (ex: Vendedor, Gerente, Entregador)',
    salario DECIMAL(10, 2) COMMENT 'Salário do funcionário',
    data_admissao DATE NOT NULL COMMENT 'Data de admissão do funcionário',
    data_cadastro DATE NOT NULL DEFAULT (CURRENT_DATE) COMMENT 'Data de cadastro no sistema',
    comissao_percentual DECIMAL(5, 2) DEFAULT 0.00 COMMENT 'Percentual de comissão sobre vendas',
    ativo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica se o funcionário está ativo',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp de criação do registro',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp da última atualização',
    
    INDEX idx_cpf (cpf),
    INDEX idx_nome (nome),
    INDEX idx_cargo (cargo),
    INDEX idx_ativo (ativo),
    INDEX idx_data_admissao (data_admissao)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabela de funcionários da floricultura';

-- ============================================================================
-- TABELA: fornecedores
-- Descrição: Armazena informações dos fornecedores de flores
-- Relacionamentos: 1:N com flores (um fornecedor pode fornecer várias flores)
-- ============================================================================
CREATE TABLE fornecedores (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único do fornecedor',
    nome VARCHAR(150) NOT NULL COMMENT 'Razão social ou nome do fornecedor',
    cnpj VARCHAR(18) NOT NULL UNIQUE COMMENT 'CNPJ do fornecedor (formato: 00.000.000/0000-00)',
    telefone VARCHAR(20) COMMENT 'Telefone de contato',
    email VARCHAR(100) COMMENT 'E-mail do fornecedor',
    endereco VARCHAR(255) COMMENT 'Endereço completo',
    contato_responsavel VARCHAR(100) COMMENT 'Nome do responsável pelo contato',
    data_cadastro DATE NOT NULL DEFAULT (CURRENT_DATE) COMMENT 'Data de cadastro do fornecedor',
    ativo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica se o fornecedor está ativo',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp de criação do registro',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp da última atualização',
    
    INDEX idx_cnpj (cnpj),
    INDEX idx_nome (nome),
    INDEX idx_ativo (ativo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabela de fornecedores de flores';

-- ============================================================================
-- TABELA: flores
-- Descrição: Armazena informações das flores disponíveis na floricultura
-- Relacionamentos: N:1 com fornecedores, 1:N com itens_venda
-- ============================================================================
CREATE TABLE flores (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único da flor',
    nome VARCHAR(100) NOT NULL COMMENT 'Nome popular da flor',
    nome_cientifico VARCHAR(150) COMMENT 'Nome científico da flor',
    especie VARCHAR(100) COMMENT 'Espécie da flor',
    cor VARCHAR(50) COMMENT 'Cor predominante da flor',
    preco DECIMAL(10, 2) NOT NULL COMMENT 'Preço unitário da flor',
    estoque INT NOT NULL DEFAULT 0 COMMENT 'Quantidade disponível em estoque',
    estoque_minimo INT NOT NULL DEFAULT 5 COMMENT 'Quantidade mínima para alerta de estoque',
    fornecedor_id INT COMMENT 'ID do fornecedor principal desta flor',
    descricao TEXT COMMENT 'Descrição detalhada da flor',
    imagem_url VARCHAR(255) COMMENT 'URL da imagem da flor',
    ativo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica se a flor está disponível para venda',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp de criação do registro',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp da última atualização',
    
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_nome (nome),
    INDEX idx_especie (especie),
    INDEX idx_cor (cor),
    INDEX idx_estoque (estoque),
    INDEX idx_ativo (ativo),
    INDEX idx_fornecedor (fornecedor_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabela de flores disponíveis na floricultura';

-- ============================================================================
-- TABELA: vendas
-- Descrição: Armazena informações das vendas realizadas
-- Relacionamentos: N:1 com clientes, N:1 com funcionarios, 1:N com itens_venda
-- ============================================================================
CREATE TABLE vendas (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único da venda',
    cliente_id INT NOT NULL COMMENT 'ID do cliente que realizou a compra',
    funcionario_id INT NOT NULL COMMENT 'ID do funcionário que realizou a venda',
    data_venda DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Data e hora da venda',
    valor_total DECIMAL(10, 2) NOT NULL DEFAULT 0.00 COMMENT 'Valor total da venda',
    desconto DECIMAL(10, 2) NOT NULL DEFAULT 0.00 COMMENT 'Valor do desconto aplicado (se cliente VIP)',
    valor_final DECIMAL(10, 2) NOT NULL DEFAULT 0.00 COMMENT 'Valor final após desconto',
    status ENUM('pendente', 'finalizada', 'cancelada') NOT NULL DEFAULT 'pendente' COMMENT 'Status da venda',
    forma_pagamento ENUM('dinheiro', 'cartao_debito', 'cartao_credito', 'pix', 'boleto') COMMENT 'Forma de pagamento',
    observacoes TEXT COMMENT 'Observações adicionais sobre a venda',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp de criação do registro',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp da última atualização',
    
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    INDEX idx_cliente (cliente_id),
    INDEX idx_funcionario (funcionario_id),
    INDEX idx_data_venda (data_venda),
    INDEX idx_status (status),
    INDEX idx_valor_total (valor_total)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabela de vendas realizadas';

-- ============================================================================
-- TABELA: itens_venda
-- Descrição: Armazena os itens (flores) de cada venda
-- Relacionamentos: N:1 com vendas, N:1 com flores
-- ============================================================================
CREATE TABLE itens_venda (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único do item',
    venda_id INT NOT NULL COMMENT 'ID da venda à qual o item pertence',
    flor_id INT NOT NULL COMMENT 'ID da flor vendida',
    quantidade INT NOT NULL DEFAULT 1 COMMENT 'Quantidade de flores vendidas',
    preco_unitario DECIMAL(10, 2) NOT NULL COMMENT 'Preço unitário no momento da venda',
    subtotal DECIMAL(10, 2) NOT NULL COMMENT 'Subtotal do item (quantidade * preco_unitario)',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp de criação do registro',
    
    FOREIGN KEY (venda_id) REFERENCES vendas(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (flor_id) REFERENCES flores(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    INDEX idx_venda (venda_id),
    INDEX idx_flor (flor_id),
    UNIQUE KEY uk_venda_flor (venda_id, flor_id) COMMENT 'Evita duplicação de mesma flor na mesma venda'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabela de itens (flores) de cada venda';

-- ============================================================================
-- TABELA: arranjos (opcional - para produtos compostos)
-- Descrição: Armazena informações sobre arranjos florais compostos
-- ============================================================================
CREATE TABLE arranjos (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único do arranjo',
    nome VARCHAR(100) NOT NULL COMMENT 'Nome do arranjo',
    descricao TEXT COMMENT 'Descrição do arranjo',
    preco DECIMAL(10, 2) NOT NULL COMMENT 'Preço do arranjo',
    imagem_url VARCHAR(255) COMMENT 'URL da imagem do arranjo',
    ativo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica se o arranjo está disponível',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp de criação do registro',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp da última atualização',
    
    INDEX idx_nome (nome),
    INDEX idx_ativo (ativo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabela de arranjos florais compostos';

-- ============================================================================
-- TABELA: arranjos_flores (tabela de relacionamento N:M)
-- Descrição: Relaciona arranjos com as flores que os compõem
-- ============================================================================
CREATE TABLE arranjos_flores (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único do relacionamento',
    arranjo_id INT NOT NULL COMMENT 'ID do arranjo',
    flor_id INT NOT NULL COMMENT 'ID da flor que compõe o arranjo',
    quantidade INT NOT NULL DEFAULT 1 COMMENT 'Quantidade desta flor no arranjo',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp de criação do registro',
    
    FOREIGN KEY (arranjo_id) REFERENCES arranjos(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (flor_id) REFERENCES flores(id) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE KEY uk_arranjo_flor (arranjo_id, flor_id) COMMENT 'Evita duplicação de mesma flor no mesmo arranjo',
    INDEX idx_arranjo (arranjo_id),
    INDEX idx_flor (flor_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabela de relacionamento entre arranjos e flores (N:M)';

-- ============================================================================
-- TABELA: usuarios (para autenticação e controle de acesso)
-- Descrição: Armazena usuários do sistema (funcionários com acesso ao sistema)
-- ============================================================================
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único do usuário',
    funcionario_id INT UNIQUE COMMENT 'ID do funcionário associado (opcional)',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT 'Nome de usuário para login',
    email VARCHAR(100) NOT NULL UNIQUE COMMENT 'E-mail do usuário',
    senha_hash VARCHAR(255) NOT NULL COMMENT 'Hash da senha (usar bcrypt ou similar)',
    role ENUM('admin', 'gerente', 'vendedor', 'estoque') NOT NULL DEFAULT 'vendedor' COMMENT 'Papel/permissão do usuário',
    ativo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica se o usuário está ativo',
    ultimo_login DATETIME COMMENT 'Data e hora do último login',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp de criação do registro',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp da última atualização',
    
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_username (username),
    INDEX idx_email (email),
    INDEX idx_role (role),
    INDEX idx_ativo (ativo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabela de usuários do sistema (autenticação)';

-- ============================================================================
-- TABELA: historico_estoque (auditoria de movimentações de estoque)
-- Descrição: Registra todas as movimentações de estoque (entrada/saída)
-- ============================================================================
CREATE TABLE historico_estoque (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único do registro',
    flor_id INT NOT NULL COMMENT 'ID da flor movimentada',
    tipo_movimentacao ENUM('entrada', 'saida', 'ajuste', 'perda') NOT NULL COMMENT 'Tipo de movimentação',
    quantidade INT NOT NULL COMMENT 'Quantidade movimentada (positiva ou negativa)',
    quantidade_anterior INT NOT NULL COMMENT 'Quantidade antes da movimentação',
    quantidade_atual INT NOT NULL COMMENT 'Quantidade após a movimentação',
    motivo VARCHAR(255) COMMENT 'Motivo da movimentação',
    usuario_id INT COMMENT 'ID do usuário que realizou a movimentação',
    referencia_venda_id INT COMMENT 'ID da venda relacionada (se aplicável)',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp da movimentação',
    
    FOREIGN KEY (flor_id) REFERENCES flores(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (referencia_venda_id) REFERENCES vendas(id) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX idx_flor (flor_id),
    INDEX idx_tipo (tipo_movimentacao),
    INDEX idx_data (created_at),
    INDEX idx_usuario (usuario_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Histórico de movimentações de estoque (auditoria)';

-- ============================================================================
-- VIEWS ÚTEIS PARA CONSULTAS
-- ============================================================================

-- View: Resumo de vendas por cliente
CREATE OR REPLACE VIEW vw_vendas_por_cliente AS
SELECT 
    c.id AS cliente_id,
    c.nome AS cliente_nome,
    c.cpf,
    c.vip,
    COUNT(v.id) AS total_vendas,
    SUM(v.valor_final) AS total_gasto,
    MAX(v.data_venda) AS ultima_compra
FROM clientes c
LEFT JOIN vendas v ON c.id = v.cliente_id AND v.status = 'finalizada'
GROUP BY c.id, c.nome, c.cpf, c.vip;

-- View: Resumo de vendas por funcionário
CREATE OR REPLACE VIEW vw_vendas_por_funcionario AS
SELECT 
    f.id AS funcionario_id,
    f.nome AS funcionario_nome,
    f.cargo,
    COUNT(v.id) AS total_vendas,
    SUM(v.valor_final) AS total_vendido,
    SUM(v.valor_final * f.comissao_percentual / 100) AS total_comissao
FROM funcionarios f
LEFT JOIN vendas v ON f.id = v.funcionario_id AND v.status = 'finalizada'
GROUP BY f.id, f.nome, f.cargo, f.comissao_percentual;

-- View: Flores com estoque baixo
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

-- View: Vendas do mês atual
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
WHERE YEAR(v.data_venda) = YEAR(CURRENT_DATE)
  AND MONTH(v.data_venda) = MONTH(CURRENT_DATE)
ORDER BY v.data_venda DESC;

-- ============================================================================
-- TRIGGERS PARA AUTOMAÇÃO
-- ============================================================================

-- Trigger: Atualizar estoque ao adicionar item de venda
DELIMITER //
CREATE TRIGGER trg_atualizar_estoque_inserir_item
AFTER INSERT ON itens_venda
FOR EACH ROW
BEGIN
    UPDATE flores 
    SET estoque = estoque - NEW.quantidade,
        updated_at = CURRENT_TIMESTAMP
    WHERE id = NEW.flor_id;
    
    -- Registrar no histórico
    INSERT INTO historico_estoque (
        flor_id, 
        tipo_movimentacao, 
        quantidade, 
        quantidade_anterior, 
        quantidade_atual,
        referencia_venda_id
    )
    SELECT 
        NEW.flor_id,
        'saida',
        -NEW.quantidade,
        estoque + NEW.quantidade,
        estoque,
        NEW.venda_id
    FROM flores
    WHERE id = NEW.flor_id;
END//
DELIMITER ;

-- Trigger: Atualizar valor total da venda ao inserir item
DELIMITER //
CREATE TRIGGER trg_atualizar_valor_venda_inserir
AFTER INSERT ON itens_venda
FOR EACH ROW
BEGIN
    UPDATE vendas
    SET valor_total = (
        SELECT SUM(subtotal) 
        FROM itens_venda 
        WHERE venda_id = NEW.venda_id
    ),
    valor_final = valor_total - desconto,
    updated_at = CURRENT_TIMESTAMP
    WHERE id = NEW.venda_id;
END//
DELIMITER ;

-- Trigger: Atualizar gasto total do cliente ao finalizar venda
DELIMITER //
CREATE TRIGGER trg_atualizar_gasto_cliente
AFTER UPDATE ON vendas
FOR EACH ROW
BEGIN
    IF NEW.status = 'finalizada' AND (OLD.status IS NULL OR OLD.status != 'finalizada') THEN
        UPDATE clientes
        SET gasto_total = gasto_total + NEW.valor_final,
            updated_at = CURRENT_TIMESTAMP
        WHERE id = NEW.cliente_id;
    END IF;
END//
DELIMITER ;

-- ============================================================================
-- DADOS INICIAIS (SEED DATA)
-- ============================================================================

-- Inserir usuário administrador padrão
-- Senha padrão: 'admin123' (hash bcrypt - DEVE SER ALTERADO EM PRODUÇÃO!)
INSERT INTO usuarios (username, email, senha_hash, role, ativo) VALUES
('admin', 'admin@floricultura.com', '$2a$10$rK8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X8X', 'admin', TRUE);

-- ============================================================================
-- ÍNDICES ADICIONAIS PARA OTIMIZAÇÃO
-- ============================================================================

-- Índices compostos para consultas frequentes
CREATE INDEX idx_vendas_cliente_data ON vendas(cliente_id, data_venda);
CREATE INDEX idx_vendas_funcionario_data ON vendas(funcionario_id, data_venda);
CREATE INDEX idx_flores_ativo_estoque ON flores(ativo, estoque);

-- ============================================================================
-- FIM DO SCRIPT
-- ============================================================================


