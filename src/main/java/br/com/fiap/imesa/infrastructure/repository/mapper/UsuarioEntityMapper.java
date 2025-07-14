package br.com.fiap.imesa.infrastructure.repository.mapper;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.infrastructure.repository.entity.TipoUsuarioEntity;
import br.com.fiap.imesa.infrastructure.repository.entity.UsuarioEntity;

public class UsuarioEntityMapper {

    public static Usuario toDomain(UsuarioEntity entity) {

        var tipo = TipoUsuario.builder()
                .id(entity.getTipoUsuario().getId())
                .nome(entity.getTipoUsuario().getDescricaoTipoUsuario())
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

        var tipoUsuario = TipoUsuarioEntity.builder()
                .id(usuario.getTipoUsuario().getId())
                .descricaoTipoUsuario(usuario.getTipoUsuario().getNome())
                .build();

        return UsuarioEntity.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .login(usuario.getLogin())
                .senhaHash(usuario.getPassword())
                .dataAlteracao(usuario.getDataAlteracao())
                .tipoUsuario(tipoUsuario)
                .build();
    }
}
