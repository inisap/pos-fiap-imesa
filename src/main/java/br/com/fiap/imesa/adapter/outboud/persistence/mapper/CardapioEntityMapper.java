package br.com.fiap.imesa.adapter.outboud.persistence.mapper;

import br.com.fiap.imesa.adapter.outboud.persistence.entity.CardapioEntity;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.adapter.outboud.persistence.entity.RestauranteEntity;

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
