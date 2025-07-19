package br.com.fiap.imesa.adapter.inbound.rest.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CardapioDtoResponse {

    private Long idCardapio;

    private String descricaoCardapio;

    private Long idRestaurante;

}
