package br.com.fiap.imesa.application.usecases.command;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;

public class CriarUsuarioCommand {

    private String nome;
    private String email;
    private String login;
    private String password;
    private TipoUsuario tipoUsuario;

    public CriarUsuarioCommand(String nome, String email, String login, String password, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}
