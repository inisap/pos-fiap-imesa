package br.com.fiap.imesa.application.mapper;

import br.com.fiap.imesa.application.usecases.command.GravarRestauranteCommand;
import br.com.fiap.imesa.application.usecases.command.HorarioFuncionamentoCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.restaurante.HorarioFuncionamento;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CriarRestauranteCommandMapper {

    public static Restaurante commandToDomain(GravarRestauranteCommand gravarRestauranteCommand){

        List<HorarioFuncionamento> horarioFuncionamentoList = new ArrayList<>();

        for (HorarioFuncionamentoCommand horario : gravarRestauranteCommand.getHorarioFuncionamento()){
            var horarioFuncionamento =
                    HorarioFuncionamento.builder()
                            .diaSemana(horario.getDiaSemana())
                            .horaAbertura(horario.getHoraAbertura())
                            .horaFechamento(horario.getHoraFechamento())
                            .build();
            horarioFuncionamentoList.add(horarioFuncionamento);
        }

        return Restaurante.builder()
                .nome(gravarRestauranteCommand.getNome())
                .horarioFuncionamento(horarioFuncionamentoList)
                .build();
    }
}
