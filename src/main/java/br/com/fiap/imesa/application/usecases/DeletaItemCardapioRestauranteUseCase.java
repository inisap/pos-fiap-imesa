package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.CombinacaoItemCardapioEIdCardarpioNaoExisteException;
import br.com.fiap.imesa.application.mapper.ItemCardapioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.DeletarItemCardapioCommand;
import br.com.fiap.imesa.domain.gateway.IItemCardapioRestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletaItemCardapioRestauranteUseCase {

    private final IItemCardapioRestauranteRepository itemCardapioRestauranteRepository;

    public DeletaItemCardapioRestauranteUseCase(IItemCardapioRestauranteRepository itemCardapioRestauranteRepository) {
        this.itemCardapioRestauranteRepository = itemCardapioRestauranteRepository;
    }

    public void run(DeletarItemCardapioCommand command) {

        //convertendo o command para domain
        var cardapioDeletar = ItemCardapioCommandMapper.commandDeletarToDomain(command);

        //consultando se cardapio e id existem na combinacao para deletar
        var itemCadapioDeletar  = itemCardapioRestauranteRepository.consultarPorIdItemCardapioEIdCardapio(cardapioDeletar)
                .orElseThrow(() -> new CombinacaoItemCardapioEIdCardarpioNaoExisteException(null, command.getIdItemCardapio(), command.getIdCardapio()));

        itemCardapioRestauranteRepository.deletar(itemCadapioDeletar);
    }
}
