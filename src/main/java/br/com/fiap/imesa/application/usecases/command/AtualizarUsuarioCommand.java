package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AtualizarUsuarioCommand {

    private Long idUsuario;
    private String nome;
    private String email;
    private String login;


}
