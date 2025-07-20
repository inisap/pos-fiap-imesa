package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
import br.com.fiap.imesa.adapter.outboud.persistence.mapper.TipoCozinhaEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.TipoCozinhaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TipoCozinhaRepositoryJpaAdapter implements ITipoCozinhaRepository {

    private final TipoCozinhaRepository tipoCozinhaRepository;

    public TipoCozinhaRepositoryJpaAdapter(TipoCozinhaRepository tipoCozinhaRepository){
        this.tipoCozinhaRepository = tipoCozinhaRepository;
    }

    @Override
    public Optional<TipoCozinha> consultarPorIdTipoCozinha(Integer idTipoCozinha) {

        var tipoCozinhaEntity = tipoCozinhaRepository.findById(idTipoCozinha);

        return tipoCozinhaEntity.map(TipoCozinhaEntityMapper::toDomain);
    }

    @Override
    public void deletar(TipoCozinha tipoCozinha) {

        //mapper to entity
        var tipoCozinhaDeletarEntity = TipoCozinhaEntityMapper.toEntity(tipoCozinha);

        tipoCozinhaRepository.delete(tipoCozinhaDeletarEntity);

    }

    @Override
    public List<TipoCozinha> consultarTodosTiposDeCozinha() {

        var tipoCozinhaEntityList = tipoCozinhaRepository.findAll();

        return TipoCozinhaEntityMapper.toDomain(tipoCozinhaEntityList);
    }

    @Override
    public TipoCozinha salvar(TipoCozinha tipoCozinha) {

        var usuarioEntity = TipoCozinhaEntityMapper.toEntity(tipoCozinha);

        var retorno = tipoCozinhaRepository.save(usuarioEntity);

        return TipoCozinhaEntityMapper.toDomain(retorno);

    }
}
