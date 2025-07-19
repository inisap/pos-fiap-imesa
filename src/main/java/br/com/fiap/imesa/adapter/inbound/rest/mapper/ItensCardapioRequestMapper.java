package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.AtualizarCardapioDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.AtualizarItemCardapioDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.CardapioDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.CriarItemCardapioDtoRequest;
import br.com.fiap.imesa.application.usecases.command.*;

public class ItensCardapioRequestMapper {

    public static CriarItemCardapioCommand dtoToCommandCriar(CriarItemCardapioDtoRequest dto){
        return CriarItemCardapioCommand.builder()
                .idCardapio(dto.getIdCardapio())
                .nomePrato(dto.getNomePrato())
                .descricaoPrato(dto.getDescricaoPrato())
                .preco(dto.getPreco())
                .disponivelApenasLocal(dto.getDisponivelApenasLocal())
                .linkImagemPrato(dto.getLinkImagemPrato())
                .build();
    }

    public static AtualizarItemCardapioCommand dtoToCommandAtualizar(AtualizarItemCardapioDtoRequest command, Long idItemCardapio, Long idCardapio){
        return AtualizarItemCardapioCommand.builder()
                .idItem(idItemCardapio)
                .idCardapio(idCardapio)
                .nomePrato(command.getNomePrato())
                .descricaoPrato(command.getDescricaoPrato())
                .preco(command.getPreco())
                .disponivelApenasLocal(command.getDisponivelApenasLocal())
                .linkImagemPrato(command.getLinkImagemPrato())
                .build();
    }

    public static DeletarItemCardapioCommand dtoToCommandADeletar(Long idItemCardapio, Long idCardapio){
        return DeletarItemCardapioCommand.builder()
                .idItemCardapio(idItemCardapio)
                .idCardapio(idCardapio)
                .build();
    }
}
