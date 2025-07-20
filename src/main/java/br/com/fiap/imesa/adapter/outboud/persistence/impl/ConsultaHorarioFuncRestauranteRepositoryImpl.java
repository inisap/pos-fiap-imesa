package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.mapper.HorarioFuncionamentoEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.HorarioFuncionamentoRepository;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.gateway.IConsultaHorarioRestaurantePorIdRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsultaHorarioFuncRestauranteRepositoryImpl implements IConsultaHorarioRestaurantePorIdRepository {

    private final HorarioFuncionamentoRepository horarioFuncionamentoRepository;

    public ConsultaHorarioFuncRestauranteRepositoryImpl(HorarioFuncionamentoRepository horarioFuncionamentoRepository){
        this.horarioFuncionamentoRepository = horarioFuncionamentoRepository;
    }

    @Override
    public List<HorarioFuncionamento> consultar(Long restauranteId) {

        var listRestaurantesEntity = horarioFuncionamentoRepository.findByRestauranteId(restauranteId);

        return HorarioFuncionamentoEntityMapper.toDomain(listRestaurantesEntity);
    }
}
