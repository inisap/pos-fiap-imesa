CREATE TABLE IF NOT EXISTS "usuarios"(
    "id" BIGINT NOT NULL,
    "nome" VARCHAR(150) NOT NULL,
    "email" VARCHAR(100) NOT NULL,
    "login" VARCHAR(50) NOT NULL,
    "senha_hash" VARCHAR(255) NOT NULL,
    "data_alteracao" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "tipo_usuario" BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS "restaurantes"(
    "id" BIGINT NOT NULL,
    "nome" VARCHAR(200) NOT NULL,
    "tipo_cozinha" BIGINT NOT NULL,
    "usuario_id" BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS "enderecos"(
    "id" BIGINT NOT NULL,
    "usuario_id" BIGINT NOT NULL,
    "cep" VARCHAR(20) NOT NULL,
    "logradouro" VARCHAR(200) NOT NULL,
    "numero" VARCHAR(10) NOT NULL,
    "complemento" VARCHAR(100) NOT NULL,
    "bairro" VARCHAR(40) NOT NULL,
    "cidade" VARCHAR(50) NOT NULL,
    "estado" VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS "tipos_usuarios"(
    "id" BIGINT NOT NULL,
    "descricao_tipo_usuario" INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS "tipos_cozinhas"(
    "id" INTEGER NOT NULL,
    "descricao_tipo_cozinha" VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS "cardapios"(
    "id" BIGINT NOT NULL,
    "descricao_cardapio" VARCHAR(255) NOT NULL,
    "restaurante_id" BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS "itens_cardapios"(
    "id" BIGINT NOT NULL,
    "cardapio_id" BIGINT NOT NULL,
    "nome_prato" VARCHAR(100) NOT NULL,
    "descricao_prato" VARCHAR(255) NOT NULL,
    "preco" BIGINT NOT NULL,
    "disponivel_apenas_restaurante" BOOLEAN NOT NULL,
    "link_imagem_prato" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "horarios_funcionamentos"(
    "id" BIGINT NOT NULL,
    "diaSemana" SMALLINT NOT NULL,
    "horaAbertura" TIME(0) WITHOUT TIME ZONE NOT NULL,
    "horarioFechamento" TIME(0) WITHOUT TIME ZONE NOT NULL,
    "restaurante_id" BIGINT NOT NULL,
    "flagAberto" BOOLEAN NOT NULL
);
--ALTER TABLE
--    "usuarios" ADD PRIMARY KEY("id");
--ALTER TABLE
--    "usuarios" ADD CONSTRAINT "usuarios_email_unique" UNIQUE("email");
--ALTER TABLE
--    "usuarios" ADD CONSTRAINT "usuarios_login_unique" UNIQUE("login");
--ALTER TABLE
--    "restaurantes" ADD PRIMARY KEY("id");
--ALTER TABLE
--    "enderecos" ADD PRIMARY KEY("id");
--ALTER TABLE
--    "tipos_usuarios" ADD PRIMARY KEY("id");
--ALTER TABLE
--    "tipos_cozinhas" ADD PRIMARY KEY("id");
--ALTER TABLE
--    "cardapios" ADD PRIMARY KEY("id");
--ALTER TABLE
--    "itens_cardapios" ADD PRIMARY KEY("id");
--ALTER TABLE
--    "horarios_funcionamentos" ADD PRIMARY KEY("id");
--ALTER TABLE
--    "usuarios" ADD CONSTRAINT "usuarios_tipo_usuario_foreign" FOREIGN KEY("tipo_usuario") REFERENCES "tipos_usuarios"("id");
--ALTER TABLE
--    "horarios_funcionamentos" ADD CONSTRAINT "horarios_funcionamentos_restaurante_id_foreign" FOREIGN KEY("restaurante_id") REFERENCES "restaurantes"("id");
--ALTER TABLE
--    "cardapios" ADD CONSTRAINT "cardapios_restaurante_id_foreign" FOREIGN KEY("restaurante_id") REFERENCES "restaurantes"("id");
--ALTER TABLE
--    "restaurantes" ADD CONSTRAINT "restaurantes_tipo_cozinha_foreign" FOREIGN KEY("tipo_cozinha") REFERENCES "tipos_cozinhas"("id");
--ALTER TABLE
--    "enderecos" ADD CONSTRAINT "enderecos_usuario_id_foreign" FOREIGN KEY("usuario_id") REFERENCES "usuarios"("id");
--ALTER TABLE
--    "restaurantes" ADD CONSTRAINT "restaurantes_usuario_id_foreign" FOREIGN KEY("usuario_id") REFERENCES "usuarios"("id");
--ALTER TABLE
--    "itens_cardapios" ADD CONSTRAINT "itens_cardapios_cardapio_id_foreign" FOREIGN KEY("cardapio_id") REFERENCES "cardapios"("id");