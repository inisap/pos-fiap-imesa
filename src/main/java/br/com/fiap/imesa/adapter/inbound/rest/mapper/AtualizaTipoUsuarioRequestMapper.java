package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.TipoUsuarioDtoRequest;
import br.com.fiap.imesa.application.usecases.command.AtualizarTipoUsuarioCommand;

public class AtualizaTipoUsuarioRequestMapper {

    public static AtualizarTipoUsuarioCommand dtoToCommand(Integer idTipoUsuario, TipoUsuarioDtoRequest tipoUsuarioDtoRequest){
        return AtualizarTipoUsuarioCommand.builder()
                .id(idTipoUsuario)
                .nome(tipoUsuarioDtoRequest.getNome())
                .build();
    }
}
