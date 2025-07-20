package br.com.fiap.imesa.adapter.outboud.persistence.mapper;

import br.com.fiap.imesa.adapter.outboud.persistence.entity.EnderecoEntity;
import br.com.fiap.imesa.domain.entities.Endereco;

public class EnderecoEntityMapper {

    public static Endereco toDomain(EnderecoEntity enderecoEntity) {

        return Endereco.builder()
                .id(enderecoEntity.getId())
                .usuarioId(enderecoEntity.getUsuario())
                .cep(enderecoEntity.getCep())
                .logradouro(enderecoEntity.getLogradouro())
                .numero(enderecoEntity.getNumero())
                .complemento(enderecoEntity.getComplemento())
                .bairro(enderecoEntity.getBairro())
                .cidade(enderecoEntity.getCidade())
                .estado(enderecoEntity.getEstado())
                .build();
    }

    public static EnderecoEntity toEntity(Endereco endereco) {

        var idEndereco = endereco.getId() != null ? endereco.getId() : null;

        return EnderecoEntity.builder()
                .id(idEndereco)
                .usuario(endereco.getUsuarioId())
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
