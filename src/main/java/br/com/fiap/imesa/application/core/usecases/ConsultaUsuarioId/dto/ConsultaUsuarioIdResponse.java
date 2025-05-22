package br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioId.dto;

import java.time.LocalDateTime;

public class ConsultaUsuarioIdResponse {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private LocalDateTime dataUltimaAlteracao;
    private String tipoUsuario;
    private ConsultaUsuarioIdEnderecoResponse endereco;

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

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public ConsultaUsuarioIdEnderecoResponse getEndereco() {
        return endereco;
    }

    public void setEndereco(ConsultaUsuarioIdEnderecoResponse endereco) {
        this.endereco = endereco;
    }
}
