package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.UsuarioDtoRequest;
import br.com.fiap.imesa.application.usecases.command.CriarUsuarioCommand;

public class CriaUsuarioRequestMapper {

    public static CriarUsuarioCommand dtoToCommand(UsuarioDtoRequest usuarioDtoRequest){
        return CriarUsuarioCommand.builder()
                .nome(usuarioDtoRequest.getNome())
                .email(usuarioDtoRequest.getEmail())
                .login(usuarioDtoRequest.getLogin())
                .password(usuarioDtoRequest.getPassword())
                .tipoUsuario(usuarioDtoRequest.getTipoUsuario())
                .build();


    }
}
