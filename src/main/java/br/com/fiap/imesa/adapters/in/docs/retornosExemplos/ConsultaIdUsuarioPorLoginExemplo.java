package br.com.fiap.imesa.adapters.in.docs.retornosExemplos;

public class ConsultaIdUsuarioPorLoginExemplo {
    public static final String RETORNO_SUCESSO_CONSULTA_USUARIO_POR_LOGIN = "{\n" +
            "\t\"id\": 1,\n" +
            "\t\"nome\": \"Diego Pasini\",\n" +
            "\t\"email\": \"diego_t_pasini@hotmail.com\",\n" +
            "\t\"login\": \"diego_pasini\",\n" +
            "\t\"tipoUsuario\": \"CLIENTE\"\n" +
            "}";
    public static final String RETORNO_NOT_FOUND_CONSULTA_USUARIO_POR_LOGIN = "{\n" +
            "\t\"status\": 404,\n" +
            "\t\"message\": \"Usuário não encontrado\"\n" +
            "}";
}
