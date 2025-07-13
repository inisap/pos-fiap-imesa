package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.LoginDtoRequest;
import br.com.fiap.imesa.application.usecases.command.ValidarSenhaUsuarioCommand;

public class ValidarLoginUsuarioRequestMapper {

    public static ValidarSenhaUsuarioCommand dtoToCommand(LoginDtoRequest loginDtoRequest){
        return new ValidarSenhaUsuarioCommand(
                loginDtoRequest.getLogin(),
                loginDtoRequest.getPassword()
        );
    }
}
