package br.com.fiap.imesa.adapter.inbound.rest.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TipoUsuarioDtoResponse {
    private Integer codigoTipoUsuario;
    private String descricaoTipoUsuario;

}
