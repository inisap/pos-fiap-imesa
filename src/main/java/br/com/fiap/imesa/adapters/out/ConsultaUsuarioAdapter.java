package br.com.fiap.imesa.adapters.out;

import br.com.fiap.imesa.adapters.out.repository.UsuarioRepository;
import br.com.fiap.imesa.adapters.out.repository.mapper.UsuarioEntityMapper;
import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.ports.out.ConsultaUsuarioPorIdOutport;
import br.com.fiap.imesa.application.ports.out.ConsultaUsuarioPorLoginOutport;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsultaUsuarioAdapter implements ConsultaUsuarioPorIdOutport, ConsultaUsuarioPorLoginOutport {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;

    public ConsultaUsuarioAdapter(UsuarioRepository usuarioRepository,
                                  UsuarioEntityMapper usuarioEntityMapper){
        this.usuarioRepository = usuarioRepository;
        this.usuarioEntityMapper = usuarioEntityMapper;
    }

    @Override
    public Optional<UsuarioDomain> consultarPorLogin(String login){
        return usuarioRepository.findByLogin(login)
                .map(usuarioEntityMapper::toUsuarioDomain);
    }

    @Override
    public Optional<UsuarioDomain> consultarPorId(Long id){
        return usuarioRepository.findById(id)
                .map(usuarioEntityMapper::toUsuarioDomain);
    }
}
