package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.domain.entities.Endereco;
import br.com.fiap.imesa.domain.gateway.IConsultaEnderecoPorIdUsuarioRepository;
import br.com.fiap.imesa.adapter.outboud.persistence.mapper.EnderecoEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.EnderecoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ConsultaEnderecoPorIdRepositoryImpl implements IConsultaEnderecoPorIdUsuarioRepository {


    private final EnderecoRepository enderecoRepository;

    public ConsultaEnderecoPorIdRepositoryImpl(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }
    @Override
    public Optional<Endereco> consultar(Long id){

        var enderecoEntity = enderecoRepository.findById(id);

        return enderecoEntity.map(EnderecoEntityMapper::toDomain);
    }
}
