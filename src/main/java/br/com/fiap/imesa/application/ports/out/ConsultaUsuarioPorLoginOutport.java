package br.com.fiap.imesa.application.ports.out;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;

import java.util.Optional;

public interface ConsultaUsuarioPorLoginOutport {
    Optional<UsuarioDomain> consultarPorLogin(String login);
}
