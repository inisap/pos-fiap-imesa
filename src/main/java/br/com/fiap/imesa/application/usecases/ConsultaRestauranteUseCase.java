package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.mapper.ConsultarRestauranteCommandMapper;
import br.com.fiap.imesa.application.usecases.command.ConsultarRestaurantesCommand;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.IConsultaRestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ConsultaRestauranteUseCase {
    private final IConsultaRestauranteRepository consultaRestauranteRepository;

    public ConsultaRestauranteUseCase(IConsultaRestauranteRepository consultaRestauranteRepository) {
        this.consultaRestauranteRepository = consultaRestauranteRepository;
    }

    public List<Restaurante> run(ConsultarRestaurantesCommand command){

        if(Objects.nonNull(command.getCodigoTipoCozinha()) || Objects.nonNull(command.getNome())){

            var restauranteDomain = ConsultarRestauranteCommandMapper.commandToDomain(command);

            return consultaRestauranteRepository.consultarComFiltros(restauranteDomain);
        }else{
            return consultaRestauranteRepository.consultarTodos();
        }
    }
}
