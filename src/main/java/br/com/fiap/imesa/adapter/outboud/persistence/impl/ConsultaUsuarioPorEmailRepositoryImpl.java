package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.springdata.UsuarioRepository;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorEmailRepository;
import br.com.fiap.imesa.adapter.outboud.persistence.mapper.UsuarioEntityMapper;
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
