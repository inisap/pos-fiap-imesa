package br.com.fiap.imesa.application.exception;

import io.micrometer.common.util.StringUtils;

public class TipoCozinhaNaoEncontradoException extends RuntimeException {

    public TipoCozinhaNaoEncontradoException(String message, Integer codigo) {
        super(StringUtils.isEmpty(message) ?
                String.format("Codigo Tipo Cozinha não existe: [%s]", codigo) :
                message +" "+ codigo);
    }
}
