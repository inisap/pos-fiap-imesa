package br.com.fiap.imesa.adapter.inbound.rest.dto;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import lombok.Builder;

@Builder
public class UsuarioDtoResponse {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private TipoUsuario tipoUsuario;
}
