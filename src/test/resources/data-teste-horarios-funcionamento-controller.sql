INSERT INTO tipos_cozinhas (id, descricao_tipo_cozinha) VALUES(1, 'Italiana Teste');
INSERT INTO tipos_usuarios (id, descricao_tipo_usuario) VALUES(1, 'DONO DE RESTAURANTE');
INSERT INTO usuarios (id, tipo_usuario_id, login, email, nome, senha_hash, data_alteracao) VALUES (1, 1, 'teste_login', 'teste@teste.com.br', 'nome teste', 'senhaTeste123', '2025-01-01 00:00:00');--
INSERT INTO restaurantes (id, tipos_cozinha_id, usuario_id, nome) VALUES(1, 1, 1, 'Restaurante Teste');

INSERT INTO usuarios (id, tipo_usuario_id, login, email, nome, senha_hash, data_alteracao) VALUES (2, 1, 'teste_login2', 'teste2@teste.com.br', 'nome teste2', 'senhaTeste123', '2025-01-01 00:00:00');--
INSERT INTO restaurantes (id, tipos_cozinha_id, usuario_id, nome) VALUES(2, 1, 2, 'Restaurante Teste2');
INSERT INTO cardapios (id, restaurante_id, descricao_cardapio) VALUES(2, 2, 'Cardapio Teste2');
INSERT INTO horarios_funcionamentos (dia_semana, hora_abertura, hora_fechamento, restaurante_id, flag_aberto
) VALUES
(1, '08:00:00', '18:00:00', 2, true),
(2, '08:00:00', '18:00:00', 2, true),
(3, '08:00:00', '18:00:00', 2, true),
(4, '08:00:00', '18:00:00', 2, true),
(5, '08:00:00', '18:00:00', 2, true),
(6, '10:00:00', '16:00:00', 2, true),
(7, '00:00:00', '00:00:00', 2, false);
