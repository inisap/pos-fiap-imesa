package br.com.fiap.imesa.adapter.inbound.rest.presenter;

import br.com.fiap.imesa.adapter.inbound.rest.dto.TipoCozinhaDtoResponse;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;

import java.util.ArrayList;
import java.util.List;

public class ConsultarTodosTiposCozinhaPresenter {

    //utilizado apenas para construir o DTo de saida
    public static List<TipoCozinhaDtoResponse> toDto(List<TipoCozinha> tiposCozinhaList) {
        List<TipoCozinhaDtoResponse> list = new ArrayList<>();

        for (TipoCozinha tipoUsuario : tiposCozinhaList){
            var tipoCozinhaDtoResponse = TipoCozinhaDtoResponse.builder()
                    .id(tipoUsuario.getId())
                    .nomeTipo(tipoUsuario.getNome())
                    .build();

            list.add(tipoCozinhaDtoResponse);
        }

        return list;
    }
}
