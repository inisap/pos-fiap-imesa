package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.CardapioNaoEncontradoException;
import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.application.mapper.CardapioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.AtualizarCardapioCommand;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.gateway.ICardapioRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizaCardapioRestauranteUseCase {

    private final IRestauranteRepository consultaRestauranteRepository;
    private final ICardapioRestauranteRepository cardapioRestauranteRepository;

    public AtualizaCardapioRestauranteUseCase(IRestauranteRepository consultaRestauranteRepository,
                                                      ICardapioRestauranteRepository cardapioRestauranteRepository) {
        this.consultaRestauranteRepository = consultaRestauranteRepository;
        this.cardapioRestauranteRepository = cardapioRestauranteRepository;
    }

    public Cardapio run(AtualizarCardapioCommand command) {

        //validando se restaurante existe
        consultaRestauranteRepository.consultaPorId(command.getIdRestaurante())
                .orElseThrow(() -> new RestauranteNaoEncontradoException(null, command.getIdRestaurante()));

        //validando se o cardapio existe
        cardapioRestauranteRepository.consultar(command.getIdCardapio())
                .orElseThrow(() -> new CardapioNaoEncontradoException(null, command.getIdCardapio()));

        var cardapioCriar = CardapioCommandMapper.commandAtualizarToDomain(command);

        return cardapioRestauranteRepository.salvar(cardapioCriar);

    }
}
