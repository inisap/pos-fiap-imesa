package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.IDeletaTipoUsuarioRepository;
import br.com.fiap.imesa.adapter.outboud.persistence.mapper.TipoUsuarioEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.TipoUsuarioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DeletarTipoUsuarioRepositoryImpl implements IDeletaTipoUsuarioRepository {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public DeletarTipoUsuarioRepositoryImpl(TipoUsuarioRepository tipoUsuarioRepository){
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    public void deletar(TipoUsuario tipoUsuario) {

        //mapper to entity
        var tipoUsuarioDeletarEntity = TipoUsuarioEntityMapper.toEntity(tipoUsuario);

        tipoUsuarioRepository.delete(tipoUsuarioDeletarEntity);

    }
}
