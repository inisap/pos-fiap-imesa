package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.mapper.AtualizarTipoCozinhaCommandMapper;
import br.com.fiap.imesa.application.usecases.command.AtualizarTipoCozinhaCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.IConsultaTipoCozinhaPorIdRepository;
import br.com.fiap.imesa.domain.gateway.ISalvaTipoCozinhaRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizaTipoCozinhaUseCase {

    private final ISalvaTipoCozinhaRepository tipoCozinhaRepository;
    private final IConsultaTipoCozinhaPorIdRepository consultaTipoCozinhaPorIdRepository;

    public AtualizaTipoCozinhaUseCase(ISalvaTipoCozinhaRepository tipoCozinhaRepository,
                                      IConsultaTipoCozinhaPorIdRepository consultaTipoCozinhaPorIdRepository) {
        this.tipoCozinhaRepository = tipoCozinhaRepository;
        this.consultaTipoCozinhaPorIdRepository = consultaTipoCozinhaPorIdRepository;
    }

    public TipoCozinha run(AtualizarTipoCozinhaCommand command) {

        //validando se existe o tipo na base a ser alterado
        consultaTipoCozinhaPorIdRepository.consultar(command.getId())
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException("Id do Tipo de Usuario nao encontrado"));

        var tipoCozinha = AtualizarTipoCozinhaCommandMapper.commandToDomain(command);

        return tipoCozinhaRepository.salvar(tipoCozinha);
    }
}
