package br.com.fiap.imesa.infrastructure.repository.mapper;

import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.infrastructure.repository.entity.CardapioEntity;
import br.com.fiap.imesa.infrastructure.repository.entity.ItemCardapioEntity;
import br.com.fiap.imesa.infrastructure.repository.entity.RestauranteEntity;

public class ItemCardapioEntityMapper {

    public static ItemCardapio toDomain(ItemCardapioEntity itemCardapioEntity) {

        var cardapio = Cardapio.builder()
                .codigoCardapio(itemCardapioEntity.getId())
                .build();

        return ItemCardapio.builder()
                .idItemCardapio(itemCardapioEntity.getId())
                .cardapio(cardapio)
                .nome(itemCardapioEntity.getNomePrato())
                .descricao(itemCardapioEntity.getDescricaoPrato())
                .preco(itemCardapioEntity.getPreco())
                .diponivelApenasLocalmente(itemCardapioEntity.getDisponivelApenasRestaurante())
                .linkImagemPrato(itemCardapioEntity.getLinkImagePrato())
                .build();
    }

    public static ItemCardapioEntity toEntity(ItemCardapio itemCardapio) {

        var cardapioENtity = CardapioEntity.builder()
                .id(itemCardapio.getCardapio().getCodigoCardapio())
                .build();

        return ItemCardapioEntity.builder()
                .id(itemCardapio.getIdItemCardapio())
                .cardapioEntity(cardapioENtity)
                .nomePrato(itemCardapio.getNome())
                .descricaoPrato(itemCardapio.getDescricao())
                .preco(itemCardapio.getPreco())
                .disponivelApenasRestaurante(itemCardapio.isDiponivelApenasLocalmente())
                .linkImagePrato(itemCardapio.getLinkImagemPrato())
                .build();
    }
}
