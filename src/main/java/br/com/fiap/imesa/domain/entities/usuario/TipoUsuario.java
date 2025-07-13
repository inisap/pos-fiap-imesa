package br.com.fiap.imesa.domain.entities.usuario;

import lombok.Builder;

@Builder
public class TipoUsuario {

    private Integer id;
    private String nome;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
