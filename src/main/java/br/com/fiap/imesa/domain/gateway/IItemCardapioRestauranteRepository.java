package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IItemCardapioRestauranteRepository {

    ItemCardapio salvar(ItemCardapio itemCardapio);
    Optional<ItemCardapio> consultar(Long id);

    Page<ItemCardapio> listarItensCardapio(Long id, Pageable pageable);
    void deletar(ItemCardapio itemCardapio);
    Optional<ItemCardapio> consultarPorIdItemCardapioEIdCardapio(ItemCardapio itemCardapio);
}
