-- ============================================================================
-- DADOS DE EXEMPLO PARA TESTES - SISTEMA DE FLORICULTURA
-- ============================================================================
-- Este script insere dados de exemplo para testar o sistema
-- Execute APÓS criar o schema do banco de dados
-- ============================================================================

USE floricultura_db; -- Para MySQL
-- \c floricultura_db; -- Para PostgreSQL

-- ============================================================================
-- FORNECEDORES
-- ============================================================================
INSERT INTO fornecedores (nome, cnpj, telefone, email, endereco, contato_responsavel, data_cadastro, ativo) VALUES
('Flores do Campo Ltda', '12.345.678/0001-90', '(11) 3456-7890', 'contato@floresdocampo.com.br', 'Rua das Flores, 123 - São Paulo/SP', 'João Silva', '2024-01-15', TRUE),
('Jardim Encantado ME', '98.765.432/0001-10', '(21) 9876-5432', 'vendas@jardimencantado.com.br', 'Av. das Rosas, 456 - Rio de Janeiro/RJ', 'Maria Santos', '2024-02-01', TRUE),
('Natureza Viva Importadora', '11.222.333/0001-44', '(31) 3333-4444', 'compras@naturezaviva.com.br', 'Rua Verde, 789 - Belo Horizonte/MG', 'Pedro Costa', '2024-01-20', TRUE),
('Orquídeas Premium S.A.', '55.666.777/0001-88', '(41) 5555-6666', 'contato@orquideaspremium.com.br', 'Alameda das Orquídeas, 321 - Curitiba/PR', 'Ana Paula', '2024-03-10', TRUE);

-- ============================================================================
-- FUNCIONÁRIOS
-- ============================================================================
INSERT INTO funcionarios (nome, cpf, telefone, email, endereco, cargo, salario, data_admissao, data_cadastro, comissao_percentual, ativo) VALUES
('Carlos Mendes', '123.456.789-00', '(11) 99999-1111', 'carlos@floricultura.com', 'Rua A, 100', 'Gerente', 5000.00, '2023-01-15', '2023-01-15', 0.00, TRUE),
('Juliana Alves', '234.567.890-11', '(11) 99999-2222', 'juliana@floricultura.com', 'Rua B, 200', 'Vendedora', 2500.00, '2023-03-20', '2023-03-20', 5.00, TRUE),
('Roberto Silva', '345.678.901-22', '(11) 99999-3333', 'roberto@floricultura.com', 'Rua C, 300', 'Vendedor', 2500.00, '2023-05-10', '2023-05-10', 5.00, TRUE),
('Fernanda Lima', '456.789.012-33', '(11) 99999-4444', 'fernanda@floricultura.com', 'Rua D, 400', 'Estoquista', 2000.00, '2023-07-01', '2023-07-01', 0.00, TRUE);

-- ============================================================================
-- FLORES
-- ============================================================================
INSERT INTO flores (nome, nome_cientifico, especie, cor, preco, estoque, estoque_minimo, fornecedor_id, descricao, ativo) VALUES
('Rosa Vermelha', 'Rosa × hybrida', 'Híbrida', 'Vermelho', 15.90, 150, 20, 1, 'Rosa vermelha clássica, símbolo de amor e paixão', TRUE),
('Rosa Branca', 'Rosa × hybrida', 'Híbrida', 'Branco', 14.90, 120, 20, 1, 'Rosa branca elegante, perfeita para casamentos', TRUE),
('Rosa Amarela', 'Rosa × hybrida', 'Híbrida', 'Amarelo', 16.90, 100, 20, 1, 'Rosa amarela vibrante, expressa amizade', TRUE),
('Lírio Branco', 'Lilium candidum', 'Lírio', 'Branco', 18.50, 80, 15, 2, 'Lírio branco puro, elegante e sofisticado', TRUE),
('Lírio Amarelo', 'Lilium longiflorum', 'Lírio', 'Amarelo', 19.90, 70, 15, 2, 'Lírio amarelo radiante', TRUE),
('Tulipa Vermelha', 'Tulipa gesneriana', 'Tulipa', 'Vermelho', 12.90, 200, 30, 2, 'Tulipa vermelha vibrante', TRUE),
('Tulipa Amarela', 'Tulipa gesneriana', 'Tulipa', 'Amarelo', 12.90, 180, 30, 2, 'Tulipa amarela ensolarada', TRUE),
('Girassol', 'Helianthus annuus', 'Girassol', 'Amarelo', 8.90, 250, 40, 3, 'Girassol grande e radiante', TRUE),
('Orquídea Phalaenopsis', 'Phalaenopsis amabilis', 'Orquídea', 'Rosa', 45.00, 30, 10, 4, 'Orquídea delicada e exótica', TRUE),
('Orquídea Branca', 'Phalaenopsis amabilis', 'Orquídea', 'Branco', 48.00, 25, 10, 4, 'Orquídea branca elegante', TRUE),
('Margarida', 'Bellis perennis', 'Margarida', 'Branco', 6.90, 300, 50, 3, 'Margarida simples e encantadora', TRUE),
('Crisântemo', 'Chrysanthemum morifolium', 'Crisântemo', 'Amarelo', 9.90, 180, 30, 3, 'Crisântemo tradicional', TRUE);

