package br.com.fiap.imesa.application.exception;

import io.micrometer.common.util.StringUtils;

public class CardapioNaoExisteParaRestauranteException extends RuntimeException {

    public CardapioNaoExisteParaRestauranteException(String message, Long codigo) {
        super(StringUtils.isEmpty(message) ?
                String.format("Não existe cardapio cadastrado para o id do restaurante: [%s]", codigo) :
                message +" "+ codigo);
    }
}
