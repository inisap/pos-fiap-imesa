package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ValidarSenhaUsuarioCommand {

    private String login;

    private String password;

}
