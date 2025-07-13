package br.com.fiap.imesa.domain.entities.cozinha;

import lombok.Builder;

@Builder
public class TipoCozinha {

    private Integer id;
    private String nome;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
