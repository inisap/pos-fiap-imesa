package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorEmailRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.UsuarioEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ConsultaUsuarioPorEmailRepositoryImpl implements IConsultaUsuarioPorEmailRepository {

    private final UsuarioRepository usuarioRepository;

    public ConsultaUsuarioPorEmailRepositoryImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> consultar(String email) {

        var usuarioEntity = usuarioRepository.findByEmail(email);

        return usuarioEntity.map(UsuarioEntityMapper::toDomain);

    }
}
