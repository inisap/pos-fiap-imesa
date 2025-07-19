package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.AtualizarCardapioDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.CardapioDtoRequest;
import br.com.fiap.imesa.application.usecases.command.AtualizarCardapioCommand;
import br.com.fiap.imesa.application.usecases.command.CriarCardapioCommand;
import br.com.fiap.imesa.application.usecases.command.DeletarCardapioCommand;

public class CardapioRequestMapper {

    public static CriarCardapioCommand dtoToCommandCriar(CardapioDtoRequest cardapioDtoRequest){
        return CriarCardapioCommand.builder()
                .descricaoCardapio(cardapioDtoRequest.getDescricaoCardapio())
                .idRestaurante(cardapioDtoRequest.getIdRestaurante())
                .build();
    }

    public static AtualizarCardapioCommand dtoToCommandAtualizar(AtualizarCardapioDtoRequest command, Long idCardapio, Long idRestaurante){
        return AtualizarCardapioCommand.builder()
                .idCardapio(idCardapio)
                .descricaoCardapio(command.getDescricaoCardapio())
                .idRestaurante(idRestaurante)
                .build();
    }

    public static DeletarCardapioCommand dtoToCommandADeletar(Long idCardapio, Long idRestaurante){
        return DeletarCardapioCommand.builder()
                .idCardapio(idCardapio)
                .idRestaurante(idRestaurante)
                .build();
    }
}
