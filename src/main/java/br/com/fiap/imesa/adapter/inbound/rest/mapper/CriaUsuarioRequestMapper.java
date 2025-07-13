package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.UsuarioDtoRequest;
import br.com.fiap.imesa.application.usecases.command.CriarUsuarioCommand;

public class CriaUsuarioRequestMapper {

    public static CriarUsuarioCommand dtoToCommand(UsuarioDtoRequest usuarioDtoRequest){
        return new CriarUsuarioCommand(
                usuarioDtoRequest.getNome(),
                usuarioDtoRequest.getEmail(),
                usuarioDtoRequest.getLogin(),
                usuarioDtoRequest.getPassword(),
                usuarioDtoRequest.getTipoUsuario()
        );
    }
}
