package br.com.fiap.imesa.adapter.inbound.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RestauranteDtoResponse {

    private String nome;
    private Integer tipoCozinha;
    private List<HorarioFuncionamentoDtoResponse> horarioFuncionamento;
    private Long usuarioId;
}
