package br.com.fiap.imesa.infrastructure.repository.mapper;

import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.infrastructure.repository.entity.CardapioEntity;
import br.com.fiap.imesa.infrastructure.repository.entity.HorarioFuncionamentoEntity;
import br.com.fiap.imesa.infrastructure.repository.entity.RestauranteEntity;

import java.util.ArrayList;
import java.util.List;

public class CardapioEntityMapper {

    public static Cardapio toDomain(CardapioEntity cardapioEntity) {

        var restaurante = Restaurante.builder()
                .id(cardapioEntity.getRestauranteEntity().getId())
                .build();

        return Cardapio.builder()
                .codigoCardapio(cardapioEntity.getId())
                .descricaoCardapio(cardapioEntity.getDescricaoCardapio())
                .idRestaurante(restaurante)
                .build();
    }

    public static CardapioEntity toEntity(Cardapio cardapio) {

        var restauranteEntity = RestauranteEntity.builder()
                .id(cardapio.getIdRestaurante().getId())
                .build();

        return CardapioEntity.builder()
                .id(cardapio.getCodigoCardapio())
                .descricaoCardapio(cardapio.getDescricaoCardapio())
                .restauranteEntity(restauranteEntity)
                .build();
    }
}
