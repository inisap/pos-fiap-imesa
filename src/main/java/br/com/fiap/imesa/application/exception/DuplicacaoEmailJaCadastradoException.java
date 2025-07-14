package br.com.fiap.imesa.application.exception;

import io.micrometer.common.util.StringUtils;

public class DuplicacaoEmailJaCadastradoException extends RuntimeException {

    public DuplicacaoEmailJaCadastradoException(String message, String var) {
        super(StringUtils.isEmpty(message) ?
                String.format("Já existe um usuário com este email: [%s]", var) :
                message +" "+ var);
    }
}
