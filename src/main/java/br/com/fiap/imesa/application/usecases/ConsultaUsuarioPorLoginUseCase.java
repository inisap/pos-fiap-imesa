package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.LoginNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultaUsuarioPorLoginUseCase {
    private final IUsuarioRepository usuarioRepository;

    public ConsultaUsuarioPorLoginUseCase(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario run(String login){

        var retornoUsuarioOpt = usuarioRepository.consultarPorLogin(login);

        return retornoUsuarioOpt
                .orElseThrow(() -> new LoginNaoEncontradoException(null, login));
    }
}
