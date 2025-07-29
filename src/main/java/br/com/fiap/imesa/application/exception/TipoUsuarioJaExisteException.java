package br.com.fiap.imesa.application.exception;

import io.micrometer.common.util.StringUtils;

public class TipoUsuarioJaExisteException extends RuntimeException {

    public TipoUsuarioJaExisteException(String message, String nome) {

        super(StringUtils.isEmpty(message) ?
                String.format("Tipo Usuario ja cadastrado: [%s]", nome) :
                message +" "+ nome);
    }
}
