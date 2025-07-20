package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.mapper.UsuarioEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.UsuarioRepository;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorLoginRepository;
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
