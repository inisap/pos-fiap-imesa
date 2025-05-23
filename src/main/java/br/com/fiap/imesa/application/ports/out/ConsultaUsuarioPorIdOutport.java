package br.com.fiap.imesa.application.ports.out;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;

import java.util.Optional;

public interface ConsultaUsuarioPorIdOutport {
    Optional<UsuarioDomain> consultarPorId(Long id);
}