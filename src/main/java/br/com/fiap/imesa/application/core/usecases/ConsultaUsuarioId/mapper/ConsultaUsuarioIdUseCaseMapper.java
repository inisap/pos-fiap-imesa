package br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioId.mapper;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioId.dto.ConsultaUsuarioIdResponse;

public interface ConsultaUsuarioIdUseCaseMapper {

    ConsultaUsuarioIdResponse toResponse(UsuarioDomain usuario);
}
