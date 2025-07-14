package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Builder
@Getter
public class FuncionamentoCommand {

    private Integer diaSemana;
    private LocalTime horaAbertura;
    private LocalTime horaFechamento;
    private Boolean flagDiaAberto;

}
