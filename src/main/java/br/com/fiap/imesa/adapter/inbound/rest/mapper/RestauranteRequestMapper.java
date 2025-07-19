package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.AtualizarRestauranteDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.RestauranteDtoRequest;
import br.com.fiap.imesa.application.usecases.command.AtualizarRestauranteCommand;
import br.com.fiap.imesa.application.usecases.command.GravarRestauranteCommand;

public class RestauranteRequestMapper {

    public static GravarRestauranteCommand dtoToCommandCriar(RestauranteDtoRequest restauranteDtoRequest){

        return GravarRestauranteCommand
                .builder()
                .nome(restauranteDtoRequest.getNome())
                .tipoCozinha(restauranteDtoRequest.getTipoCozinha())
                .usuarioId(restauranteDtoRequest.getUsuarioId())
                .build();
    }

    public static AtualizarRestauranteCommand dtoToCommandAtualizar(AtualizarRestauranteDtoRequest atualizarRestauranteDtoRequest, Long idRestaurante){

        return AtualizarRestauranteCommand
                .builder()
                .nome(atualizarRestauranteDtoRequest.getNome())
                .tipoCozinha(atualizarRestauranteDtoRequest.getTipoCozinha())
                .idRestaurante(idRestaurante)
                .build();
    }
}
