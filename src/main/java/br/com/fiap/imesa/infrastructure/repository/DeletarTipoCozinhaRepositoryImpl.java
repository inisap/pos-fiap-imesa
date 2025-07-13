package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.IDeletaTipoCozinhaRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.TipoCozinhaEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.TipoCozinhaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DeletarTipoCozinhaRepositoryImpl implements IDeletaTipoCozinhaRepository {

    private final TipoCozinhaRepository tipoCozinhaRepository;

    public DeletarTipoCozinhaRepositoryImpl(TipoCozinhaRepository tipoCozinhaRepository){
        this.tipoCozinhaRepository = tipoCozinhaRepository;
    }

    @Override
    public void deletar(TipoCozinha tipoCozinha) {

        //mapper to entity
        var tipoCozinhaDeletarEntity = TipoCozinhaEntityMapper.toEntity(tipoCozinha);

        tipoCozinhaRepository.delete(tipoCozinhaDeletarEntity);

    }
}
