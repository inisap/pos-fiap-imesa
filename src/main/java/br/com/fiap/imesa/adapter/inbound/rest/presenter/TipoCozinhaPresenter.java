package br.com.fiap.imesa.adapter.inbound.rest.presenter;

import br.com.fiap.imesa.adapter.inbound.rest.dto.TipoCozinhaDtoResponse;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;

public class TipoCozinhaPresenter {

    //utilizado apenas para construir o DTo de saida
    public static TipoCozinhaDtoResponse toDto(TipoCozinha tipoCozinha){


        return TipoCozinhaDtoResponse.builder()
                .id(tipoCozinha.getId())
                .nomeTipo(tipoCozinha.getNome())
                .build();
    }
}
