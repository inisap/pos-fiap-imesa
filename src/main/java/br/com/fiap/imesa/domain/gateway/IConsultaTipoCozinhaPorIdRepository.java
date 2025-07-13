package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;

import java.util.Optional;

public interface IConsultaTipoCozinhaPorIdRepository {

    Optional<TipoCozinha> consultar(Integer id);
}
