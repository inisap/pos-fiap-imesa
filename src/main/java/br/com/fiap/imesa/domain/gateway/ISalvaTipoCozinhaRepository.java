package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;

public interface ISalvaTipoCozinhaRepository {

    TipoCozinha salvar(TipoCozinha tipoCozinha);
}
