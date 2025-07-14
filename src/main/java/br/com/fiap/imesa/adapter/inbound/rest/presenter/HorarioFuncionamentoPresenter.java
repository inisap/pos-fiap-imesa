package br.com.fiap.imesa.adapter.inbound.rest.presenter;

import br.com.fiap.imesa.adapter.inbound.rest.dto.HorarioFuncionamentoDiarioDtoResponse;
import br.com.fiap.imesa.adapter.inbound.rest.dto.HorarioFuncionamentoDtoResponse;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;

import java.util.ArrayList;
import java.util.List;

public class HorarioFuncionamentoPresenter {

    //utilizado apenas para construir o DTo de saida
    public static HorarioFuncionamentoDtoResponse toDto(List<HorarioFuncionamento> horarioFuncionamentoList){

        List<HorarioFuncionamentoDiarioDtoResponse> horarioFuncionamento = new ArrayList<>();

        for(HorarioFuncionamento hf : horarioFuncionamentoList){
            horarioFuncionamento.add(
                HorarioFuncionamentoDiarioDtoResponse.builder()
                    .diaSemana(hf.getDiaSemana())
                    .horaAbertura(hf.getHoraAbertura())
                    .horaFechamento(hf.getHoraFechamento())
                    .flagDiaAberto(hf.getFlagDiaAberto())
                    .build());
        }

        return HorarioFuncionamentoDtoResponse.builder()
                .horariosFuncionamento(horarioFuncionamento)
                .build();

    }
}
