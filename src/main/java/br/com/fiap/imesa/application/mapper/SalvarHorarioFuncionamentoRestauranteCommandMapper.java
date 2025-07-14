package br.com.fiap.imesa.application.mapper;

import br.com.fiap.imesa.application.usecases.command.FuncionamentoCommand;
import br.com.fiap.imesa.application.usecases.command.HorarioFuncionamentoCommand;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;

import java.util.ArrayList;
import java.util.List;

public class SalvarHorarioFuncionamentoRestauranteCommandMapper {

    public static List<HorarioFuncionamento> commandToDomain(HorarioFuncionamentoCommand horarioFuncionamento) {

        List<HorarioFuncionamento> list = new ArrayList<>();

        for (FuncionamentoCommand funcionamento : horarioFuncionamento.getFuncionamentoCommand()) {
            list.add(
                    HorarioFuncionamento.builder()
                            .diaSemana(funcionamento.getDiaSemana())
                            .horaAbertura(funcionamento.getHoraAbertura())
                            .horaFechamento(funcionamento.getHoraFechamento())
                            .flagDiaAberto(funcionamento.getFlagDiaAberto())
                            .build());
        }

        return list;
    }
}
