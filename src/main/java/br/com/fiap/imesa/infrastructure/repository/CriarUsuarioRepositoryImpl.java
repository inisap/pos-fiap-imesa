package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.ICriaUsuarioRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.UsuarioEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.UsuarioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CriarUsuarioRepositoryImpl implements ICriaUsuarioRepository {

    private final UsuarioRepository usuarioRepository;

    public CriarUsuarioRepositoryImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario criar(Usuario usuario) {

        var usuarioEntity = UsuarioEntityMapper.toEntity(usuario);

        var retorno = usuarioRepository.save(usuarioEntity);

        return UsuarioEntityMapper.toDomain(retorno);

    }
}
