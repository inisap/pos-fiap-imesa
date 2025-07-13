package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorIdRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.UsuarioEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ConsultaUsuarioPorIdRepositoryImpl implements IConsultaUsuarioPorIdRepository {


    private final UsuarioRepository usuarioRepository;

    public ConsultaUsuarioPorIdRepositoryImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public Optional<Usuario> consultar(Long id){

        var usuarioEntity = usuarioRepository.findById(id);

        return usuarioEntity.map(UsuarioEntityMapper::toDomain);
    }
}
