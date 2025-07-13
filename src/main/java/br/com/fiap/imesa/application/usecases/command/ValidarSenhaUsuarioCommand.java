package br.com.fiap.imesa.application.usecases.command;

public class ValidarSenhaUsuarioCommand {

    private String login;

    private String password;

    public ValidarSenhaUsuarioCommand(String login, String pass) {
        this.login = login;
        this.password = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
