package br.com.fiap.imesa.infrastructure.repository.mapper;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.infrastructure.repository.entity.TipoCozinhaEntity;
import br.com.fiap.imesa.infrastructure.repository.entity.TipoUsuarioEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TipoCozinhaEntityMapper {

    public static TipoCozinha toDomain(TipoCozinhaEntity entity) {

        return TipoCozinha.builder()
                        .id(entity.getId())
                        .nome(entity.getDescricaoTipoCozinha())
                        .build();
    }

    public static TipoCozinhaEntity toEntity(TipoCozinha tipoCozinha) {

        return TipoCozinhaEntity.builder()
                .id(tipoCozinha.getId())
                .descricaoTipoCozinha(tipoCozinha.getNome())
                .build();
    }

    public static List<TipoCozinha> toDomain(List<TipoCozinhaEntity> entities) {

        return entities.stream()
                .map(TipoCozinhaEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
