package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.mapper.CriarTipoUsuarioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.CriarTipoUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.ISalvaTipoUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CriaTipoUsuarioUseCase {

    private final ISalvaTipoUsuarioRepository tipoUsuarioRepository;

    public CriaTipoUsuarioUseCase(ISalvaTipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public TipoUsuario run(CriarTipoUsuarioCommand command) {

        var tipoUsuario = CriarTipoUsuarioCommandMapper.commandToDomain(command);

        return tipoUsuarioRepository.salvar(tipoUsuario);
    }
}
