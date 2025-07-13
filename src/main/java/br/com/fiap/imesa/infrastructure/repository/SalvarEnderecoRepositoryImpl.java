package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.Endereco;
import br.com.fiap.imesa.domain.gateway.ISalvaEnderecoRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.EnderecoEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.EnderecoRepository;
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
