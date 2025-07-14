package br.com.fiap.imesa.application.exception;

import io.micrometer.common.util.StringUtils;

public class EnderecoNaoEncontradoParaUsuarioException extends RuntimeException {

    public EnderecoNaoEncontradoParaUsuarioException(String message, Long codigo) {
        super(StringUtils.isEmpty(message) ?
                String.format("Não existe endereço cadastrado para o usuario Id: [%s]", codigo) :
                message +" "+ codigo);
    }
}
