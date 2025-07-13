package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.Endereco;

public interface ISalvaEnderecoRepository {

    Endereco salvar(Endereco endereco);
}
