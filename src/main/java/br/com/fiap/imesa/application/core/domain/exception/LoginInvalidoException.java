package br.com.fiap.imesa.application.core.domain.exception;

public class LoginInvalidoException extends RuntimeException {

    public LoginInvalidoException(String message) {
        super(message);
    }
}
