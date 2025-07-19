package br.com.fiap.imesa.application.mapper;

import br.com.fiap.imesa.application.usecases.command.AtualizarRestauranteCommand;
import br.com.fiap.imesa.application.usecases.command.GravarRestauranteCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;

public class RestauranteCommandMapper {

    public static Restaurante commandToDomainCriar(GravarRestauranteCommand gravarRestauranteCommand){

        return Restaurante.builder()
                .nome(gravarRestauranteCommand.getNome()).build();
    }

    public static Restaurante commandToDomainAtualizar(AtualizarRestauranteCommand command){

        var tipoCozinha = TipoCozinha.builder()
                .id(command.getTipoCozinha())
                .build();

        return Restaurante.builder()
                .nome(command.getNome())
                .tipoCozinha(tipoCozinha)
                .build();
    }
}
