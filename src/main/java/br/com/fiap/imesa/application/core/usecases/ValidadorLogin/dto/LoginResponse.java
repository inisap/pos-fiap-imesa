package br.com.fiap.imesa.application.core.usecases.ValidadorLogin.dto;

import br.com.fiap.imesa.application.core.domain.TipoUsuarioDomain;

public class LoginResponse {

    private String nome;
    private String login;
    private TipoUsuarioDomain tipo;

    public LoginResponse(String nome, String login, TipoUsuarioDomain tipo) {
        this.nome = nome;
        this.login = login;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public TipoUsuarioDomain getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuarioDomain tipo) {
        this.tipo = tipo;
    }
}
