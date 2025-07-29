package br.com.fiap.imesa.application.exception;

import io.micrometer.common.util.StringUtils;

public class TipoUsuarioNaoEncontradoException extends RuntimeException {

    public TipoUsuarioNaoEncontradoException(String message, String tipoUsuario) {

        super(StringUtils.isEmpty(message) ?
                String.format("Tipo Usuario não existe: [%s]", tipoUsuario) :
                message +" "+ tipoUsuario);
    }
}
