package br.com.fiap.imesa.application.core.usecases.ValidadorLogin;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.core.domain.exception.LoginInvalidoException;
import br.com.fiap.imesa.application.core.usecases.ValidadorLogin.dto.LoginRequest;
import br.com.fiap.imesa.application.core.usecases.ValidadorLogin.dto.LoginResponse;
import br.com.fiap.imesa.application.ports.in.ValidaLoginPortIn;
import br.com.fiap.imesa.application.ports.out.ConsultaUsuarioPorLoginOutport;
import org.springframework.stereotype.Service;

@Service
public class ValidarLoginUseCase implements ValidaLoginPortIn {

    private final ConsultaUsuarioPorLoginOutport consultaUsuarioPorLoginOutport;

    public ValidarLoginUseCase(ConsultaUsuarioPorLoginOutport consultaUsuarioPorLoginOutport){
        this.consultaUsuarioPorLoginOutport = consultaUsuarioPorLoginOutport;
    }

    @Override
    public LoginResponse validaLogin(LoginRequest loginRequest) {


        var retornoUsuarioOpt = consultaUsuarioPorLoginOutport.consultarPorLogin(loginRequest.getLogin());

        UsuarioDomain retornoUsuario = retornoUsuarioOpt
                .orElseThrow(() -> new LoginInvalidoException("Usuário ou senha inválidos"));

        if (retornoUsuario.getPassword().equalsIgnoreCase(loginRequest.getPassword())) {
            return new LoginResponse(retornoUsuario.getNome(), retornoUsuario.getLogin(), retornoUsuario.getTipoUsuario());
        } else {
            throw new LoginInvalidoException("Usuário ou senha inválidos");
        }

    }
}
