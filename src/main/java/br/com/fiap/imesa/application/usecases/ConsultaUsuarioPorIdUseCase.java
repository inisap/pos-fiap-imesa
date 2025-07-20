package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultaUsuarioPorIdUseCase {
    private final IUsuarioRepository usuarioRepository;

    public ConsultaUsuarioPorIdUseCase(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario run(Long id){

        var retornoUsuarioOpt = usuarioRepository.consultarPorIdUsuario(id);

        return retornoUsuarioOpt
                .orElseThrow(() -> new UsuarioNaoEncontradoException(null, id));
    }
}
