package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AtualizarTipoCozinhaCommand {

    private Integer id;
    private String nome;

    public AtualizarTipoCozinhaCommand(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
