package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.springdata.HorarioFuncionamentoRepository;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.gateway.ISalvaHorarioFuncionamentoRepository;
import br.com.fiap.imesa.adapter.outboud.persistence.mapper.HorarioFuncionamentoEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SalvarFuncionamentoRepositoryImpl implements ISalvaHorarioFuncionamentoRepository {

    private final HorarioFuncionamentoRepository horarioFuncionamentoRepository;

    public SalvarFuncionamentoRepositoryImpl(HorarioFuncionamentoRepository horarioFuncionamentoRepository){
        this.horarioFuncionamentoRepository = horarioFuncionamentoRepository;
    }

    @Override
    public List<HorarioFuncionamento> salvar(List<HorarioFuncionamento> horarioFuncionamento) {

        var horarioFuncionamentoEntityList = HorarioFuncionamentoEntityMapper.toEntity(horarioFuncionamento);

        var retorno = horarioFuncionamentoRepository.saveAll(horarioFuncionamentoEntityList);

        return HorarioFuncionamentoEntityMapper.toDomain(retorno);

    }
}
