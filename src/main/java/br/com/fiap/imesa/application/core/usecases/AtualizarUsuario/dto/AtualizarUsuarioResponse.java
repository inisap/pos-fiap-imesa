package br.com.fiap.imesa.application.core.usecases.AtualizarUsuario.dto;

public class AtualizarUsuarioResponse {
    private String nome;
    private String email;
    private String login;
    private AtualizarUsuarioEnderecoResponse endereco;

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

    public AtualizarUsuarioEnderecoResponse getEndereco() {
        return endereco;
    }

    public void setEndereco(AtualizarUsuarioEnderecoResponse endereco) {
        this.endereco = endereco;
    }
}
