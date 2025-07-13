package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.IConsultaTipoUsuarioPorIdRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaTodosTipoUsuarioRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.TipoUsuarioEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.mapper.UsuarioEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.TipoUsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
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
