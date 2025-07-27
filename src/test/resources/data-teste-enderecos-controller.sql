INSERT INTO tipos_usuarios (id, descricao_tipo_usuario) VALUES(1, 'DONO DE RESTAURANTE');

INSERT INTO usuarios (id, tipo_usuario_id, login, email, nome, senha_hash, data_alteracao) VALUES (1, 1, 'teste_login1', 'teste1@teste.com.br', 'nome teste1', 'senhaTeste123', '2025-01-01 00:00:00');--

INSERT INTO usuarios (id, tipo_usuario_id, login, email, nome, senha_hash, data_alteracao) VALUES (2, 1, 'teste_login2', 'teste2@teste.com.br', 'nome teste2', 'senhaTeste123', '2025-01-01 00:00:00');--
INSERT INTO enderecos (id, usuario_id, logradouro, numero, cidade, estado, cep, complemento, bairro)
VALUES (2, 2, 'Rua das Palmeiras', '123', 'São Paulo', 'SP', '01000-000', 'Apartamento 101', 'Jardins');

