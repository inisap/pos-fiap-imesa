package br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioLogin;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.core.domain.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioLogin.dto.ConsultaUsuarioLoginResponse;
import br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioLogin.mapper.ConsultaUsuarioLoginUseCaseMapper;
import br.com.fiap.imesa.application.ports.in.ConsultarUsuarioPorLoginPortIn;
import br.com.fiap.imesa.application.ports.out.ConsultaUsuarioPorLoginOutport;
import org.springframework.stereotype.Service;

@Service
public class ConsultarUsuarioLoginUseCase implements ConsultarUsuarioPorLoginPortIn {

    private final ConsultaUsuarioPorLoginOutport consultaUsuarioPorLoginOutport;
    private final ConsultaUsuarioLoginUseCaseMapper usuarioLoginUseCaseMapper;

    public ConsultarUsuarioLoginUseCase(ConsultaUsuarioPorLoginOutport consultaUsuarioPorLoginOutport,
                                        ConsultaUsuarioLoginUseCaseMapper usuarioLoginUseCaseMapper){
        this.consultaUsuarioPorLoginOutport = consultaUsuarioPorLoginOutport;
        this.usuarioLoginUseCaseMapper = usuarioLoginUseCaseMapper;
    }

    @Override
    public ConsultaUsuarioLoginResponse consutar(String login){

        var retornoUsuarioOpt = consultaUsuarioPorLoginOutport.consultarPorLogin(login);

        UsuarioDomain retornoUsuario = retornoUsuarioOpt
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        return usuarioLoginUseCaseMapper.toResponse(retornoUsuario);
    }
}
