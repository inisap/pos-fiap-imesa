package br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioLogin.mapper;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioLogin.dto.ConsultaUsuarioLoginResponse;

public interface ConsultaUsuarioLoginUseCaseMapper {

    ConsultaUsuarioLoginResponse toResponse(UsuarioDomain usuario);
}
