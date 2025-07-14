package br.com.fiap.imesa.application.exception;

import io.micrometer.common.util.StringUtils;

public class LoginNaoEncontradoException extends RuntimeException {

    public LoginNaoEncontradoException(String message, String var) {
        super(StringUtils.isEmpty(message) ?
                String.format("Login não encontrado: [%s]", var) :
                message +" "+ var);
    }
}
