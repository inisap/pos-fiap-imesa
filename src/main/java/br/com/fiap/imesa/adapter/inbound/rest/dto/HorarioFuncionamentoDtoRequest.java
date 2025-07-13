package br.com.fiap.imesa.adapter.inbound.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.sql.Time;
import java.time.LocalTime;

@Getter
public class HorarioFuncionamentoDtoRequest {

    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private Integer diaSemana;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private LocalTime horaAbertura;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private LocalTime horaFechamento;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private Boolean flagDiaAberto;

}
