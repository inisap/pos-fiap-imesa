package br.com.fiap.imesa.application.core.usecases.AtualizarSenhaUsuario;

import br.com.fiap.imesa.application.core.domain.exception.AlteracaoSenhaDivergenteException;
import br.com.fiap.imesa.application.core.domain.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.core.usecases.AtualizarSenhaUsuario.dto.AtualizarSenhaUsuarioRequest;
import br.com.fiap.imesa.application.ports.in.AtualizarSenhaUsuarioPortIn;
import br.com.fiap.imesa.application.ports.out.AtualizarUsuarioOutport;
import br.com.fiap.imesa.application.ports.out.ConsultaUsuarioPorIdOutport;
import org.springframework.stereotype.Service;

@Service
public class AtualizarSenhaUsuarioUseCase implements AtualizarSenhaUsuarioPortIn {

    private final ConsultaUsuarioPorIdOutport consultaUsuarioPorIdOutport;
    private final AtualizarUsuarioOutport atualizarUsuarioOutport;

    public AtualizarSenhaUsuarioUseCase(ConsultaUsuarioPorIdOutport consultaUsuarioPorIdOutport,
                                        AtualizarUsuarioOutport atualizarUsuarioOutport){
        this.consultaUsuarioPorIdOutport = consultaUsuarioPorIdOutport;
        this.atualizarUsuarioOutport = atualizarUsuarioOutport;
    }

    @Override
    public void atualizar(Long id, AtualizarSenhaUsuarioRequest atualizarSenhaUsuarioRequest){
        var usuarioDomainOptional = consultaUsuarioPorIdOutport.consultarPorId(id);

        var usuario = usuarioDomainOptional
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Id de usuario não encontrado"));

        if(!atualizarSenhaUsuarioRequest.getSenhaNova()
                .equals(atualizarSenhaUsuarioRequest.getConfirmacaoSenhaNova())){
            throw new AlteracaoSenhaDivergenteException("As senha nova não coincide com a confirmação de senha");
        }

        if (!usuario.getPassword().equals(atualizarSenhaUsuarioRequest.getSenhaAntiga())) {
            throw new AlteracaoSenhaDivergenteException("A senha antiga não confere");
        }

        usuario.setPassword(atualizarSenhaUsuarioRequest.getSenhaNova());

        atualizarUsuarioOutport.atualizar(usuario);

    }
}
