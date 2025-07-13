package br.com.fiap.imesa.infrastructure.repository.mapper;

import br.com.fiap.imesa.domain.entities.Endereco;
import br.com.fiap.imesa.infrastructure.repository.entity.EnderecoEntity;

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

        return EnderecoEntity.builder()
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
