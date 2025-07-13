package br.com.fiap.imesa.adapter.inbound.rest.dto;

import lombok.Builder;

@Builder
public class TipoCozinhaDtoResponse {
    private Integer id;
    private String nomeTipo;

    public TipoCozinhaDtoResponse(Integer id, String nomeTipo) {
        this.id = id;
        this.nomeTipo = nomeTipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }
}
