package br.com.fiap.imesa.adapters.in.docs.retornosExemplos;

public class CriarUsuarioExemplo {
    public static final String RETORNO_CRIACAO_USUARIO_SUCESSO = "{\n" +
            "\t\"id\": 12,\n" +
            "\t\"nome\": \"Diego Pasini\",\n" +
            "\t\"email\": \"diego_t_pasini@hotmail.com\",\n" +
            "\t\"login\": \"pasini\",\n" +
            "\t\"tipoUsuario\": \"CLIENTE\",\n" +
            "\t\"endereco\": {\n" +
            "\t\t\"logradouro\": \"Rua Paraguacu Paulista\",\n" +
            "\t\t\"numero\": \"993\",\n" +
            "\t\t\"cidade\": \"Carapicuiba\",\n" +
            "\t\t\"estado\": \"São Paulo\",\n" +
            "\t\t\"cep\": \"06364550\",\n" +
            "\t\t\"complemento\": \"casa 2\",\n" +
            "\t\t\"bairro\": \"Jardim Ana Estela\"\n" +
            "\t}\n" +
            "}";
    public static final String RETORNO_USUARIO_SENHA_INVALIDO = "{\n" +
            "\t\"status\": 401,\n" +
            "\t\"message\": \"Usuário ou senha inválidos\"\n" +
            "}";
}
