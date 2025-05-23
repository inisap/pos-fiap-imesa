package br.com.fiap.imesa.application.core.usecases.CriarUsuario;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.core.domain.exception.ConflitoChaveUnicaException;
import br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto.CriarUsuarioRequest;
import br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto.CriarUsuarioResponse;
import br.com.fiap.imesa.application.core.usecases.CriarUsuario.mapper.UsuarioMapper;
import br.com.fiap.imesa.application.ports.in.CriarUsuarioPortIn;
import br.com.fiap.imesa.application.ports.out.ConsultaUsuarioPorEmailOutport;
import br.com.fiap.imesa.application.ports.out.ConsultaUsuarioPorLoginOutport;
import br.com.fiap.imesa.application.ports.out.CriarUsuarioOutPort;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioUseCase implements CriarUsuarioPortIn {

    private final CriarUsuarioOutPort criarUsuarioOutPort;
    private final ConsultaUsuarioPorEmailOutport consultaUsuarioPorEmailOutport;
    private final ConsultaUsuarioPorLoginOutport consultaUsuarioPorLoginOutport;
    private final UsuarioMapper mapper;

    public CriarUsuarioUseCase(CriarUsuarioOutPort criarUsuarioOutPort,
                               ConsultaUsuarioPorEmailOutport consultaUsuarioPorEmailOutport,
                               ConsultaUsuarioPorLoginOutport consultaUsuarioPorLoginOutport,
                               UsuarioMapper mapper){
        this.criarUsuarioOutPort = criarUsuarioOutPort;
        this.consultaUsuarioPorEmailOutport = consultaUsuarioPorEmailOutport;
        this.consultaUsuarioPorLoginOutport = consultaUsuarioPorLoginOutport;
        this.mapper = mapper;

    }

    @Override
    public CriarUsuarioResponse criar(CriarUsuarioRequest criarUsuarioRequest){

        UsuarioDomain usuarioDomain = mapper.toDomain(criarUsuarioRequest);

        if (consultaUsuarioPorEmailOutport.consultarPorEmail(usuarioDomain.getEmail()).isPresent()) {
            throw new ConflitoChaveUnicaException("Já existe um usuário com este e-mail.");
        }

        if (consultaUsuarioPorLoginOutport.consultarPorLogin(usuarioDomain.getLogin()).isPresent()) {
            throw new ConflitoChaveUnicaException("Já existe um usuário com este login.");
        }

        var retorno = criarUsuarioOutPort.criarUsuario(usuarioDomain);

        return mapper.toResponse(retorno);
    }
}
