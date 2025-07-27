INSERT INTO tipos_cozinhas (id, descricao_tipo_cozinha) VALUES(1, 'Italiana Teste');
INSERT INTO tipos_usuarios (id, descricao_tipo_usuario) VALUES(1, 'DONO DE RESTAURANTE');

INSERT INTO usuarios (id, tipo_usuario_id, login, email, nome, senha_hash, data_alteracao) VALUES (1, 1, 'teste_login1', 'teste1@teste.com.br', 'nome teste1', 'senhaTeste123', '2025-01-01 00:00:00');--

INSERT INTO usuarios (id, tipo_usuario_id, login, email, nome, senha_hash, data_alteracao) VALUES (2, 1, 'teste_login2', 'teste2@teste.com.br', 'nome teste2', 'senhaTeste123', '2025-01-01 00:00:00');--
INSERT INTO restaurantes (id, tipos_cozinha_id, usuario_id, nome) VALUES(2, 1, 2, 'Restaurante Teste2');

INSERT INTO usuarios (id, tipo_usuario_id, login, email, nome, senha_hash, data_alteracao) VALUES (3, 1, 'teste_login3', 'teste3@teste.com.br', 'nome teste3', 'senhaTeste123', '2025-01-01 00:00:00');--
INSERT INTO restaurantes (id, tipos_cozinha_id, usuario_id, nome) VALUES(3, 1, 3, 'Restaurante Teste3');

