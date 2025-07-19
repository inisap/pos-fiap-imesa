package br.com.fiap.imesa.application.exception;

import io.micrometer.common.util.StringUtils;

public class CardapioNaoEncontradoException extends RuntimeException {

    public CardapioNaoEncontradoException(String message, Long codigo) {
        super(StringUtils.isEmpty(message) ?
                String.format("Id do Cardapio não encontrado: [%s]", codigo) :
                message +" "+ codigo);
    }
}
