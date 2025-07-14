package br.com.fiap.imesa.application.mapper;

import br.com.fiap.imesa.application.usecases.command.GravarRestauranteCommand;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;

public class CriarRestauranteCommandMapper {

    public static Restaurante commandToDomain(GravarRestauranteCommand gravarRestauranteCommand){

        return Restaurante.builder()
                .nome(gravarRestauranteCommand.getNome()).build();
    }
}
