package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.HorarioFuncionamentoDiarioDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.HorarioFuncionamentoDtoRequest;
import br.com.fiap.imesa.application.usecases.command.FuncionamentoCommand;
import br.com.fiap.imesa.application.usecases.command.HorarioFuncionamentoCommand;

import java.util.ArrayList;
import java.util.List;

public class HorarioFuncionamentoRequestMapper {

    public static HorarioFuncionamentoCommand dtoToCommand(Long restauranteId, HorarioFuncionamentoDtoRequest horarioFuncionamentoDtoRequest){

        List<FuncionamentoCommand> funcionamentoCommands = new ArrayList<>();

        for(HorarioFuncionamentoDiarioDtoRequest horario : horarioFuncionamentoDtoRequest.getHorariosFuncionamento()){
            funcionamentoCommands.add(
                    FuncionamentoCommand.builder()
                    .diaSemana(horario.getDiaSemana())
                    .horaAbertura(horario.getHoraAbertura())
                    .horaFechamento(horario.getHoraFechamento())
                    .flagDiaAberto(horario.getFlagDiaAberto())
                    .build()
            );
        }

        return HorarioFuncionamentoCommand.builder()
                .restauranteId(restauranteId)
                .funcionamentoCommand(funcionamentoCommands)
                .build();

    }
}
