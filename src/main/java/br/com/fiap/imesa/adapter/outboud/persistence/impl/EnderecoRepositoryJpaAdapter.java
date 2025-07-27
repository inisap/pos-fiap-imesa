package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.mapper.EnderecoEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.EnderecoRepository;
import br.com.fiap.imesa.domain.entities.endereco.Endereco;
import br.com.fiap.imesa.domain.gateway.IEnderecoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EnderecoRepositoryJpaAdapter implements IEnderecoRepository {

    private final EnderecoRepository enderecoRepository;

    public EnderecoRepositoryJpaAdapter(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public Endereco salvar(Endereco endereco) {

        var enderecoEntity = EnderecoEntityMapper.toEntity(endereco);

        var retorno = enderecoRepository.save(enderecoEntity);

        return EnderecoEntityMapper.toDomain(retorno);

    }

    @Override
    public Optional<Endereco> consultarPorIdDeUsuario(Long id){

        var enderecoEntity = enderecoRepository.findByUsuario(id);

        return enderecoEntity.map(EnderecoEntityMapper::toDomain);
    }

    @Override
    public void deletar(Endereco endereco) {

        //mapper to entity
        var enderecoDeletarEntity = EnderecoEntityMapper.toEntity(endereco);

        enderecoRepository.delete(enderecoDeletarEntity);

    }
}
