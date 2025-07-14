package br.com.fiap.imesa.adapter.inbound.rest.presenter;

import br.com.fiap.imesa.adapter.inbound.rest.dto.RestauranteDtoResponse;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;

public class CriarRestaurantePresenter {

    //utilizado apenas para construir o DTo de saida
    public static RestauranteDtoResponse toDto(Restaurante restaurante){

        return RestauranteDtoResponse.builder()
                .nome(restaurante.getNome())
                .tipoCozinha(restaurante.getTipoCozinha().getId())
                .usuarioId(restaurante.getUsuarioProprietario().getId())
                .build();
    }
}
