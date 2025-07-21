package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.AtualizarUsuarioDtoRequest;
import br.com.fiap.imesa.application.usecases.command.AtualizarUsuarioCommand;

public class AtualizaUsuarioRequestMapper {

    public static AtualizarUsuarioCommand dtoToCommand(Long idUsuario, AtualizarUsuarioDtoRequest atualizarUsuarioRequest){
        return AtualizarUsuarioCommand.builder()
                .idUsuario(idUsuario)
                .nome(atualizarUsuarioRequest.getNome())
                .email(atualizarUsuarioRequest.getEmail())
                .login(atualizarUsuarioRequest.getLogin())
                .build();
    }
}
