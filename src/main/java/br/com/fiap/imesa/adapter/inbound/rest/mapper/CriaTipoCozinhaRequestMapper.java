package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.TipoCozinhaDtoRequest;
import br.com.fiap.imesa.application.usecases.command.CriarTipoCozinhaCommand;
import br.com.fiap.imesa.application.usecases.command.CriarTipoUsuarioCommand;

public class CriaTipoCozinhaRequestMapper {

    public static CriarTipoCozinhaCommand dtoToCommand(TipoCozinhaDtoRequest tipoCozinhaDtoRequest){
        return CriarTipoCozinhaCommand.builder()
                .descricaoTipoCozinha(tipoCozinhaDtoRequest.getNome())
                .build();
    }
}
