package br.com.fiap.imesa.adapter.inbound.rest.dto;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDtoRequest {

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
    @NotBlank(message = "não pode ser vazio")
    private TipoUsuario tipoUsuario;

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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
