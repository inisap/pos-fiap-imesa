package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.mapper.TipoUsuarioEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.TipoUsuarioRepository;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.ITipoUsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TipoUsuarioRepositoryJpaAdapter implements ITipoUsuarioRepository {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioRepositoryJpaAdapter(TipoUsuarioRepository tipoUsuarioRepository){
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    public Optional<TipoUsuario> consultarPorIdTipoUsuario(Integer idTipoUsuario) {

        var tipoUsuarioEntity = tipoUsuarioRepository.findById(idTipoUsuario);

        return tipoUsuarioEntity.map(TipoUsuarioEntityMapper::toDomain);
    }

    @Override
    public TipoUsuario salvar(TipoUsuario tipoUsuario) {

        var usuarioEntity = TipoUsuarioEntityMapper.toEntity(tipoUsuario);

        var retorno = tipoUsuarioRepository.save(usuarioEntity);

        return TipoUsuarioEntityMapper.toDomain(retorno);

    }

    @Override
    public List<TipoUsuario> consultarTodosTiposDeUsuario() {

        var tipoUsuarioEntityList = tipoUsuarioRepository.findAll();

        return TipoUsuarioEntityMapper.toDomain(tipoUsuarioEntityList);
    }

    @Override
    public void deletar(TipoUsuario tipoUsuario) {

        //mapper to entity
        var tipoUsuarioDeletarEntity = TipoUsuarioEntityMapper.toEntity(tipoUsuario);

        tipoUsuarioRepository.delete(tipoUsuarioDeletarEntity);

    }
}
