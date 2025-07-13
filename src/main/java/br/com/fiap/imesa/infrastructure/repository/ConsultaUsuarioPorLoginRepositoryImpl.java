package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorEmailRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorLoginRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.UsuarioEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ConsultaUsuarioPorLoginRepositoryImpl implements IConsultaUsuarioPorLoginRepository {

    private final UsuarioRepository usuarioRepository;

    public ConsultaUsuarioPorLoginRepositoryImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> consultar(String login) {

        var usuarioEntity = usuarioRepository.findByLogin(login);

        return usuarioEntity.map(UsuarioEntityMapper::toDomain);

    }
}
