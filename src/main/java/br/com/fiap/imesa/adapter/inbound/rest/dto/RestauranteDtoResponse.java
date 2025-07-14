package br.com.fiap.imesa.adapter.inbound.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RestauranteDtoResponse {

    private String nome;
    private Integer tipoCozinha;
    private Long usuarioId;
}
