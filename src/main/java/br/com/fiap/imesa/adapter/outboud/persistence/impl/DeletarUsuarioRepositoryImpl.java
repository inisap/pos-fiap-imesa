package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.springdata.UsuarioRepository;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IDeletaUsuarioRepository;
import br.com.fiap.imesa.adapter.outboud.persistence.mapper.UsuarioEntityMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DeletarUsuarioRepositoryImpl implements IDeletaUsuarioRepository {

    private final UsuarioRepository usuarioRepository;

    public DeletarUsuarioRepositoryImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void deletar(Usuario usuario) {

        //mapper to entity
        var usuarioDeletarEntity = UsuarioEntityMapper.toEntity(usuario);

        usuarioRepository.delete(usuarioDeletarEntity);

    }
}
