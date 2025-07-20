package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;

import java.util.List;

public interface IHorarioFuncionamentoRepository {

    List<HorarioFuncionamento> salvar(List<HorarioFuncionamento> horarioFuncionamento);

    List<HorarioFuncionamento> consultarPorIdDeRestaurante(Long restauranteId);

}
