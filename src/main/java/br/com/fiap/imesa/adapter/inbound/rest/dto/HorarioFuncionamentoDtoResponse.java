package br.com.fiap.imesa.adapter.inbound.rest.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class HorarioFuncionamentoDtoResponse {

    private Integer diaSemana;
    private LocalTime horaAbertura;
    private LocalTime horaFechamento;
    private Boolean flagDiaAberto;

}
