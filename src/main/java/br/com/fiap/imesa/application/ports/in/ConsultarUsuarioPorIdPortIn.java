package br.com.fiap.imesa.application.ports.in;

import br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioId.dto.ConsultaUsuarioIdResponse;

public interface ConsultarUsuarioPorIdPortIn {

    ConsultaUsuarioIdResponse consultar(Long id);
}
