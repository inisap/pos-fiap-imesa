package br.com.fiap.imesa.application.exception;

import io.micrometer.common.util.StringUtils;

public class CombinacaoCardarpioERestauranteNaoExisteException extends RuntimeException {

    public CombinacaoCardarpioERestauranteNaoExisteException(String message, Long idCardapio, Long idRestaurante) {
        super(StringUtils.isEmpty(message) ?
                String.format("Este Cardapio: [%s] nao pertence a esta Restaurante id [%s]", idCardapio, idRestaurante) :
                message);
    }
}
