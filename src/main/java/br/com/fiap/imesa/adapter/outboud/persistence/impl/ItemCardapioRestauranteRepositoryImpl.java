package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.imesa.domain.gateway.IItemCardapioRestauranteRepository;
import br.com.fiap.imesa.adapter.outboud.persistence.mapper.ItemCardapioEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.ItemCardapioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemCardapioRestauranteRepositoryImpl implements IItemCardapioRestauranteRepository {

    private final ItemCardapioRepository itemCardapioRestauranteRepository;

    public ItemCardapioRestauranteRepositoryImpl(ItemCardapioRepository itemCardapioRestauranteRepository){
        this.itemCardapioRestauranteRepository = itemCardapioRestauranteRepository;
    }

    @Override
    public ItemCardapio salvar(ItemCardapio itemCardapio) {

        var itemCardapioEntity = ItemCardapioEntityMapper.toEntity(itemCardapio);

        var retorno = itemCardapioRestauranteRepository.save(itemCardapioEntity);

        return ItemCardapioEntityMapper.toDomain(retorno);

    }

    @Override
    public Optional<ItemCardapio> consultar(Long id) {

        var cardapioEntity = itemCardapioRestauranteRepository.findById(id);

        return cardapioEntity.map(ItemCardapioEntityMapper::toDomain);
    }

    @Override
    public Page<ItemCardapio> listarItensCardapio(Long id, Pageable pageable) {
        var entities = itemCardapioRestauranteRepository.findByCardapioEntity_Id(id, pageable);

        return entities.map(ItemCardapioEntityMapper::toDomain);

    }

    @Override
    public void deletar(ItemCardapio itemCardapio) {
        itemCardapioRestauranteRepository.deleteById(itemCardapio.getIdItemCardapio());
    }

    @Override
    public Optional<ItemCardapio> consultarPorIdItemCardapioEIdCardapio(ItemCardapio itemCardapio) {
        var itemCardapioEntity = itemCardapioRestauranteRepository.findByIdAndCardapioEntity_Id(
                itemCardapio.getIdItemCardapio(), itemCardapio.getCardapio().getCodigoCardapio());

        return itemCardapioEntity.map(ItemCardapioEntityMapper::toDomain);
    }


}
