package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.TipoCozinhaDtoRequest;
import br.com.fiap.imesa.application.usecases.command.AtualizarTipoCozinhaCommand;

public class AtualizaTipoCozinhaRequestMapper {

    public static AtualizarTipoCozinhaCommand dtoToCommand(Integer idTipoCozinha, TipoCozinhaDtoRequest tipoCozinhaDtoRequest){
        return AtualizarTipoCozinhaCommand.builder()
                .id(idTipoCozinha)
                .nome(tipoCozinhaDtoRequest.getNome())
                .build();
    }
}
