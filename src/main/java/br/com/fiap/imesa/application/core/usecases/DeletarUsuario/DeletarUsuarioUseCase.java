package br.com.fiap.imesa.application.core.usecases.DeletarUsuario;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.core.domain.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.core.usecases.DeletarUsuario.dto.DeletarUsuarioRequest;
import br.com.fiap.imesa.application.ports.in.DeletarUsuarioPortIn;
import br.com.fiap.imesa.application.ports.out.ConsultaUsuarioPorIdOutport;
import br.com.fiap.imesa.application.ports.out.DeletarUsuarioOutport;
import org.springframework.stereotype.Service;

@Service
public class DeletarUsuarioUseCase implements DeletarUsuarioPortIn {

    private final ConsultaUsuarioPorIdOutport consultaUsuarioOutport;
    private final DeletarUsuarioOutport deletarUsuarioOutport;

    public DeletarUsuarioUseCase(ConsultaUsuarioPorIdOutport consultaUsuarioOutport,
                                 DeletarUsuarioOutport deletarUsuarioOutport){
        this.consultaUsuarioOutport = consultaUsuarioOutport;
        this.deletarUsuarioOutport = deletarUsuarioOutport;

    }

    @Override
    public void deletarUsuario(Long userId){

        var usuarioOptional = consultaUsuarioOutport.consultarPorId(userId);

        UsuarioDomain usuario = usuarioOptional
            .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));

        deletarUsuarioOutport.deletarUsuario(usuario);
    }

}
