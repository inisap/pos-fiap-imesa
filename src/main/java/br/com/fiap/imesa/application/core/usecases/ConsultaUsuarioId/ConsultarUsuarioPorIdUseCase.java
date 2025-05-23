package br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioId;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.core.domain.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioId.dto.ConsultaUsuarioIdResponse;
import br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioId.mapper.ConsultaUsuarioIdUseCaseMapper;
import br.com.fiap.imesa.application.ports.in.ConsultarUsuarioPorIdPortIn;
import br.com.fiap.imesa.application.ports.out.ConsultaUsuarioPorIdOutport;
import org.springframework.stereotype.Service;

@Service
public class ConsultarUsuarioPorIdUseCase implements ConsultarUsuarioPorIdPortIn {

    private final ConsultaUsuarioPorIdOutport consultaUsuarioPorIdOutport;
    private final ConsultaUsuarioIdUseCaseMapper usuarioUseCaseMapper;

    public ConsultarUsuarioPorIdUseCase(ConsultaUsuarioPorIdOutport consultaUsuarioPorIdOutport,
                                        ConsultaUsuarioIdUseCaseMapper usuarioUseCaseMapper){
        this.consultaUsuarioPorIdOutport = consultaUsuarioPorIdOutport;
        this.usuarioUseCaseMapper = usuarioUseCaseMapper;
    }

    @Override
    public ConsultaUsuarioIdResponse consultar(Long id){

        var retornoUsuarioOpt = consultaUsuarioPorIdOutport.consultarPorId(id);

        UsuarioDomain retornoUsuario = retornoUsuarioOpt
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        return usuarioUseCaseMapper.toResponse(retornoUsuario);
    }
}
