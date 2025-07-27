package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.endereco.Endereco;

import java.util.Optional;

public interface IEnderecoRepository {

    Endereco salvar(Endereco endereco);

    void deletar(Endereco endereco);

    Optional<Endereco> consultarPorIdDeUsuario(Long idUsuario);
}
