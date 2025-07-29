DELETE FROM tipos_usuarios;
INSERT INTO tipos_usuarios (id, descricao_tipo_usuario) VALUES (1, 'DONO_RESTAURANTE');

INSERT INTO usuarios (id, tipo_usuario_id, login, email, nome, senha_hash, data_alteracao) VALUES (2, 1, 'teste_login', 'teste@teste.com.br', 'nome teste', 'senhaTeste123', '2025-01-01 00:00:00');
INSERT INTO usuarios (id, tipo_usuario_id, login, email, nome, senha_hash, data_alteracao) VALUES (3, 1, 'teste_login44', 'teste@teste44.com.br', 'nome teste44', 'senhaTeste1234', '2025-01-01 00:00:00');
INSERT INTO usuarios (id, tipo_usuario_id, login, email, nome, senha_hash, data_alteracao) VALUES (4, 1, 'teste_login45', 'teste@teste45.com.br', 'nome teste45', 'senhaTeste1234', '2025-01-01 00:00:00');
INSERT INTO usuarios (id, tipo_usuario_id, login, email, nome, senha_hash, data_alteracao) VALUES (5, 1, 'teste_login5', 'teste@teste5.com.br', 'nome teste5', 'senhaTeste1234', '2025-01-01 00:00:00');
INSERT INTO usuarios (id, tipo_usuario_id, login, email, nome, senha_hash, data_alteracao) VALUES (6, 1, 'teste_login6', 'teste@teste6.com.br', 'nome teste6', 'senhaTeste1234', '2025-01-01 00:00:00');

