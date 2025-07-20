package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.gateway.IHorarioFuncionamentoRepository;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaHorarioRestauranteUseCase {
    private final IRestauranteRepository consultaRestauranteRepository;
    private final IHorarioFuncionamentoRepository horarioFuncionamentoRepository;

    public ConsultaHorarioRestauranteUseCase(IRestauranteRepository consultaRestauranteRepository,
                                             IHorarioFuncionamentoRepository horarioFuncionamentoRepository) {
        this.consultaRestauranteRepository = consultaRestauranteRepository;
        this.horarioFuncionamentoRepository = horarioFuncionamentoRepository;
    }

    public List<HorarioFuncionamento> run(Long restauranteId){


        consultaRestauranteRepository.consultaPorId(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(null, restauranteId));

        return horarioFuncionamentoRepository.consultarPorIdDeRestaurante(restauranteId);
    }
}
