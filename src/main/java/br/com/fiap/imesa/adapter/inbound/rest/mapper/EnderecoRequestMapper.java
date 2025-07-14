package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.EnderecoDtoRequest;
import br.com.fiap.imesa.application.usecases.command.GravarEnderecoCommand;

public class EnderecoRequestMapper {

    public static GravarEnderecoCommand dtoToCommand(Long idUsuario, EnderecoDtoRequest enderecoDtoRequest){
        return GravarEnderecoCommand.builder()
                .usuarioId(idUsuario)
                .enderecoDtoRequest(enderecoDtoRequest)
                .build();
    }
}
