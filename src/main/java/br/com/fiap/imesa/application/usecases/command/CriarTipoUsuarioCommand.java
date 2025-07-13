package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;

@Builder
public class CriarTipoUsuarioCommand {

    private String nome;

    public CriarTipoUsuarioCommand(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
