package br.com.fiap.imesa.application.mapper;

import br.com.fiap.imesa.application.usecases.command.*;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;

public class ItemCardapioCommandMapper {

    public static ItemCardapio commandCriarToDomain(CriarItemCardapioCommand command){

        var cardapio = Cardapio.builder()
                .codigoCardapio(command.getIdCardapio())
                .build();

        return ItemCardapio.builder()
                .cardapio(cardapio)
                .nome(command.getNomePrato())
                .descricao(command.getDescricaoPrato())
                .preco(command.getPreco())
                .diponivelApenasLocalmente(command.getDisponivelApenasLocal())
                .linkImagemPrato(command.getLinkImagemPrato())
                .build();
    }

    public static ItemCardapio commandAtualizarToDomain(AtualizarItemCardapioCommand command){

        var cardapio = Cardapio.builder()
                .codigoCardapio(command.getIdCardapio())
                .build();

        return ItemCardapio.builder()
                .idItemCardapio(command.getIdItem())
                .cardapio(cardapio)
                .nome(command.getNomePrato())
                .descricao(command.getDescricaoPrato())
                .preco(command.getPreco())
                .diponivelApenasLocalmente(command.getDisponivelApenasLocal())
                .linkImagemPrato(command.getLinkImagemPrato())
                .build();
    }

    public static ItemCardapio commandDeletarToDomain(DeletarItemCardapioCommand command){

        var cardapio = Cardapio.builder()
                .codigoCardapio(command.getIdCardapio())
                .build();

        return ItemCardapio.builder()
                .idItemCardapio(command.getIdItemCardapio())
                .cardapio(cardapio)
                .build();
    }
}
