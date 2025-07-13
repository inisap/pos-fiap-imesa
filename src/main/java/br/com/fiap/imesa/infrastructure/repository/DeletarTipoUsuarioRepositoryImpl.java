package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IDeletaTipoUsuarioRepository;
import br.com.fiap.imesa.domain.gateway.IDeletaUsuarioRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.TipoUsuarioEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.mapper.UsuarioEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.TipoUsuarioRepository;
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
