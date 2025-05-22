# pos-fiap-imesa
Projeto de gestão de Restaurante criado na Pós Tech em Arquitetura Java da Fiap

---

## Pré-requisitos

Certifique-se de ter os seguintes softwares instalados:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven](https://maven.apache.org/)

---

## Como executar

### 1. Clone o repositório

git clone https://github.com/inisap/pos-fiap-imesa

### 2. Compile a aplicação localmente utilizando maven

./mvnw clean package

### 3. Suba os containers com Docker Compose

docker-compose up --build

### 4. Acesse a aplicação
##### Obs: Utilizamos portas altas para evitar risco de conflito na hora de executar o projeto

API Spring Boot: http://localhost:9000/imesa

Postgres:

Host: localhost

Porta: 5432
Usuário: imesa_user
Senha: imesa_password
Database: imesa_db

# Acessar documentação completa dos endpoints

executar a aplicação e acessar o endpoint via navegador: http://localhost:9000/imesa/swagger-ui/index.html