-- ============================================================================
-- CLIENTES
-- ============================================================================
INSERT INTO clientes (nome, cpf, telefone, email, endereco, data_cadastro, vip, gasto_total, ativo) VALUES
('Ana Paula Santos', '111.222.333-44', '(11) 98888-1111', 'ana@email.com', 'Rua das Flores, 100 - São Paulo/SP', '2024-01-10', TRUE, 850.50, TRUE),
('Bruno Oliveira', '222.333.444-55', '(11) 98888-2222', 'bruno@email.com', 'Av. Paulista, 500 - São Paulo/SP', '2024-02-15', FALSE, 320.00, TRUE),
('Carla Mendes', '333.444.555-66', '(11) 98888-3333', 'carla@email.com', 'Rua Augusta, 200 - São Paulo/SP', '2024-03-01', TRUE, 1200.00, TRUE),
('Daniel Costa', '444.555.666-77', '(11) 98888-4444', 'daniel@email.com', 'Rua Consolação, 300 - São Paulo/SP', '2024-03-20', FALSE, 150.00, TRUE),
('Eduarda Silva', '555.666.777-88', '(11) 98888-5555', 'eduarda@email.com', 'Alameda Santos, 400 - São Paulo/SP', '2024-04-05', FALSE, 0.00, TRUE);

-- ============================================================================
-- ARRANJOS
-- ============================================================================
INSERT INTO arranjos (nome, descricao, preco, ativo) VALUES
('Buquê de Rosas Vermelhas', 'Buquê romântico com 12 rosas vermelhas', 189.90, TRUE),
('Arranjo Elegante', 'Arranjo com lírios brancos e rosas brancas', 249.90, TRUE),
('Buquê Colorido', 'Buquê com tulipas e margaridas coloridas', 159.90, TRUE),
('Arranjo Premium', 'Arranjo exclusivo com orquídeas e rosas', 399.90, TRUE);

-- ============================================================================
-- ARRANJOS_FLORES (Relacionamento N:M)
-- ============================================================================
INSERT INTO arranjos_flores (arranjo_id, flor_id, quantidade) VALUES
-- Buquê de Rosas Vermelhas (12 rosas)
(1, 1, 12),
-- Arranjo Elegante (5 lírios + 5 rosas brancas)
(2, 4, 5),
(2, 2, 5),
-- Buquê Colorido (6 tulipas + 8 margaridas)
(3, 6, 6),
(3, 11, 8),
-- Arranjo Premium (2 orquídeas + 6 rosas)
(4, 9, 2),
(4, 1, 6);

-- ============================================================================
-- VENDAS DE EXEMPLO
-- ============================================================================
-- Venda 1: Ana Paula (VIP) - Finalizada
INSERT INTO vendas (cliente_id, funcionario_id, data_venda, valor_total, desconto, valor_final, status, forma_pagamento, observacoes) VALUES
(1, 2, '2024-05-15 10:30:00', 189.90, 18.99, 170.91, 'finalizada', 'cartao_credito', 'Entrega em casa');

-- Itens da Venda 1
INSERT INTO itens_venda (venda_id, flor_id, quantidade, preco_unitario, subtotal) VALUES
(1, 1, 12, 15.90, 190.80);

