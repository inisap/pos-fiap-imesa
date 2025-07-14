package br.com.fiap.imesa.application.exception;

import io.micrometer.common.util.StringUtils;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(String message, Long codigo) {
        super(StringUtils.isEmpty(message) ?
                String.format("Id de Usuario não encontrado: [%s]", codigo) :
                message +" "+ codigo);
    }
}
