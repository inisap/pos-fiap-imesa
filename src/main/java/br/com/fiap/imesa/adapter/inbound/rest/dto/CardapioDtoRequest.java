package br.com.fiap.imesa.adapter.inbound.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CardapioDtoRequest {

    @NotNull(message = "é obrigatório")
    private String descricaoCardapio;

    @NotNull(message = "é obrigatório")
    private Long idRestaurante;

}
