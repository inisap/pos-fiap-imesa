package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.Endereco;

public interface IDeletaEnderecoUsuarioRepository {

    void deletar(Endereco endereco);
}
