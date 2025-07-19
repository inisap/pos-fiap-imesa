package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.CardapioNaoEncontradoException;
import br.com.fiap.imesa.application.exception.CombinacaoItemCardapioEIdCardarpioNaoExisteException;
import br.com.fiap.imesa.application.mapper.ItemCardapioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.AtualizarItemCardapioCommand;
import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.imesa.domain.gateway.ICardapioRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IItemCardapioRestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizaItemCardapioRestauranteUseCase {

    private final IItemCardapioRestauranteRepository itemCardapioRestauranteRepository;
    private final ICardapioRestauranteRepository cardapioRestauranteRepository;

    public AtualizaItemCardapioRestauranteUseCase(IItemCardapioRestauranteRepository itemCardapioRestauranteRepository,
                                                  ICardapioRestauranteRepository cardapioRestauranteRepository) {
        this.itemCardapioRestauranteRepository = itemCardapioRestauranteRepository;
        this.cardapioRestauranteRepository = cardapioRestauranteRepository;
    }

    public ItemCardapio run(AtualizarItemCardapioCommand command) {

        var itemCardapioDomain = ItemCardapioCommandMapper.commandAtualizarToDomain(command);

        //validando se a combinacao do item com o cardapio existe
        itemCardapioRestauranteRepository.consultarPorIdItemCardapioEIdCardapio(itemCardapioDomain)
                .orElseThrow(() -> new CombinacaoItemCardapioEIdCardarpioNaoExisteException(
                        null, command.getIdItem(), command.getIdCardapio()));

        //validando se o cardapio existe
        cardapioRestauranteRepository.consultar(command.getIdCardapio())
                .orElseThrow(() -> new CardapioNaoEncontradoException(null, command.getIdCardapio()));

        var cardapioCriar = ItemCardapioCommandMapper.commandAtualizarToDomain(command);

        return itemCardapioRestauranteRepository.salvar(cardapioCriar);

    }
}
