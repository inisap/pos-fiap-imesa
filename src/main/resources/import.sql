INSERT INTO usuarios (id, data_alteracao, login, email, nome, senha_hash, tipo_usuario) values (1, '2025-06-02 15:00:00.333', 'teste002', 'cenario2@hotmail.com', 'pessoa dois', 'pasini123', 'CLIENTE');
INSERT INTO enderecos (id, usuario_id, numero, cep, bairro, cidade, estado, complemento, logradouro) values (1, 1,'993','06364550', 'Jardim Ana Estela', 'Carapicuiba','São Paulo', 'casa 2', 'Rua Paraguacu Paulista');

INSERT INTO usuarios (id, data_alteracao, login, email, nome, senha_hash, tipo_usuario) values (2, '2025-06-02 15:00:00.333', 'testecenario3', 'testecenario3@hotmail.com', 'pessoa tres', 'pasini123', 'CLIENTE');
INSERT INTO enderecos (id, usuario_id, numero, cep, bairro, cidade, estado, complemento, logradouro) values (2, 2,'993','06364550', 'Jardim Ana Estela', 'Carapicuiba','São Paulo', 'casa 2', 'Rua Paraguacu Paulista');

INSERT INTO usuarios (id, data_alteracao, login, email, nome, senha_hash, tipo_usuario) values (3, '2025-06-02 15:00:00.333', 'cenario15', 'cenario15@hotmail.com', 'pessoa quinze', 'pessoa15123', 'CLIENTE');
INSERT INTO enderecos (id, usuario_id, numero, cep, bairro, cidade, estado, complemento, logradouro) values (3, 3,'993','06364550', 'Jardim Ana Estela', 'Carapicuiba','São Paulo', 'casa 2', 'Rua Paraguacu Paulista');

INSERT INTO usuarios (id, data_alteracao, login, email, nome, senha_hash, tipo_usuario) values (4, '2025-06-02 15:00:00.333', 'cenario18', 'cenario18@hotmail.com', 'pessoa dezoito', 'senha18', 'CLIENTE');
INSERT INTO enderecos (id, usuario_id, numero, cep, bairro, cidade, estado, complemento, logradouro) values (4, 4,'993','06364550', 'Jardim Ana Estela', 'Carapicuiba','São Paulo', 'casa 2', 'Rua Paraguacu Paulista');

INSERT INTO usuarios (id, data_alteracao, login, email, nome, senha_hash, tipo_usuario) values (5, '2025-06-02 15:00:00.333', 'cenario19', 'cenario19@hotmail.com', 'pessoa dezenove', 'senha19antiga', 'CLIENTE');
INSERT INTO enderecos (id, usuario_id, numero, cep, bairro, cidade, estado, complemento, logradouro) values (5, 5,'993','06364550', 'Jardim Ana Estela', 'Carapicuiba','São Paulo', 'casa 2', 'Rua Paraguacu Paulista');

INSERT INTO usuarios (id, data_alteracao, login, email, nome, senha_hash, tipo_usuario) values (6, '2025-06-02 15:00:00.333', 'cenario20', 'cenario20@hotmail.com', 'pessoa vinte', 'senha20', 'CLIENTE');
INSERT INTO enderecos (id, usuario_id, numero, cep, bairro, cidade, estado, complemento, logradouro) values (6, 6,'993','06364550', 'Jardim Ana Estela', 'Carapicuiba','São Paulo', 'casa 2', 'Rua Paraguacu Paulista');

INSERT INTO usuarios (id, data_alteracao, login, email, nome, senha_hash, tipo_usuario) values (7, '2025-06-02 15:00:00.333', 'cenario22', 'cenario22@hotmail.com', 'pessoa vinte e dois', 'senha22', 'CLIENTE');
INSERT INTO enderecos (id, usuario_id, numero, cep, bairro, cidade, estado, complemento, logradouro) values (7, 7,'993','06364550', 'Jardim Ana Estela', 'Carapicuiba','São Paulo', 'casa 2', 'Rua Paraguacu Paulista');

INSERT INTO usuarios (id, data_alteracao, login, email, nome, senha_hash, tipo_usuario) values (8, '2025-06-02 15:00:00.333', 'cenario24', 'cenario24@hotmail.com', 'pessoa vinte e quatro', 'senha24', 'CLIENTE');
INSERT INTO enderecos (id, usuario_id, numero, cep, bairro, cidade, estado, complemento, logradouro) values (8, 8,'993','06364550', 'Jardim Ana Estela', 'Carapicuiba','São Paulo', 'casa 2', 'Rua Paraguacu Paulista');

INSERT INTO usuarios (id, data_alteracao, login, email, nome, senha_hash, tipo_usuario) values (9, '2025-06-02 15:00:00.333', 'cenario26', 'cenario26@hotmail.com', 'pessoa vinte e seis', 'senha26', 'CLIENTE');
INSERT INTO enderecos (id, usuario_id, numero, cep, bairro, cidade, estado, complemento, logradouro) values (9, 9,'993','06364550', 'Jardim Ana Estela', 'Carapicuiba','São Paulo', 'casa 2', 'Rua Paraguacu Paulista');

INSERT INTO usuarios (id, data_alteracao, login, email, nome, senha_hash, tipo_usuario) values (10, '2025-06-02 15:00:00.333', 'cenario28', 'cenario28@hotmail.com', 'pessoa vinte e oito', 'senha28', 'CLIENTE');
INSERT INTO enderecos (id, usuario_id, numero, cep, bairro, cidade, estado, complemento, logradouro) values (10, 10,'993','06364550', 'Jardim Ana Estela', 'Carapicuiba','São Paulo', 'casa 2', 'Rua Paraguacu Paulista');

SELECT setval('usuarios_id_seq', (SELECT MAX(id) FROM usuarios));
SELECT setval('enderecos_id_seq', (SELECT MAX(id) FROM enderecos));