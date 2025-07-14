package br.com.fiap.imesa.adapter.inbound.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TipoUsuarioDtoResponse {
    private Integer codigoTipoUsuario;
    private String descricaoTipoUsuario;

}
