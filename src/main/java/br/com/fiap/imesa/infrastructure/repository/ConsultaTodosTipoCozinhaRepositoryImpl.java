package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.IConsultaTodosTipoCozinhaRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.TipoCozinhaEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.TipoCozinhaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsultaTodosTipoCozinhaRepositoryImpl implements IConsultaTodosTipoCozinhaRepository {

    private final TipoCozinhaRepository tipoCozinhaRepository;

    public ConsultaTodosTipoCozinhaRepositoryImpl(TipoCozinhaRepository tipoCozinhaRepository){
        this.tipoCozinhaRepository = tipoCozinhaRepository;
    }

    @Override
    public List<TipoCozinha> consultar() {

        var tipoCozinhaEntityList = tipoCozinhaRepository.findAll();

        return TipoCozinhaEntityMapper.toDomain(tipoCozinhaEntityList);
    }
}
