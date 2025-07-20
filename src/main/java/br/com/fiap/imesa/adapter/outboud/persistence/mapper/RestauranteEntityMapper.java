package br.com.fiap.imesa.adapter.outboud.persistence.mapper;

import br.com.fiap.imesa.adapter.outboud.persistence.entity.RestauranteEntity;
import br.com.fiap.imesa.adapter.outboud.persistence.entity.TipoCozinhaEntity;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.adapter.outboud.persistence.entity.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;

public class RestauranteEntityMapper {

    public static Restaurante toDomain(RestauranteEntity entity) {

        var tipo = TipoCozinha.builder()
                .id(entity.getTipoCozinha().getId())
                .nome(entity.getTipoCozinha().getDescricaoTipoCozinha())
                .build();

        List<HorarioFuncionamento> horaFuncList = new ArrayList<>();

        var usuario = Usuario.builder()
                .id(entity.getUsuario().getId())
                .build();

        return Restaurante.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .tipoCozinha(tipo)
                .usuarioProprietario(usuario)
                .build();
    }

    public static List<Restaurante> toListDomain(List<RestauranteEntity> entityList) {

        List<Restaurante> restauranteList = new ArrayList<>();

        for(RestauranteEntity entity : entityList) {

            var tipo = TipoCozinha.builder()
                    .id(entity.getTipoCozinha().getId())
                    .nome(entity.getTipoCozinha().getDescricaoTipoCozinha())
                    .build();

            var usuario = Usuario.builder()
                    .id(entity.getUsuario().getId())
                    .build();

            restauranteList.add(
                    Restaurante.builder()
                    .id(entity.getId())
                    .nome(entity.getNome())
                    .tipoCozinha(tipo)
                    .usuarioProprietario(usuario)
                    .build());
        }
        return restauranteList;
    }

    public static RestauranteEntity toEntity(Restaurante restaurante) {

        var usuario = UsuarioEntity.builder()
                .id(restaurante.getUsuarioProprietario().getId())
                .build();

        var tipoCozinha =
        TipoCozinhaEntity.builder()
                .id(restaurante.getTipoCozinha().getId())
                .descricaoTipoCozinha(restaurante.getTipoCozinha().getNome())
                .build();


        return RestauranteEntity.builder()
                .id(restaurante.getId())
                .nome(restaurante.getNome())
                .tipoCozinha(tipoCozinha)
                .usuario(usuario)
                .build();
    }
}
