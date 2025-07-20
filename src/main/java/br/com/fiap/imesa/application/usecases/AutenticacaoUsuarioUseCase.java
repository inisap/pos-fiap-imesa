package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.LoginInvalidoException;
import br.com.fiap.imesa.application.usecases.command.ValidarSenhaUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoUsuarioUseCase {
    private final IUsuarioRepository usuarioRepository;


    public AutenticacaoUsuarioUseCase(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario run(ValidarSenhaUsuarioCommand validarSenhaUsuarioCommand){

        var retornoUsuarioOpt = usuarioRepository.consultarPorLogin(validarSenhaUsuarioCommand.getLogin());

        Usuario retornoUsuario = retornoUsuarioOpt
                .orElseThrow(() -> new LoginInvalidoException("Usuário ou senha inválidos"));

        if (retornoUsuario.getPassword().equalsIgnoreCase(validarSenhaUsuarioCommand.getPassword())) {
            return retornoUsuarioOpt.get();
        } else {
            throw new LoginInvalidoException("Usuário ou senha inválidos");
        }
    }
}
