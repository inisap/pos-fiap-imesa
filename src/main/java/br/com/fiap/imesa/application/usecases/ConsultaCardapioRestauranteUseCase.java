package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.CardapioNaoEncontradoException;
import br.com.fiap.imesa.application.exception.CardapioNaoExisteParaRestauranteException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.gateway.ICardapioRestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultaCardapioRestauranteUseCase {

    private final ICardapioRestauranteRepository cardapioRestauranteRepository;

    public ConsultaCardapioRestauranteUseCase(ICardapioRestauranteRepository cardapioRestauranteRepository) {
        this.cardapioRestauranteRepository = cardapioRestauranteRepository;
    }

    public Cardapio run(Long idRestaurante) {

        return cardapioRestauranteRepository.consultar(idRestaurante)
                .orElseThrow(() -> new CardapioNaoExisteParaRestauranteException(null, idRestaurante));

    }
}
