package br.com.fiap.imesa.adapter.inbound.rest.presenter;

import br.com.fiap.imesa.adapter.inbound.rest.dto.TipoUsuarioDtoResponse;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;

import java.util.ArrayList;
import java.util.List;

public class ConsultarTodosTiposUsuarioPresenter {

    //utilizado apenas para construir o DTo de saida
    public static List<TipoUsuarioDtoResponse> toDto(List<TipoUsuario> tiposUsuarioList) {
        List<TipoUsuarioDtoResponse> list = new ArrayList<>();

        for (TipoUsuario tipoUsuario : tiposUsuarioList){
            var tipoUsuarioDtoResponse = TipoUsuarioDtoResponse.builder()
                    .id(tipoUsuario.getId())
                    .nomeTipo(tipoUsuario.getNome())
                    .build();

            list.add(tipoUsuarioDtoResponse);
        }

        return list;
    }
}
