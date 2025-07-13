package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;

@Builder
public class CriarTipoCozinhaCommand {

    private String nome;

    public CriarTipoCozinhaCommand(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
