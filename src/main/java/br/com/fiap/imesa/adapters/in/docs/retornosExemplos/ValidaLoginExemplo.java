package br.com.fiap.imesa.adapters.in.docs.retornosExemplos;

public class ValidaLoginExemplo {
    public static final String RETORNO_SUCESSO = "{\n" +
            "\t\"nome\": \"Diego Pasini\",\n" +
            "\t\"login\": \"pasini\",\n" +
            "\t\"tipo\": \"CLIENTE\"\n" +
            "}";
    public static final String RETORNO_USUARIO_SENHA_INVALIDO = "{\n" +
            "\t\"status\": 401,\n" +
            "\t\"message\": \"Usuário ou senha inválidos\"\n" +
            "}";
}
