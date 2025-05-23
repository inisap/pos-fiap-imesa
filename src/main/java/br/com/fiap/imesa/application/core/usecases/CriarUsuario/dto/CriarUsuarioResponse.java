package br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto;


public class CriarUsuarioResponse {
    private Long id;
    private String nome;
    private String email;
    private String login;
    private String tipoUsuario;
    private CriarUsuarioEnderecoResponse endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public CriarUsuarioEnderecoResponse getEndereco() {
        return endereco;
    }

    public void setEndereco(CriarUsuarioEnderecoResponse endereco) {
        this.endereco = endereco;
    }
}
