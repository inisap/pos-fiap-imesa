package br.com.fiap.imesa.adapters.out;

import br.com.fiap.imesa.adapters.out.repository.UsuarioRepository;
import br.com.fiap.imesa.adapters.out.repository.mapper.UsuarioEntityMapper;
import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.ports.out.DeletarUsuarioOutport;
import org.springframework.stereotype.Component;

@Component
public class DeletarUsuarioAdapter implements DeletarUsuarioOutport {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;

    public DeletarUsuarioAdapter(UsuarioRepository usuarioRepository,
                                 UsuarioEntityMapper usuarioEntityMapper){
        this.usuarioRepository = usuarioRepository;
        this.usuarioEntityMapper = usuarioEntityMapper;
    }

    @Override
    public void deletarUsuario(UsuarioDomain usuarioDomain){

        var entity = usuarioEntityMapper.toUsuarioEntity(usuarioDomain);
        usuarioRepository.delete(entity);
    }
}
