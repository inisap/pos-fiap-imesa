package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.mapper.UsuarioEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.UsuarioRepository;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepositoryJpaAdapter implements IUsuarioRepository {

    private final UsuarioRepository usuarioRepository;

    public UsuarioRepositoryJpaAdapter(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario criar(Usuario usuario) {

        var usuarioEntity = UsuarioEntityMapper.toEntity(usuario);

        var retorno = usuarioRepository.save(usuarioEntity);

        return UsuarioEntityMapper.toDomain(retorno);

    }

    @Override
    public Usuario atualizar(Usuario usuario) {

        //to entity
        var entity = UsuarioEntityMapper.toEntity(usuario);

        var retornoUsuarioEntity = usuarioRepository.save(entity);

        //to domain
        return UsuarioEntityMapper.toDomain(retornoUsuarioEntity);
    }

    @Override
    public Optional<Usuario> consultarPorEmail(String email) {

        var usuarioEntity = usuarioRepository.findByEmail(email);

        return usuarioEntity.map(UsuarioEntityMapper::toDomain);

    }

    @Override
    public Optional<Usuario> consultarPorLogin(String login) {

        var usuarioEntity = usuarioRepository.findByLogin(login);

        return usuarioEntity.map(UsuarioEntityMapper::toDomain);

    }

    @Override
    public Optional<Usuario> consultarPorIdUsuario(Long id){

        var usuarioEntity = usuarioRepository.findById(id);

        return usuarioEntity.map(UsuarioEntityMapper::toDomain);
    }

    @Override
    public void deletar(Usuario usuario) {

        //mapper to entity
        var usuarioDeletarEntity = UsuarioEntityMapper.toEntity(usuario);

        usuarioRepository.delete(usuarioDeletarEntity);

    }
}
