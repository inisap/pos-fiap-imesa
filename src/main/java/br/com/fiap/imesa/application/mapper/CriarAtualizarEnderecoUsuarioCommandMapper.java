package br.com.fiap.imesa.application.mapper;

import br.com.fiap.imesa.application.usecases.command.GravarEnderecoCommand;
import br.com.fiap.imesa.domain.entities.Endereco;

public class CriarAtualizarEnderecoUsuarioCommandMapper {

    public static Endereco commandToDomain(GravarEnderecoCommand enderecoCommand){
        return Endereco.builder()
                .usuarioId(enderecoCommand.getUsuarioId())
                .cep(enderecoCommand.getEnderecoDtoRequest().getCep())
                .logradouro(enderecoCommand.getEnderecoDtoRequest().getLogradouro())
                .numero(enderecoCommand.getEnderecoDtoRequest().getNumero())
                .complemento(enderecoCommand.getEnderecoDtoRequest().getComplemento())
                .bairro(enderecoCommand.getEnderecoDtoRequest().getBairro())
                .cidade(enderecoCommand.getEnderecoDtoRequest().getCidade())
                .estado(enderecoCommand.getEnderecoDtoRequest().getEstado())
                .build();
    }
}
