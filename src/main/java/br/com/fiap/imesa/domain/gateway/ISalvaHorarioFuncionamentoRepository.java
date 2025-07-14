package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;

import java.util.List;

public interface ISalvaHorarioFuncionamentoRepository {

    List<HorarioFuncionamento> salvar(List<HorarioFuncionamento> horarioFuncionamento);
}
