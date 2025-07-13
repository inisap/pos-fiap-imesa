package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;

public interface IDeletaTipoCozinhaRepository {

    void deletar(TipoCozinha tipoCozinha);
}
