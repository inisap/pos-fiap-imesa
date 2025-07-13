package br.com.fiap.imesa.adapter.inbound.rest.presenter;

import br.com.fiap.imesa.adapter.inbound.rest.dto.HorarioFuncionamentoDtoResponse;
import br.com.fiap.imesa.adapter.inbound.rest.dto.RestauranteDtoResponse;
import br.com.fiap.imesa.domain.entities.restaurante.HorarioFuncionamento;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class CriarRestaurantePresenter {

    //utilizado apenas para construir o DTo de saida
    public static RestauranteDtoResponse toDto(Restaurante restaurante){

        List<HorarioFuncionamentoDtoResponse> horarioFuncionamento = new ArrayList<>();

        for(HorarioFuncionamento horaFunc : restaurante.getHorarioFuncionamento()){
            horarioFuncionamento.add(HorarioFuncionamentoDtoResponse.builder()
                    .diaSemana(horaFunc.getDiaSemana())
                    .horaAbertura(horaFunc.getHoraAbertura())
                    .horaFechamento(horaFunc.getHoraFechamento())
                    .flagDiaAberto(horaFunc.getFlagDiaAberto())
                    .build());
        }

        return RestauranteDtoResponse.builder()
                .nome(restaurante.getNome())
                .tipoCozinha(restaurante.getTipoCozinha().getId())
                .horarioFuncionamento(horarioFuncionamento)
                .usuarioId(restaurante.getUsuarioProprietario().getId())
                .build();
    }
}
