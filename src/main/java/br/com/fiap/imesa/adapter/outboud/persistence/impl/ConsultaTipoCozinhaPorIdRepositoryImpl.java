package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.IConsultaTipoCozinhaPorIdRepository;
import br.com.fiap.imesa.adapter.outboud.persistence.mapper.TipoCozinhaEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.TipoCozinhaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ConsultaTipoCozinhaPorIdRepositoryImpl implements IConsultaTipoCozinhaPorIdRepository {

    private final TipoCozinhaRepository tipoCozinhaRepository;

    public ConsultaTipoCozinhaPorIdRepositoryImpl(TipoCozinhaRepository tipoCozinhaRepository){
        this.tipoCozinhaRepository = tipoCozinhaRepository;
    }

    @Override
    public Optional<TipoCozinha> consultar(Integer idTipoCozinha) {

        var tipoCozinhaEntity = tipoCozinhaRepository.findById(idTipoCozinha);

        return tipoCozinhaEntity.map(TipoCozinhaEntityMapper::toDomain);
    }
}
