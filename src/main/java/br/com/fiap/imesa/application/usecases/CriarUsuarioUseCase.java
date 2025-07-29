package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.DuplicacaoEmailJaCadastradoException;
import br.com.fiap.imesa.application.exception.DuplicacaoLoginJaCadastradoException;
import br.com.fiap.imesa.application.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.mapper.CriarUsuarioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.CriarUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.ITipoUsuarioRepository;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CriarUsuarioUseCase {

    private final IUsuarioRepository UsuarioRepository;
    private final ITipoUsuarioRepository tipoUsuarioRepository;

    public CriarUsuarioUseCase(IUsuarioRepository UsuarioRepository,
                               ITipoUsuarioRepository tipoUsuarioRepository) {
        this.UsuarioRepository = UsuarioRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public Usuario run(CriarUsuarioCommand command){

        if (UsuarioRepository.consultarPorEmail(command.getEmail()).isPresent()) {
            throw new DuplicacaoEmailJaCadastradoException(null, command.getEmail());
        }

        if (UsuarioRepository.consultarPorLogin(command.getLogin()).isPresent()) {
            throw new DuplicacaoLoginJaCadastradoException(null, command.getLogin());
        }

        var tipoUsuario = tipoUsuarioRepository
                .consultarPorNome(command.getTipoUsuario())
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(null, command.getTipoUsuario()));

        var usuario = CriarUsuarioCommandMapper.commandToDomain(command);

        usuario.setTipoUsuario(tipoUsuario);

        usuario.setId(null);
        usuario.setDataAlteracao(LocalDateTime.now());

        return UsuarioRepository.criar(usuario);
    }
}
