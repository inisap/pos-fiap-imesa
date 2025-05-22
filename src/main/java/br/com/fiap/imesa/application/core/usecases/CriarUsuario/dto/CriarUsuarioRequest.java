package br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto;

public class CriarUsuarioRequest {

    private String nome;
    private String email;
    private String login;
    private String password;
    private String tipoUsuario;
    private CriarUsuarioEnderecoRequest endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public CriarUsuarioEnderecoRequest getEndereco() {
        return endereco;
    }

    public void setEndereco(CriarUsuarioEnderecoRequest endereco) {
        this.endereco = endereco;
    }
}
