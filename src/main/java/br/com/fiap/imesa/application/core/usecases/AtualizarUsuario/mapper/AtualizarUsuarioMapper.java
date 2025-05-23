package br.com.fiap.imesa.application.core.usecases.AtualizarUsuario.mapper;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.core.usecases.AtualizarUsuario.dto.AtualizarUsuarioResponse;

public interface AtualizarUsuarioMapper {

    AtualizarUsuarioResponse toResponse(UsuarioDomain domain);
}
