package br.com.fiap.imesa.application.core.domain;

import java.time.LocalDateTime;

public class UsuarioDomain {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String password;
    private LocalDateTime dataAlteracao;
    private TipoUsuarioDomain tipoUsuario;
    private EnderecoDomain endereco;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public TipoUsuarioDomain getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarioDomain tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public EnderecoDomain getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDomain endereco) {
        this.endereco = endereco;
    }
}
