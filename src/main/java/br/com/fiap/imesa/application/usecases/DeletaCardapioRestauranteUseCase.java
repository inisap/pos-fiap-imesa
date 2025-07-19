package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.CombinacaoCardarpioERestauranteNaoExisteException;
import br.com.fiap.imesa.application.mapper.CardapioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.DeletarCardapioCommand;
import br.com.fiap.imesa.domain.gateway.ICardapioRestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletaCardapioRestauranteUseCase {

    private final ICardapioRestauranteRepository cardapioRestauranteRepository;

    public DeletaCardapioRestauranteUseCase(ICardapioRestauranteRepository cardapioRestauranteRepository) {
        this.cardapioRestauranteRepository = cardapioRestauranteRepository;
    }

    public void run(DeletarCardapioCommand command) {

        //convertendo o command para domain
        var cardapioDeletar = CardapioCommandMapper.commandDeletarToDomain(command);

        //consultando se cardapio e id existem na combinacao para deletar
        var cadapioDeletar  = cardapioRestauranteRepository.consultarPorIdCardapioEIdRestaurante(cardapioDeletar)
                .orElseThrow(() -> new CombinacaoCardarpioERestauranteNaoExisteException(null, command.getIdCardapio(), command.getIdRestaurante()));

        cardapioRestauranteRepository.deletar(cadapioDeletar);
    }
}
