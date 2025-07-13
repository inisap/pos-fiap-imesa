package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.HorarioFuncionamentoDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.RestauranteDtoRequest;
import br.com.fiap.imesa.application.usecases.command.GravarRestauranteCommand;
import br.com.fiap.imesa.application.usecases.command.HorarioFuncionamentoCommand;

import java.util.ArrayList;
import java.util.List;

public class CriaRestauranteRequestMapper {

    public static GravarRestauranteCommand dtoToCommand(RestauranteDtoRequest restauranteDtoRequest){

        List<HorarioFuncionamentoCommand> horarioFuncionamentoCommandList = new ArrayList<>();

        for (HorarioFuncionamentoDtoRequest horario : restauranteDtoRequest.getHorarioFuncionamento()){
            var horarioFuncionamento =
                    HorarioFuncionamentoCommand.builder()
                    .diaSemana(horario.getDiaSemana())
                    .horaAbertura(horario.getHoraAbertura())
                    .horaFechamento(horario.getHoraFechamento())
                    .build();
            horarioFuncionamentoCommandList.add(horarioFuncionamento);
        }

        return GravarRestauranteCommand
                .builder()
                .nome(restauranteDtoRequest.getNome())
                .tipoCozinha(restauranteDtoRequest.getTipoCozinha())
                .horarioFuncionamento(horarioFuncionamentoCommandList)
                .usuarioId(restauranteDtoRequest.getUsuarioId())
                .build();
    }
}
