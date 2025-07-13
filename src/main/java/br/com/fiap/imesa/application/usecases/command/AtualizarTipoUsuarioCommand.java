package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AtualizarTipoUsuarioCommand {

    private Integer id;
    private String nome;

    public AtualizarTipoUsuarioCommand(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
