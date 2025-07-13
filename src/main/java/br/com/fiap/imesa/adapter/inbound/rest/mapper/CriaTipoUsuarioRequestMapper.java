package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.TipoUsuarioDtoRequest;
import br.com.fiap.imesa.application.usecases.command.CriarTipoUsuarioCommand;

public class CriaTipoUsuarioRequestMapper {

    public static CriarTipoUsuarioCommand dtoToCommand(TipoUsuarioDtoRequest tipoUsuarioDtoRequest){
        return CriarTipoUsuarioCommand.builder()
                .nome(tipoUsuarioDtoRequest.getNome())
                .build();
    }
}
