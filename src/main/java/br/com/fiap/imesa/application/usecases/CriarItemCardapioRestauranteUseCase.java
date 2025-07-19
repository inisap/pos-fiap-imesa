package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.CardapioNaoEncontradoException;
import br.com.fiap.imesa.application.mapper.ItemCardapioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.CriarItemCardapioCommand;
import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.imesa.domain.gateway.ICardapioRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IItemCardapioRestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarItemCardapioRestauranteUseCase {

    private final ICardapioRestauranteRepository cardapioRestauranteRepository;
    private final IItemCardapioRestauranteRepository itemCardapioRestauranteRepository;

    public CriarItemCardapioRestauranteUseCase(ICardapioRestauranteRepository cardapioRestauranteRepository,
                                               IItemCardapioRestauranteRepository itemCardapioRestauranteRepository) {
        this.cardapioRestauranteRepository = cardapioRestauranteRepository;
        this.itemCardapioRestauranteRepository = itemCardapioRestauranteRepository;
    }

    public ItemCardapio run(CriarItemCardapioCommand command) {

        //validando se cardapio existe
        cardapioRestauranteRepository.consultar(command.getIdCardapio())
                .orElseThrow(() -> new CardapioNaoEncontradoException(null, command.getIdCardapio()));

        var itemCardapioCriar = ItemCardapioCommandMapper.commandCriarToDomain(command);

        return itemCardapioRestauranteRepository.salvar(itemCardapioCriar);

    }
}
