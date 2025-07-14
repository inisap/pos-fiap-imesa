package br.com.fiap.imesa.application.exception;

import io.micrometer.common.util.StringUtils;

public class RestauranteNaoEncontradoException extends RuntimeException {

    public RestauranteNaoEncontradoException(String message, Long codigo) {
        super(StringUtils.isEmpty(message) ?
                String.format("Id do Restaurante não encontrado: [%s]", codigo) :
                message +" "+ codigo);
    }
}
