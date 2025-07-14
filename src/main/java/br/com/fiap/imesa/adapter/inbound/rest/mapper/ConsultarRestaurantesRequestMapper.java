package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.application.usecases.command.ConsultarRestaurantesCommand;

public class ConsultarRestaurantesRequestMapper {

    public static ConsultarRestaurantesCommand toCommand(Integer tipoCozinha, String nomeRestaurante){
        return ConsultarRestaurantesCommand.builder()
                .codigoTipoCozinha(tipoCozinha)
                .nome(nomeRestaurante)
                .build();
    }
}
