package br.com.fiap.imesa.adapters.in.docs.retornosExemplos;

public class ConsultaUsuarioExemplo {
    public static final String RETORNO_SUCESSO_CONSULTA_USUARIO_POR_ID = "{\n" +
            "\t\"id\": 3,\n" +
            "\t\"nome\": \"Diego Pasini\",\n" +
            "\t\"email\": \"diego_t_pasini@hotmail.com\",\n" +
            "\t\"login\": \"pasini\",\n" +
            "\t\"dataUltimaAlteracao\": \"2025-05-22T22:39:34.681797\",\n" +
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
    public static final String RETORNO_NOT_FOUND_CONSULTA_USUARIO_POR_ID = "{\n" +
            "\t\"status\": 404,\n" +
            "\t\"message\": \"Usuário não encontrado\"\n" +
            "}";
}