-- Venda 2: Bruno - Finalizada
INSERT INTO vendas (cliente_id, funcionario_id, data_venda, valor_total, desconto, valor_final, status, forma_pagamento) VALUES
(2, 3, '2024-05-20 14:15:00', 74.50, 0.00, 74.50, 'finalizada', 'pix');

-- Itens da Venda 2
INSERT INTO itens_venda (venda_id, flor_id, quantidade, preco_unitario, subtotal) VALUES
(2, 4, 2, 18.50, 37.00),
(2, 8, 3, 8.90, 26.70),
(2, 11, 5, 6.90, 34.50);

-- Venda 3: Carla (VIP) - Finalizada
INSERT INTO vendas (cliente_id, funcionario_id, data_venda, valor_total, desconto, valor_final, status, forma_pagamento) VALUES
(3, 2, '2024-06-01 09:00:00', 399.90, 39.99, 359.91, 'finalizada', 'cartao_credito');

-- Itens da Venda 3
INSERT INTO itens_venda (venda_id, flor_id, quantidade, preco_unitario, subtotal) VALUES
(3, 9, 2, 45.00, 90.00),
(3, 1, 6, 15.90, 95.40),
(3, 4, 4, 18.50, 74.00),
(3, 2, 8, 14.90, 119.20);

-- Venda 4: Daniel - Pendente
INSERT INTO vendas (cliente_id, funcionario_id, data_venda, valor_total, desconto, valor_final, status, forma_pagamento) VALUES
(4, 3, '2024-06-10 16:45:00', 75.50, 0.00, 75.50, 'pendente', 'dinheiro');

-- Itens da Venda 4
INSERT INTO itens_venda (venda_id, flor_id, quantidade, preco_unitario, subtotal) VALUES
(4, 6, 3, 12.90, 38.70),
(4, 7, 2, 12.90, 25.80),
(4, 12, 1, 9.90, 9.90);

-- ============================================================================
-- USUÁRIOS (associados aos funcionários)
-- ============================================================================
-- Nota: As senhas são hashes de exemplo. Em produção, use bcrypt com salt adequado
INSERT INTO usuarios (funcionario_id, username, email, senha_hash, role, ativo) VALUES
(1, 'carlos.mendes', 'carlos@floricultura.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'gerente', TRUE),
(2, 'juliana.alves', 'juliana@floricultura.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'vendedor', TRUE),
(3, 'roberto.silva', 'roberto@floricultura.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'vendedor', TRUE),
(4, 'fernanda.lima', 'fernanda@floricultura.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'estoque', TRUE);

-- ============================================================================
-- VERIFICAÇÕES (Consultas úteis para validar os dados)
-- ============================================================================

-- Ver total de registros por tabela
-- SELECT 'Fornecedores' AS tabela, COUNT(*) AS total FROM fornecedores
-- UNION ALL SELECT 'Funcionários', COUNT(*) FROM funcionarios
-- UNION ALL SELECT 'Flores', COUNT(*) FROM flores
-- UNION ALL SELECT 'Clientes', COUNT(*) FROM clientes
-- UNION ALL SELECT 'Vendas', COUNT(*) FROM vendas
-- UNION ALL SELECT 'Itens Venda', COUNT(*) FROM itens_venda
-- UNION ALL SELECT 'Arranjos', COUNT(*) FROM arranjos
-- UNION ALL SELECT 'Usuários', COUNT(*) FROM usuarios;

-- Ver estoque atualizado após vendas
-- SELECT f.nome, f.estoque, f.estoque_minimo, 
--        CASE WHEN f.estoque <= f.estoque_minimo THEN 'ATENÇÃO' ELSE 'OK' END AS status_estoque
-- FROM flores f
-- ORDER BY f.estoque ASC;

-- Ver vendas por cliente
-- SELECT c.nome, COUNT(v.id) AS total_vendas, SUM(v.valor_final) AS total_gasto
-- FROM clientes c
-- LEFT JOIN vendas v ON c.id = v.cliente_id AND v.status = 'finalizada'
-- GROUP BY c.id, c.nome
-- ORDER BY total_gasto DESC;

-- ============================================================================
-- FIM DO SCRIPT DE DADOS DE EXEMPLO
-- ============================================================================

