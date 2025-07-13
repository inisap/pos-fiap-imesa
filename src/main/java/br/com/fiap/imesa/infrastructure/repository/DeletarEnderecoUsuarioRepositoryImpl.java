package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.Endereco;
import br.com.fiap.imesa.domain.gateway.IDeletaEnderecoUsuarioRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.EnderecoEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.EnderecoRepository;
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
