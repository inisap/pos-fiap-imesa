INSERT INTO tipos_cozinhas (id, descricao_tipo_cozinha) VALUES(1, 'Italiana Teste');
INSERT INTO tipos_usuarios (id, descricao_tipo_usuario) VALUES(1, 'DONO DE RESTAURANTE');
INSERT INTO usuarios (id, tipo_usuario_id, login, email, nome, senha_hash, data_alteracao) VALUES (1, 1, 'teste_login', 'teste@teste.com.br', 'nome teste', 'senhaTeste123', '2025-01-01 00:00:00');--
INSERT INTO restaurantes (id, tipos_cozinha_id, usuario_id, nome) VALUES(1, 1, 1, 'Restaurante Teste');
INSERT INTO cardapios (id, restaurante_id, descricao_cardapio) VALUES(1, 1, 'Cardapio Teste');