package br.com.fiap.imesa.adapter.inbound.rest.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TipoCozinhaDtoResponse {
    private Integer codigoTipoCozinha;
    private String descricaoTipoCozinha;

}
