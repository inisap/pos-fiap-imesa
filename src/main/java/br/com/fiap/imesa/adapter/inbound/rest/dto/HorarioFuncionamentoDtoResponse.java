package br.com.fiap.imesa.adapter.inbound.rest.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class HorarioFuncionamentoDtoResponse {

    private List<HorarioFuncionamentoDiarioDtoResponse> horariosFuncionamento;

}
