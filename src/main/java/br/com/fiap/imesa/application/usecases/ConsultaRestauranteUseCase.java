package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.mapper.ConsultarRestauranteCommandMapper;
import br.com.fiap.imesa.application.usecases.command.ConsultarRestaurantesCommand;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ConsultaRestauranteUseCase {
    private final IRestauranteRepository consultaRestauranteRepository;

    public ConsultaRestauranteUseCase(IRestauranteRepository consultaRestauranteRepository) {
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
