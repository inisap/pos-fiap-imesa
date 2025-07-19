package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.gateway.IConsultaHorarioRestaurantePorIdRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.HorarioFuncionamentoEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.HorarioFuncionamentoRepository;
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
