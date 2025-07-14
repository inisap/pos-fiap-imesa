package br.com.fiap.imesa.infrastructure.repository.mapper;

import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.infrastructure.repository.entity.HorarioFuncionamentoEntity;
import br.com.fiap.imesa.infrastructure.repository.entity.RestauranteEntity;

import java.util.ArrayList;
import java.util.List;

public class HorarioFuncionamentoEntityMapper {

    public static List<HorarioFuncionamento> toDomain(List<HorarioFuncionamentoEntity> horarioFuncionamentoEntityList) {

        List<HorarioFuncionamento> horarioFuncionamentoList = new ArrayList<>();

        for(HorarioFuncionamentoEntity hf : horarioFuncionamentoEntityList){

            var restaurante = Restaurante.builder()
                    .id(hf.getRestaurante().getId())
                    .build();

            horarioFuncionamentoList.add(
                    HorarioFuncionamento.builder()
                    .id(hf.getId())
                    .restauranteId(restaurante)
                    .diaSemana(hf.getDiaSemana())
                    .horaAbertura(hf.getHoraAbertura())
                    .horaFechamento(hf.getHoraFechamento())
                    .flagDiaAberto(hf.getFlagAberto())
                    .build());
        }
        return horarioFuncionamentoList;
    }

    public static List<HorarioFuncionamentoEntity> toEntity(List<HorarioFuncionamento> horarioFuncionamentoList) {

        List<HorarioFuncionamentoEntity> horarioFuncionamentoEntityList = new ArrayList<>();

        for(HorarioFuncionamento hf : horarioFuncionamentoList){
            var restaurante = RestauranteEntity.builder()
                    .id(hf.getRestauranteId().getId())
                    .build();

            horarioFuncionamentoEntityList.add(HorarioFuncionamentoEntity.builder()
                    .id(hf.getId())
                    .diaSemana(hf.getDiaSemana())
                    .horaAbertura(hf.getHoraAbertura())
                    .horaFechamento(hf.getHoraFechamento())
                    .flagAberto(hf.getFlagDiaAberto())
                    .restaurante(restaurante)
                    .build());
        }

        return horarioFuncionamentoEntityList;
    }
}
