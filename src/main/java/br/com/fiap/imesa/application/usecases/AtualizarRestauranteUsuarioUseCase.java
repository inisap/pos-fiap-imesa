package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.application.exception.TipoCozinhaNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.AtualizarRestauranteCommand;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarRestauranteUsuarioUseCase {

    private final ITipoCozinhaRepository tipoCozinhaRepository;
    private final IRestauranteRepository restauranteRepository;

    public AtualizarRestauranteUsuarioUseCase(
                                          ITipoCozinhaRepository tipoCozinhaRepository,
                                          IRestauranteRepository restauranteRepository) {
        this.tipoCozinhaRepository = tipoCozinhaRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Restaurante run(AtualizarRestauranteCommand atualizarRestauranteCommand) {

        //validando se restaurante existe
        var restauranteDomain = restauranteRepository.consultaPorId(atualizarRestauranteCommand.getIdRestaurante())
                .orElseThrow(() -> new RestauranteNaoEncontradoException(null, atualizarRestauranteCommand.getIdRestaurante()));

        //validar se cozinha selecionada existe
        var tipoCozinha = tipoCozinhaRepository.consultarPorIdTipoCozinha(atualizarRestauranteCommand.getTipoCozinha())
                .orElseThrow(() -> new TipoCozinhaNaoEncontradoException(null, atualizarRestauranteCommand.getTipoCozinha()));


        restauranteDomain.setTipoCozinha(tipoCozinha);
        restauranteDomain.setNome(atualizarRestauranteCommand.getNome());

        return restauranteRepository.atualizar(restauranteDomain);
    }

}
