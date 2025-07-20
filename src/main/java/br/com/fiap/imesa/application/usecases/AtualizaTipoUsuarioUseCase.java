package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.mapper.AtualizarTipoUsuarioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.AtualizarTipoUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.ITipoUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizaTipoUsuarioUseCase {

    private final ITipoUsuarioRepository tipoUsuarioRepository;

    public AtualizaTipoUsuarioUseCase(ITipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public TipoUsuario run(AtualizarTipoUsuarioCommand command) {

        //validando se existe o tipo na base a ser alterado
        tipoUsuarioRepository.consultarPorIdTipoUsuario(command.getId())
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(null, command.getId()));

        var tipoUsuario = AtualizarTipoUsuarioCommandMapper.commandToDomain(command);

        return tipoUsuarioRepository.salvar(tipoUsuario);
    }
}
