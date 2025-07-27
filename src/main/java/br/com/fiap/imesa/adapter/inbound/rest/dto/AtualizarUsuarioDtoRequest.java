package br.com.fiap.imesa.adapter.inbound.rest.dto;

import lombok.Getter;

@Getter
public class AtualizarUsuarioDtoRequest {

    private String nome;
    private String email;
    private String login;
}
