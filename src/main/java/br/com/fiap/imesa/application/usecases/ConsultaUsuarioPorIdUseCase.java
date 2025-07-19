package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorIdRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultaUsuarioPorIdUseCase {
    private final IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository;

    public ConsultaUsuarioPorIdUseCase(IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository) {
        this.consultaUsuarioPorIdRepository = consultaUsuarioPorIdRepository;
    }

    public Usuario run(Long id){

        var retornoUsuarioOpt = consultaUsuarioPorIdRepository.consultar(id);

        return retornoUsuarioOpt
                .orElseThrow(() -> new UsuarioNaoEncontradoException(null, id));
    }
}
