package br.com.fiap.imesa.adapter.inbound.rest.presenter;

import br.com.fiap.imesa.adapter.inbound.rest.dto.RestauranteDtoResponse;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class ConsultarRestaurantePresenter {

    //utilizado apenas para construir o DTo de saida
    public static List<RestauranteDtoResponse> toDto(List<Restaurante> restauranteList){

        List<RestauranteDtoResponse> list = new ArrayList<>();

        for(Restaurante restaurante :  restauranteList){
            list.add(RestauranteDtoResponse.builder()
                    .nome(restaurante.getNome())
                    .tipoCozinha(restaurante.getTipoCozinha().getId())
                    .usuarioId(restaurante.getUsuarioProprietario().getId())
                    .build());
        }

        return list;
    }
}
