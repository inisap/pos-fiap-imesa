package br.com.fiap.imesa.adapter.inbound.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RestauranteDtoRequest {

    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String nome;
    @NotNull(message = "é obrigatório")
    private Integer tipoCozinha;
    @NotNull(message = "é obrigatório")
    private Long usuarioId;
}
