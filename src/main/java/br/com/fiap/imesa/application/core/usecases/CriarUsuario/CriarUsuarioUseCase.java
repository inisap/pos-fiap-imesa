package br.com.fiap.imesa.application.core.usecases.CriarUsuario;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto.CriarUsuarioRequest;
import br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto.CriarUsuarioResponse;
import br.com.fiap.imesa.application.core.usecases.CriarUsuario.mapper.UsuarioMapper;
import br.com.fiap.imesa.application.ports.in.CriarUsuarioPortIn;
import br.com.fiap.imesa.application.ports.out.CriarUsuarioOutPort;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioUseCase implements CriarUsuarioPortIn {

    private final CriarUsuarioOutPort criarUsuarioOutPort;
    private final UsuarioMapper mapper;

    public CriarUsuarioUseCase(CriarUsuarioOutPort criarUsuarioOutPort,
                               UsuarioMapper mapper){
        this.criarUsuarioOutPort = criarUsuarioOutPort;
        this.mapper = mapper;

    }

    @Override
    public CriarUsuarioResponse criar(CriarUsuarioRequest criarUsuarioRequest){

        UsuarioDomain usuarioDomain = mapper.toDomain(criarUsuarioRequest);

        var retorno = criarUsuarioOutPort.criarUsuario(usuarioDomain);

        return mapper.toResponse(retorno);
    }
}
