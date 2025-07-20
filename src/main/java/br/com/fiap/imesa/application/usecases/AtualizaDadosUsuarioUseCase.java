package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.DuplicacaoEmailJaCadastradoException;
import br.com.fiap.imesa.application.exception.DuplicacaoLoginJaCadastradoException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.AtualizarUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AtualizaDadosUsuarioUseCase {
    private final IUsuarioRepository usuarioRepository;


    public AtualizaDadosUsuarioUseCase(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;

    }

    public Usuario run(AtualizarUsuarioCommand atualizarUsuarioCommand){

        Usuario usuario = buscaUsuarioExistente(atualizarUsuarioCommand.getIdUsuario());

        validaDuplicidade(atualizarUsuarioCommand);

        aplicarAtualizacoes(usuario, atualizarUsuarioCommand);

        return usuarioRepository.atualizar(usuario);
    }

    private Usuario buscaUsuarioExistente(Long idUsuario){
        return usuarioRepository.consultarPorIdUsuario(idUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(null, idUsuario));
    }

    private void aplicarAtualizacoes(Usuario usuario, AtualizarUsuarioCommand atualizarUsuarioCommand) {

        if (atualizarUsuarioCommand.getNome() != null) {
            usuario.setNome(atualizarUsuarioCommand.getNome());
            if (atualizarUsuarioCommand.getEmail() != null)
                usuario.setEmail(atualizarUsuarioCommand.getEmail());
            if (atualizarUsuarioCommand.getLogin() != null)
                usuario.setLogin(atualizarUsuarioCommand.getLogin());
        }
    }

    private void validaDuplicidade(AtualizarUsuarioCommand atualizarUsuarioCommand){
        var user1 = usuarioRepository.consultarPorLogin(atualizarUsuarioCommand.getLogin());

        if(user1.isPresent() && !Objects.equals(user1.get().getId(), atualizarUsuarioCommand.getIdUsuario())){
            throw new DuplicacaoLoginJaCadastradoException(null, atualizarUsuarioCommand.getLogin());
        }

        var user2 = usuarioRepository.consultarPorEmail(atualizarUsuarioCommand.getEmail());

        if(user2.isPresent() && !Objects.equals(user2.get().getId(), atualizarUsuarioCommand.getIdUsuario())){
            throw new DuplicacaoEmailJaCadastradoException(null, atualizarUsuarioCommand.getEmail());
        }
    }

}
