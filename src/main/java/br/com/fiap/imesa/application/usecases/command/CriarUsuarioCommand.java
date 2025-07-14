package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CriarUsuarioCommand {

    private String nome;
    private String email;
    private String login;
    private String password;
    private Integer codigoTipoUsuario;

}
