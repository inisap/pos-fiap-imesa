package br.com.fiap.imesa.adapter.inbound.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AtualizarCardapioDtoRequest {

    @NotNull(message = "é obrigatório")
    private String descricaoCardapio;

}
