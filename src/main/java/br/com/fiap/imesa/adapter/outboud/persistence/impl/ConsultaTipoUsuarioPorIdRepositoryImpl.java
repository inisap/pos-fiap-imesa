package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.mapper.TipoUsuarioEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.TipoUsuarioRepository;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.IConsultaTipoUsuarioPorIdRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ConsultaTipoUsuarioPorIdRepositoryImpl implements IConsultaTipoUsuarioPorIdRepository {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public ConsultaTipoUsuarioPorIdRepositoryImpl(TipoUsuarioRepository tipoUsuarioRepository){
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    public Optional<TipoUsuario> consultar(Integer idTipoUsuario) {

        var tipoUsuarioEntity = tipoUsuarioRepository.findById(idTipoUsuario);

        return tipoUsuarioEntity.map(TipoUsuarioEntityMapper::toDomain);
    }
}
