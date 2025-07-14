package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class HorarioFuncionamentoCommand {

    private Long restauranteId;
    private List<FuncionamentoCommand> funcionamentoCommand;
}
