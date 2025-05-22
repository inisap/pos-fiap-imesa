package br.com.fiap.imesa.application.ports.in;

import br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioLogin.dto.ConsultaUsuarioLoginResponse;

public interface ConsultarUsuarioPorLoginPortIn {

    ConsultaUsuarioLoginResponse consutar(String login);
}
