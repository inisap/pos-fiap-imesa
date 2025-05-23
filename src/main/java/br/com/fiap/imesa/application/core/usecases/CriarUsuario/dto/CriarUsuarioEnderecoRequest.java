package br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CriarUsuarioEnderecoRequest {
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String logradouro;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String numero;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String cidade;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String estado;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String cep;
    private String complemento;
    private String bairro;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
