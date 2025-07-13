package br.com.fiap.imesa.infrastructure.repository.mapper;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.infrastructure.repository.entity.UsuarioEntity;

public class UsuarioEntityMapper {

    public static Usuario toDomain(UsuarioEntity entity) {

        var tipo = TipoUsuario.builder()
                .id(entity.getTipoUsuario().getId())
                .nome(entity.getNome())
                .build();


        return Usuario.builder()
                        .id(entity.getId())
                        .nome(entity.getNome())
                        .email(entity.getEmail())
                        .login(entity.getLogin())
                        .password(entity.getSenhaHash())
                        .dataAlteracao(entity.getDataAlteracao())
                        .tipoUsuario(tipo)
                        .build();
    }

    public static UsuarioEntity toEntity(Usuario usuario) {

        return UsuarioEntity.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .login(usuario.getLogin())
                .senhaHash(usuario.getPassword())
                .dataAlteracao(usuario.getDataAlteracao())
                .build();
    }
}
