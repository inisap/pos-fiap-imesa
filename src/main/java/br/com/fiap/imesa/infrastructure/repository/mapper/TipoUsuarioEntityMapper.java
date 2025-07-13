package br.com.fiap.imesa.infrastructure.repository.mapper;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.infrastructure.repository.entity.TipoUsuarioEntity;
import br.com.fiap.imesa.infrastructure.repository.entity.UsuarioEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TipoUsuarioEntityMapper {

    public static TipoUsuario toDomain(TipoUsuarioEntity entity) {

        return TipoUsuario.builder()
                        .id(entity.getId())
                        .nome(entity.getDescricaoTipoUsuario())
                        .build();
    }

    public static TipoUsuarioEntity toEntity(TipoUsuario tipoUsuario) {

        return TipoUsuarioEntity.builder()
                .id(tipoUsuario.getId())
                .descricaoTipoUsuario(tipoUsuario.getNome())
                .build();
    }

    public static List<TipoUsuario> toDomain(List<TipoUsuarioEntity> entities) {

        return entities.stream()
                .map(TipoUsuarioEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
