package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorLoginRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultaUsuarioPorLoginUseCase {
    private final IConsultaUsuarioPorLoginRepository consultaUsuarioPorLoginRepository;

    public ConsultaUsuarioPorLoginUseCase(IConsultaUsuarioPorLoginRepository consultaUsuarioPorLoginRepository) {
        this.consultaUsuarioPorLoginRepository = consultaUsuarioPorLoginRepository;
    }

    public Usuario run(String login){

        var retornoUsuarioOpt = consultaUsuarioPorLoginRepository.consultar(login);

        return retornoUsuarioOpt
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));
    }
}
