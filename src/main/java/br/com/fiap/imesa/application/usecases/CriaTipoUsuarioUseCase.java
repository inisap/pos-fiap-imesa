package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.TipoUsuarioJaExisteException;
import br.com.fiap.imesa.application.mapper.CriarTipoUsuarioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.CriarTipoUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.ITipoUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CriaTipoUsuarioUseCase {

    private final ITipoUsuarioRepository tipoUsuarioRepository;

    public CriaTipoUsuarioUseCase(ITipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public TipoUsuario run(CriarTipoUsuarioCommand command) {

        var tipoUsuario = CriarTipoUsuarioCommandMapper.commandToDomain(command);

        //validando se ja não existe
        if(tipoUsuarioRepository.consultarPorNome(tipoUsuario.getNome().toUpperCase())
                .isPresent()){
            throw new TipoUsuarioJaExisteException(null, tipoUsuario.getNome().toUpperCase());
        }

        return tipoUsuarioRepository.salvar(tipoUsuario);
    }
}
