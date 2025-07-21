package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.LoginDtoRequest;
import br.com.fiap.imesa.application.usecases.command.ValidarSenhaUsuarioCommand;

public class ValidarLoginUsuarioRequestMapper {

    public static ValidarSenhaUsuarioCommand dtoToCommand(LoginDtoRequest loginDtoRequest){
        return ValidarSenhaUsuarioCommand.builder()
                .login(loginDtoRequest.getLogin())
                .password(loginDtoRequest.getPassword())
                .build();
    }
}
