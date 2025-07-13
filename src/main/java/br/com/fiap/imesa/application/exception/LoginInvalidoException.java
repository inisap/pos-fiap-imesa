package br.com.fiap.imesa.application.exception;

public class LoginInvalidoException extends RuntimeException {

    public LoginInvalidoException(String message) {
        super(message);
    }
}
