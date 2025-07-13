package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.mapper.CriarTipoCozinhaCommandMapper;
import br.com.fiap.imesa.application.usecases.command.CriarTipoCozinhaCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.ISalvaTipoCozinhaRepository;
import org.springframework.stereotype.Service;

@Service
public class CriaTipoCozinhaUseCase {

    private final ISalvaTipoCozinhaRepository salvaTipoCozinhaRepository;

    public CriaTipoCozinhaUseCase(ISalvaTipoCozinhaRepository salvaTipoCozinhaRepository) {
        this.salvaTipoCozinhaRepository = salvaTipoCozinhaRepository;
    }

    public TipoCozinha run(CriarTipoCozinhaCommand command) {

        var tipoCozinha = CriarTipoCozinhaCommandMapper.commandToDomain(command);

        return salvaTipoCozinhaRepository.salvar(tipoCozinha);
    }
}
