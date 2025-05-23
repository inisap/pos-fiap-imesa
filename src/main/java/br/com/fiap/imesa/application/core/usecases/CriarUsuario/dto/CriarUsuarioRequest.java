package br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CriarUsuarioRequest {

    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String nome;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String email;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String login;
    @NotNull(message = "é obrigatória")
    @NotBlank(message = "não pode ser vazio")
    private String password;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "precisa ser 'RESTAURANTE' ou 'CLIENTE'")
    private String tipoUsuario;

    @Valid
    @NotNull(message = "é obrigatório")
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
