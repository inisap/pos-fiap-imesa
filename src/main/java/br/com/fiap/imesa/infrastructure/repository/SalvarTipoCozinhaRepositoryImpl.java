package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.ISalvaTipoCozinhaRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.TipoCozinhaEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.TipoCozinhaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SalvarTipoCozinhaRepositoryImpl implements ISalvaTipoCozinhaRepository {

    private final TipoCozinhaRepository tipoCozinhaRepository;

    public SalvarTipoCozinhaRepositoryImpl(TipoCozinhaRepository tipoCozinhaRepository){
        this.tipoCozinhaRepository = tipoCozinhaRepository;
    }

    @Override
    public TipoCozinha salvar(TipoCozinha tipoCozinha) {

        var usuarioEntity = TipoCozinhaEntityMapper.toEntity(tipoCozinha);

        var retorno = tipoCozinhaRepository.save(usuarioEntity);

        return TipoCozinhaEntityMapper.toDomain(retorno);

    }
}
