package br.com.fiap.imesa.adapter.inbound.rest.presenter;

import br.com.fiap.imesa.adapter.inbound.rest.dto.EnderecoDtoResponse;
import br.com.fiap.imesa.domain.entities.endereco.Endereco;

public class EnderecoPresenter {

    //utilizado apenas para construir o DTo de saida
    public static EnderecoDtoResponse toDto(Endereco endereco){


        return EnderecoDtoResponse.builder()
                .id(endereco.getId())
                .usuarioId(endereco.getUsuarioId())
                .cep(endereco.getCep())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .build();
    }
}
