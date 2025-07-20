package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.mapper.EnderecoEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.EnderecoRepository;
import br.com.fiap.imesa.domain.entities.Endereco;
import br.com.fiap.imesa.domain.gateway.ISalvaEnderecoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SalvarEnderecoRepositoryImpl implements ISalvaEnderecoRepository {

    private final EnderecoRepository enderecoRepository;

    public SalvarEnderecoRepositoryImpl(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public Endereco salvar(Endereco endereco) {

        var enderecoEntity = EnderecoEntityMapper.toEntity(endereco);

        var retorno = enderecoRepository.save(enderecoEntity);

        return EnderecoEntityMapper.toDomain(retorno);

    }
}
