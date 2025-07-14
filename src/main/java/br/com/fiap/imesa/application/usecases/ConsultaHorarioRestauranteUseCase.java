package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.gateway.IConsultaHorarioRestaurantePorIdRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaRestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaHorarioRestauranteUseCase {
    private final IConsultaRestauranteRepository consultaRestauranteRepository;
    private final IConsultaHorarioRestaurantePorIdRepository consultaHorarioRestaurantePorIdRepository;

    public ConsultaHorarioRestauranteUseCase(IConsultaRestauranteRepository consultaRestauranteRepository,
                                             IConsultaHorarioRestaurantePorIdRepository consultaHorarioRestaurantePorIdRepository) {
        this.consultaRestauranteRepository = consultaRestauranteRepository;
        this.consultaHorarioRestaurantePorIdRepository = consultaHorarioRestaurantePorIdRepository;
    }

    public List<HorarioFuncionamento> run(Long restauranteId){


        consultaRestauranteRepository.consultaPorId(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(null, restauranteId));

        return consultaHorarioRestaurantePorIdRepository.consultar(restauranteId);
    }
}
