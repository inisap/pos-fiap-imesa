package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.application.mapper.CardapioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.CriarCardapioCommand;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.gateway.ICardapioRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarCardapioRestauranteUseCase {

    private final ICardapioRestauranteRepository salvaCardapioRestauranteRepository;
    private final IRestauranteRepository consultaRestauranteRepository;

    public CriarCardapioRestauranteUseCase(ICardapioRestauranteRepository salvaCardapioRestauranteRepository,
                                           IRestauranteRepository consultaRestauranteRepository) {
        this.salvaCardapioRestauranteRepository = salvaCardapioRestauranteRepository;
        this.consultaRestauranteRepository = consultaRestauranteRepository;
    }

    public Cardapio run(CriarCardapioCommand command) {

        //validando se restaurante existe
        consultaRestauranteRepository.consultaPorId(command.getIdRestaurante())
                .orElseThrow(() -> new RestauranteNaoEncontradoException(null, command.getIdRestaurante()));

        var cardapioCriar = CardapioCommandMapper.commandCriarToDomain(command);

        return salvaCardapioRestauranteRepository.salvar(cardapioCriar);

    }
}
