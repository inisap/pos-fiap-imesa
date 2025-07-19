package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.imesa.domain.gateway.IItemCardapioRestauranteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConsultaItensCardapioRestauranteUseCase {

    private final IItemCardapioRestauranteRepository itemCardapioRestauranteRepository;

    public ConsultaItensCardapioRestauranteUseCase(IItemCardapioRestauranteRepository itemCardapioRestauranteRepository) {
        this.itemCardapioRestauranteRepository = itemCardapioRestauranteRepository;
    }

    public Page<ItemCardapio> run(Long idCardapio, Pageable pageable) {

        return itemCardapioRestauranteRepository.listarItensCardapio(idCardapio, pageable);

    }
}
