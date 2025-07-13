package br.com.fiap.imesa.application.usecases.command;

public class AtualizarUsuarioCommand {

    private Long idUsuario;
    private String nome;
    private String email;
    private String login;

    public AtualizarUsuarioCommand(Long idUsuario, String nome, String email, String login) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.login = login;
    }

    public Long getIdUsuario() {
        return idUsuario;
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

}
