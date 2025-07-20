package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.mapper.UsuarioEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.UsuarioRepository;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IAtualizaDadosUsuarioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AtualizaDadosUsuarioRepositoryImpl implements IAtualizaDadosUsuarioRepository {

    private final UsuarioRepository usuarioRepository;

    public AtualizaDadosUsuarioRepositoryImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario atualizar(Usuario usuario) {

        //to entity
        var entity = UsuarioEntityMapper.toEntity(usuario);

        var retornoUsuarioEntity = usuarioRepository.save(entity);

        //to domain
        return UsuarioEntityMapper.toDomain(retornoUsuarioEntity);
    }
}
