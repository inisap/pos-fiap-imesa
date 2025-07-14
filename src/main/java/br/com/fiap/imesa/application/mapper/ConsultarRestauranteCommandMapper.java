package br.com.fiap.imesa.application.mapper;

import br.com.fiap.imesa.application.usecases.command.ConsultarRestaurantesCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;

public class ConsultarRestauranteCommandMapper {

    public static Restaurante commandToDomain(ConsultarRestaurantesCommand command){

        var tipoCozinha =
                TipoCozinha.builder()
                .id(command.getCodigoTipoCozinha())
                .build();


        return Restaurante.builder()
                .tipoCozinha(tipoCozinha)
                .nome(command.getNome())
                .build();
    }
}
