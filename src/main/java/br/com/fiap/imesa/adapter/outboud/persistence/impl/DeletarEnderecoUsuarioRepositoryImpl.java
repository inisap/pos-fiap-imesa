package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.mapper.EnderecoEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.EnderecoRepository;
import br.com.fiap.imesa.domain.entities.Endereco;
import br.com.fiap.imesa.domain.gateway.IDeletaEnderecoUsuarioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DeletarEnderecoUsuarioRepositoryImpl implements IDeletaEnderecoUsuarioRepository {

    private final EnderecoRepository enderecoRepository;

    public DeletarEnderecoUsuarioRepositoryImpl(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public void deletar(Endereco endereco) {

        //mapper to entity
        var enderecoDeletarEntity = EnderecoEntityMapper.toEntity(endereco);

        enderecoRepository.delete(enderecoDeletarEntity);

    }
}
