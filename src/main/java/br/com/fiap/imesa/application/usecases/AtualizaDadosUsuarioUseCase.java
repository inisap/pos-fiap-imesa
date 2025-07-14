package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.DuplicacaoEmailJaCadastradoException;
import br.com.fiap.imesa.application.exception.DuplicacaoLoginJaCadastradoException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.AtualizarUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IAtualizaDadosUsuarioRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorEmailRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorIdRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorLoginRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AtualizaDadosUsuarioUseCase {
    private final IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository;

    private final IAtualizaDadosUsuarioRepository atualizaDadosUsuarioRepository;

    private final IConsultaUsuarioPorLoginRepository consultaUsuarioPorLoginRepository;

    private final IConsultaUsuarioPorEmailRepository consultaUsuarioPorEmailRepository;

    public AtualizaDadosUsuarioUseCase(IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository,
                                       IAtualizaDadosUsuarioRepository atualizaDadosUsuarioRepository,
                                       IConsultaUsuarioPorLoginRepository consultaUsuarioPorLoginRepository,
                                       IConsultaUsuarioPorEmailRepository consultaUsuarioPorEmailRepository) {
        this.consultaUsuarioPorIdRepository = consultaUsuarioPorIdRepository;
        this.atualizaDadosUsuarioRepository = atualizaDadosUsuarioRepository;
        this.consultaUsuarioPorLoginRepository = consultaUsuarioPorLoginRepository;
        this.consultaUsuarioPorEmailRepository = consultaUsuarioPorEmailRepository;
    }

    public Usuario run(AtualizarUsuarioCommand atualizarUsuarioCommand){

        Usuario usuario = buscaUsuarioExistente(atualizarUsuarioCommand.getIdUsuario());

        validaDuplicidade(atualizarUsuarioCommand);

        aplicarAtualizacoes(usuario, atualizarUsuarioCommand);

        return atualizaDadosUsuarioRepository.atualizar(usuario);
    }

    private Usuario buscaUsuarioExistente(Long idUsuario){
        return consultaUsuarioPorIdRepository.consultar(idUsuario)
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
        var user1 = consultaUsuarioPorLoginRepository.consultar(atualizarUsuarioCommand.getLogin());

        if(user1.isPresent() && !Objects.equals(user1.get().getId(), atualizarUsuarioCommand.getIdUsuario())){
            throw new DuplicacaoLoginJaCadastradoException(null, atualizarUsuarioCommand.getLogin());
        }

        var user2 = consultaUsuarioPorEmailRepository.consultar(atualizarUsuarioCommand.getEmail());

        if(user2.isPresent() && !Objects.equals(user2.get().getId(), atualizarUsuarioCommand.getIdUsuario())){
            throw new DuplicacaoEmailJaCadastradoException(null, atualizarUsuarioCommand.getEmail());
        }
    }

}
