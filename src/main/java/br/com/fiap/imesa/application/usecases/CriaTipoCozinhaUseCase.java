package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.mapper.CriarTipoCozinhaCommandMapper;
import br.com.fiap.imesa.application.usecases.command.CriarTipoCozinhaCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
import org.springframework.stereotype.Service;

@Service
public class CriaTipoCozinhaUseCase {

    private final ITipoCozinhaRepository tipoCozinhaRepository;

    public CriaTipoCozinhaUseCase(ITipoCozinhaRepository tipoCozinhaRepository) {
        this.tipoCozinhaRepository = tipoCozinhaRepository;
    }

    public TipoCozinha run(CriarTipoCozinhaCommand command) {

        var tipoCozinha = CriarTipoCozinhaCommandMapper.commandToDomain(command);

        return tipoCozinhaRepository.salvar(tipoCozinha);
    }
}
