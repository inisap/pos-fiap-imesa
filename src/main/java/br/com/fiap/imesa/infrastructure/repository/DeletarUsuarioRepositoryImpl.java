package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IDeletaUsuarioRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.UsuarioEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.UsuarioRepository;
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
