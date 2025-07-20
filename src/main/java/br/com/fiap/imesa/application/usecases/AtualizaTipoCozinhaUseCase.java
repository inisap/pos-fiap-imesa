package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.TipoCozinhaNaoEncontradoException;
import br.com.fiap.imesa.application.mapper.AtualizarTipoCozinhaCommandMapper;
import br.com.fiap.imesa.application.usecases.command.AtualizarTipoCozinhaCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizaTipoCozinhaUseCase {

    private final ITipoCozinhaRepository tipoCozinhaRepository;

    public AtualizaTipoCozinhaUseCase(ITipoCozinhaRepository tipoCozinhaRepository) {
        this.tipoCozinhaRepository = tipoCozinhaRepository;
    }

    public TipoCozinha run(AtualizarTipoCozinhaCommand command) {

        //validando se existe o tipo na base a ser alterado
        tipoCozinhaRepository.consultarPorIdTipoCozinha(command.getId())
                .orElseThrow(() -> new TipoCozinhaNaoEncontradoException(null, command.getId()));

        var tipoCozinha = AtualizarTipoCozinhaCommandMapper.commandToDomain(command);

        return tipoCozinhaRepository.salvar(tipoCozinha);
    }
}
