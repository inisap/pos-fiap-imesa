package br.com.fiap.imesa.adapters.in.docs.retornosExemplos;

public class DeletaUsuarioExemplo {
    public static final String RETORNO_SUCESSO_DELECAO = "{\n" +
            "\t\"id\": 1,\n" +
            "\t\"nome\": \"Diego Pasini\",\n" +
            "\t\"email\": \"diego_t_pasini@hotmail.com\",\n" +
            "\t\"login\": \"diego_pasini\",\n" +
            "\t\"tipoUsuario\": \"CLIENTE\"\n" +
            "}";
    public static final String RETORNO_NOT_FOUND_NA_EXCLUSAO = "{\n" +
            "\t\"status\": 404,\n" +
            "\t\"message\": \"Usuário não encontrado\"\n" +
            "}";
}
