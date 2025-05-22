package br.com.fiap.imesa.application.core.usecases.CriarUsuario.mapper;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto.CriarUsuarioRequest;
import br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto.CriarUsuarioResponse;

public interface UsuarioMapper {

    UsuarioDomain toDomain(CriarUsuarioRequest request);

    CriarUsuarioResponse toResponse(UsuarioDomain domain);
}
