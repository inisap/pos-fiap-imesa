package br.com.fiap.imesa.application.exception;

import io.micrometer.common.util.StringUtils;

public class CombinacaoItemCardapioEIdCardarpioNaoExisteException extends RuntimeException {

    public CombinacaoItemCardapioEIdCardarpioNaoExisteException(String message, Long idItemCardapio, Long idCardapio) {
        super(StringUtils.isEmpty(message) ?
                String.format("Este Item: [%s] nao pertence a este Cardapio id [%s]", idItemCardapio, idCardapio) :
                message);
    }
}
