package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;

import java.util.Optional;

public interface ICardapioRestauranteRepository {

    Cardapio salvar(Cardapio cardapio);
    Optional<Cardapio> consultar(Long id);
    void deletar(Cardapio cardapio);
    Optional<Cardapio> consultarPorIdCardapioEIdRestaurante(Cardapio cardapio);
}
