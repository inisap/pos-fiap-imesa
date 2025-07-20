package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;

import java.util.List;
import java.util.Optional;

public interface ITipoCozinhaRepository {

    Optional<TipoCozinha> consultarPorIdTipoCozinha(Integer id);

    void deletar(TipoCozinha tipoCozinha);

    List<TipoCozinha> consultarTodosTiposDeCozinha();

    TipoCozinha salvar(TipoCozinha tipoCozinha);
}
