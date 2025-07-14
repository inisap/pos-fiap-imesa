package br.com.fiap.imesa.adapter.inbound.rest.presenter;

import br.com.fiap.imesa.adapter.inbound.rest.dto.TipoUsuarioDtoResponse;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;

public class TipoUsuarioPresenter {

    //utilizado apenas para construir o DTo de saida
    public static TipoUsuarioDtoResponse toDto(TipoUsuario tipoUsuario){


        return TipoUsuarioDtoResponse.builder()
                .codigoTipoUsuario(tipoUsuario.getId())
                .descricaoTipoUsuario(tipoUsuario.getNome())
                .build();
    }
}
