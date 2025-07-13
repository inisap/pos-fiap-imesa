package br.com.fiap.imesa.adapter.inbound.rest.dto;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import lombok.Builder;

@Builder
public class LoginDtoResponse {

    private String nome;
    private String login;
    private TipoUsuario tipo;
}
