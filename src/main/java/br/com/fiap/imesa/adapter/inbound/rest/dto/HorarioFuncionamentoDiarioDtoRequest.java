package br.com.fiap.imesa.adapter.inbound.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class HorarioFuncionamentoDiarioDtoRequest {

    @NotNull(message = "é obrigatório")
    private Integer diaSemana;
    @NotNull(message = "é obrigatório")
    private LocalTime horaAbertura;
    @NotNull(message = "é obrigatório")
    private LocalTime horaFechamento;
    @NotNull(message = "é obrigatório")
    private Boolean flagDiaAberto;

}
