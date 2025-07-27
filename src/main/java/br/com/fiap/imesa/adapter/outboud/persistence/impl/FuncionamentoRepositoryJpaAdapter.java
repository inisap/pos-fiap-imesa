package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.mapper.HorarioFuncionamentoEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.HorarioFuncionamentoRepository;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.gateway.IHorarioFuncionamentoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FuncionamentoRepositoryJpaAdapter implements IHorarioFuncionamentoRepository {

    private final HorarioFuncionamentoRepository horarioFuncionamentoRepository;

    public FuncionamentoRepositoryJpaAdapter(HorarioFuncionamentoRepository horarioFuncionamentoRepository){
        this.horarioFuncionamentoRepository = horarioFuncionamentoRepository;
    }

    @Override
    public List<HorarioFuncionamento> salvar(List<HorarioFuncionamento> horarioFuncionamento) {

        var horarioFuncionamentoEntityList = HorarioFuncionamentoEntityMapper.toEntity(horarioFuncionamento);

        var retorno = horarioFuncionamentoRepository.saveAll(horarioFuncionamentoEntityList);

        return HorarioFuncionamentoEntityMapper.toDomain(retorno);
    }

    @Override
    public List<HorarioFuncionamento> consultarPorIdDeRestaurante(Long restauranteId) {

        var listRestaurantesEntity = horarioFuncionamentoRepository.findByRestauranteId(restauranteId);

        return HorarioFuncionamentoEntityMapper.toDomain(listRestaurantesEntity);
    }
}
