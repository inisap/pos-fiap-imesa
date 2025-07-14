package br.com.fiap.imesa.adapter.inbound.rest.dto;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginDtoResponse {

    private String nome;
    private String login;
    private TipoUsuario tipo;
}
