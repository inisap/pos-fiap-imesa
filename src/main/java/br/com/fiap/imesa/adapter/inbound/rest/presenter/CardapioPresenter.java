package br.com.fiap.imesa.adapter.inbound.rest.presenter;

import br.com.fiap.imesa.adapter.inbound.rest.dto.CardapioDtoResponse;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;

public class CardapioPresenter {

    //utilizado apenas para construir o DTo de saida
    public static CardapioDtoResponse toDto(Cardapio dto){


        return CardapioDtoResponse.builder()
                .idCardapio(dto.getCodigoCardapio())
                .descricaoCardapio(dto.getDescricaoCardapio())
                .idRestaurante(dto.getIdRestaurante().getId())
                .build();
    }
}
