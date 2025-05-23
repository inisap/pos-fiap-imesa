package br.com.fiap.imesa.adapters.out;

import br.com.fiap.imesa.adapters.out.repository.UsuarioRepository;
import br.com.fiap.imesa.adapters.out.repository.mapper.UsuarioEntityMapper;
import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.ports.out.CriarUsuarioOutPort;
import org.springframework.stereotype.Component;

@Component
public class CriarUsuarioAdapter implements CriarUsuarioOutPort {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;

    public CriarUsuarioAdapter(UsuarioRepository usuarioRepository,
                                  UsuarioEntityMapper usuarioEntityMapper){
        this.usuarioRepository = usuarioRepository;
        this.usuarioEntityMapper = usuarioEntityMapper;
    }

    @Override
    public UsuarioDomain criarUsuario(UsuarioDomain usuarioDomain){

        var entity = usuarioEntityMapper.toUsuarioEntity(usuarioDomain);

        if (entity.getEndereco() != null) {
            entity.getEndereco().setUsuario(entity);
        }

        var retorno = usuarioRepository.save(entity);
        return usuarioEntityMapper.toUsuarioDomain(retorno);
    }
}
