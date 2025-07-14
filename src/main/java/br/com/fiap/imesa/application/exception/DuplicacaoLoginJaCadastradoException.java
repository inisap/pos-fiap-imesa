package br.com.fiap.imesa.application.exception;

import io.micrometer.common.util.StringUtils;

public class DuplicacaoLoginJaCadastradoException extends RuntimeException {

    public DuplicacaoLoginJaCadastradoException(String message, String var) {
            super(StringUtils.isEmpty(message) ?
                    String.format("Já existe um usuário com este login: [%s]", var) :
                    message +" "+ var);
    }
}
