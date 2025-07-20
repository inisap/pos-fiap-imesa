package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.AlteracaoSenhaDivergenteException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.AtualizaSenhaUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizaSenhaUsuarioUseCase {
    private final IUsuarioRepository usuarioRepository;

    public AtualizaSenhaUsuarioUseCase(IUsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario run(AtualizaSenhaUsuarioCommand atualizaSenhaUsuarioCommand){

        var retornoUsuarioOpt = usuarioRepository.consultarPorIdUsuario(atualizaSenhaUsuarioCommand.getIdUsuario());

        if(retornoUsuarioOpt.isEmpty()){
            throw new UsuarioNaoEncontradoException(null, atualizaSenhaUsuarioCommand.getIdUsuario());
        }

        var usuarioSenhaAlterar = retornoUsuarioOpt.get();

        aplicaAtualizacao(usuarioSenhaAlterar, atualizaSenhaUsuarioCommand);

        return usuarioRepository.atualizar(usuarioSenhaAlterar);
    }

    private void aplicaAtualizacao(Usuario usuario, AtualizaSenhaUsuarioCommand atualizaSenhaUsuarioCommand){
        if(!atualizaSenhaUsuarioCommand.getSenhaNova()
                .equals(atualizaSenhaUsuarioCommand.getConfirmacaoSenhaNova())){
            throw new AlteracaoSenhaDivergenteException("As senha nova não coincide com a confirmação de senha");
        }

        if (!usuario.getPassword().equals(atualizaSenhaUsuarioCommand.getSenhaAntiga())) {
            throw new AlteracaoSenhaDivergenteException("A senha antiga não confere");
        }

        usuario.setPassword(atualizaSenhaUsuarioCommand.getSenhaNova());
    }
}
