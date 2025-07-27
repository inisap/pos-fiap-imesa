package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.mapper.CardapioEntityMapper;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.gateway.ICardapioRestauranteRepository;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.CardapioRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CardapioRestauranteRepositoryJpaAdapter implements ICardapioRestauranteRepository {

    private final CardapioRepository cardapioRepository;

    public CardapioRestauranteRepositoryJpaAdapter(CardapioRepository cardapioRepository){
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    public Cardapio salvar(Cardapio cardapio) {

        var cardapioEntity = CardapioEntityMapper.toEntity(cardapio);

        var retorno = cardapioRepository.save(cardapioEntity);

        return CardapioEntityMapper.toDomain(retorno);

    }

    @Override
    public Optional<Cardapio> consultar(Long id) {

        var cardapioEntity = cardapioRepository.findById(id);

        return cardapioEntity.map(CardapioEntityMapper::toDomain);
    }

    @Override
    public void deletar(Cardapio cardapio) {
        cardapioRepository.deleteById(cardapio.getCodigoCardapio());
    }

    @Override
    public Optional<Cardapio> consultarPorIdCardapioEIdRestaurante(Cardapio cardapio) {
        var cardapioEntity = cardapioRepository.findByIdAndRestauranteEntity_Id(
                cardapio.getCodigoCardapio(), cardapio.getIdRestaurante().getId());

        return cardapioEntity.map(CardapioEntityMapper::toDomain);
    }


}
