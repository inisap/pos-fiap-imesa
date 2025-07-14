package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;

import java.util.List;

public interface IConsultaHorarioRestaurantePorIdRepository {

    List<HorarioFuncionamento> consultar(Long restauranteId);
}
