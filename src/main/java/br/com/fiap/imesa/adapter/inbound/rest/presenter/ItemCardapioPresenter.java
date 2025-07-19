package br.com.fiap.imesa.adapter.inbound.rest.presenter;

import br.com.fiap.imesa.adapter.inbound.rest.dto.ItemCardapioDtoResponse;
import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;
import org.springframework.data.domain.Page;

public class ItemCardapioPresenter {

    //utilizado apenas para construir o DTo de saida
    public static ItemCardapioDtoResponse toDto(ItemCardapio dto){


        return ItemCardapioDtoResponse.builder()
                .idItem(dto.getIdItemCardapio())
                .idCardapio(dto.getCardapio().getCodigoCardapio())
                .nomePrato(dto.getNome())
                .descricaoPrato(dto.getDescricao())
                .preco(dto.getPreco())
                .disponivelApenasLocal(dto.isDiponivelApenasLocalmente())
                .linkImagemPrato(dto.getLinkImagemPrato())
                .build();
    }

    public static Page<ItemCardapioDtoResponse> toDto(Page<ItemCardapio> itemCardapioPage){

        return itemCardapioPage.map(itemCardapio ->
                ItemCardapioDtoResponse.builder()
                        .idItem(itemCardapio.getIdItemCardapio())
                        .idCardapio(itemCardapio.getCardapio().getCodigoCardapio())
                        .nomePrato(itemCardapio.getNome())
                        .descricaoPrato(itemCardapio.getDescricao())
                        .preco(itemCardapio.getPreco())
                        .disponivelApenasLocal(itemCardapio.isDiponivelApenasLocalmente())
                        .linkImagemPrato(itemCardapio.getLinkImagemPrato())
                        .build()
        );
    }
}
