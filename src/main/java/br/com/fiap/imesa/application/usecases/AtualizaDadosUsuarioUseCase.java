package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.AtualizarUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IAtualizaDadosUsuarioRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorIdRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizaDadosUsuarioUseCase {
    private final IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository;

    private final IAtualizaDadosUsuarioRepository atualizaDadosUsuarioRepository;

    public AtualizaDadosUsuarioUseCase(IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository,
                                       IAtualizaDadosUsuarioRepository atualizaDadosUsuarioRepository) {
        this.consultaUsuarioPorIdRepository = consultaUsuarioPorIdRepository;
        this.atualizaDadosUsuarioRepository = atualizaDadosUsuarioRepository;
    }

    public Usuario run(AtualizarUsuarioCommand atualizarUsuarioCommand){

        Usuario usuario = buscaUsuarioExistente(atualizarUsuarioCommand.getIdUsuario());

        aplicarAtualizacoes(usuario, atualizarUsuarioCommand);

        return atualizaDadosUsuarioRepository.atualizar(usuario);
    }

    private Usuario buscaUsuarioExistente(Long idUsuario){
        return consultaUsuarioPorIdRepository.consultar(idUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));
    }

    private void aplicarAtualizacoes(Usuario usuario, AtualizarUsuarioCommand atualizarUsuarioCommand){

        if(atualizarUsuarioCommand.getNome() != null)
            usuario.setNome(atualizarUsuarioCommand.getNome());
        if(atualizarUsuarioCommand.getEmail() != null)
            usuario.setEmail(atualizarUsuarioCommand.getEmail());
        if(atualizarUsuarioCommand.getLogin() != null)
            usuario.setLogin(atualizarUsuarioCommand.getLogin());
    }
}
