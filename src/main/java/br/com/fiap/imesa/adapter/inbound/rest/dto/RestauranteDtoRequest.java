package br.com.fiap.imesa.adapter.inbound.rest.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class RestauranteDtoRequest {

    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String nome;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private Integer tipoCozinha;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    @Valid
    private List<HorarioFuncionamentoDtoRequest> horarioFuncionamento;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private Long usuarioId;
}
