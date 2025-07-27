package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletaRestauranteUseCase {

    private final IRestauranteRepository restauranteRepository;

    public DeletaRestauranteUseCase(IRestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public void run(Long restauranteId) {

        //consultando se cardapio e id existem na combinacao para deletar
        var restauranteDomain = restauranteRepository.consultaPorId(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(null, restauranteId));


        restauranteRepository.deletar(restauranteDomain);
    }
}
