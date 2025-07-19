package br.com.fiap.imesa.application.mapper;

import br.com.fiap.imesa.application.usecases.command.AtualizarCardapioCommand;
import br.com.fiap.imesa.application.usecases.command.CriarCardapioCommand;
import br.com.fiap.imesa.application.usecases.command.DeletarCardapioCommand;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;

public class CardapioCommandMapper {

    public static Cardapio commandCriarToDomain(CriarCardapioCommand command){

        var restaurante = Restaurante.builder()
                .id(command.getIdRestaurante())
                .build();

        return Cardapio.builder()
                .descricaoCardapio(command.getDescricaoCardapio())
                .idRestaurante(restaurante)
                .build();
    }

    public static Cardapio commandAtualizarToDomain(AtualizarCardapioCommand command){

        var restaurante = Restaurante.builder()
                .id(command.getIdRestaurante())
                .build();

        return Cardapio.builder()
                .codigoCardapio(command.getIdCardapio())
                .descricaoCardapio(command.getDescricaoCardapio())
                .idRestaurante(restaurante)
                .build();
    }

    public static Cardapio commandDeletarToDomain(DeletarCardapioCommand command){

        var restaurante = Restaurante.builder()
                .id(command.getIdRestaurante())
                .build();

        return Cardapio.builder()
                .codigoCardapio(command.getIdCardapio())
                .idRestaurante(restaurante)
                .build();
    }
}
