package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.ISalvaTipoUsuarioRepository;
import br.com.fiap.imesa.adapter.outboud.persistence.mapper.TipoUsuarioEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.TipoUsuarioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SalvarTipoUsuarioRepositoryImpl implements ISalvaTipoUsuarioRepository {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public SalvarTipoUsuarioRepositoryImpl(TipoUsuarioRepository tipoUsuarioRepository){
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    public TipoUsuario salvar(TipoUsuario tipoUsuario) {

        var usuarioEntity = TipoUsuarioEntityMapper.toEntity(tipoUsuario);

        var retorno = tipoUsuarioRepository.save(usuarioEntity);

        return TipoUsuarioEntityMapper.toDomain(retorno);

    }
}
