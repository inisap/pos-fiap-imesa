package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class ValidarSenhaUsuarioCommand {

    private String login;

    private String password;

}
