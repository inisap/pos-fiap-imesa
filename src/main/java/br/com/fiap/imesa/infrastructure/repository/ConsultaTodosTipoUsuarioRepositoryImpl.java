package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.IConsultaTodosTipoUsuarioRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.TipoUsuarioEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.TipoUsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsultaTodosTipoUsuarioRepositoryImpl implements IConsultaTodosTipoUsuarioRepository {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public ConsultaTodosTipoUsuarioRepositoryImpl(TipoUsuarioRepository tipoUsuarioRepository){
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    public List<TipoUsuario> consultar() {

        var tipoUsuarioEntityList = tipoUsuarioRepository.findAll();

        return TipoUsuarioEntityMapper.toDomain(tipoUsuarioEntityList);
    }
}
