package br.com.fiap.imesa.adapter.inbound.rest.dto;

import lombok.Builder;

@Builder
public class EnderecoDtoRequest {
    private Long usuarioId;
    private String logradouro;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;
    private String bairro;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }
}
