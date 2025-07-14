package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.RestauranteDtoRequest;
import br.com.fiap.imesa.application.usecases.command.GravarRestauranteCommand;

public class CriaRestauranteRequestMapper {

    public static GravarRestauranteCommand dtoToCommand(RestauranteDtoRequest restauranteDtoRequest){

        return GravarRestauranteCommand
                .builder()
                .nome(restauranteDtoRequest.getNome())
                .tipoCozinha(restauranteDtoRequest.getTipoCozinha())
                .usuarioId(restauranteDtoRequest.getUsuarioId())
                .build();
    }
}
