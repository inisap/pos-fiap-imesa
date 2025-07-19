package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.mapper.AtualizarTipoUsuarioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.AtualizarTipoUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.IConsultaTipoUsuarioPorIdRepository;
import br.com.fiap.imesa.domain.gateway.ISalvaTipoUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizaTipoUsuarioUseCase {

    private final ISalvaTipoUsuarioRepository tipoUsuarioRepository;
    private final IConsultaTipoUsuarioPorIdRepository consultaTipoUsuarioPorIdRepository;

    public AtualizaTipoUsuarioUseCase(ISalvaTipoUsuarioRepository tipoUsuarioRepository,
                                      IConsultaTipoUsuarioPorIdRepository consultaTipoUsuarioPorIdRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.consultaTipoUsuarioPorIdRepository = consultaTipoUsuarioPorIdRepository;
    }

    public TipoUsuario run(AtualizarTipoUsuarioCommand command) {

        //validando se existe o tipo na base a ser alterado
        consultaTipoUsuarioPorIdRepository.consultar(command.getId())
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(null, command.getId()));

        var tipoUsuario = AtualizarTipoUsuarioCommandMapper.commandToDomain(command);

        return tipoUsuarioRepository.salvar(tipoUsuario);
    }
}
