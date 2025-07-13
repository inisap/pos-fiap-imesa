package br.com.fiap.imesa.adapter.inbound.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TipoUsuarioDtoRequest {
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
