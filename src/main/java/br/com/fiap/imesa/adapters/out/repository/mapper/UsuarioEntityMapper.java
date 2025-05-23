package br.com.fiap.imesa.adapters.out.repository.mapper;

import br.com.fiap.imesa.adapters.out.repository.entity.UsuarioEntity;
import br.com.fiap.imesa.application.core.domain.UsuarioDomain;

public interface UsuarioEntityMapper {

    UsuarioDomain toUsuarioDomain(UsuarioEntity entity);

    UsuarioEntity toUsuarioEntity(UsuarioDomain domain);
}
