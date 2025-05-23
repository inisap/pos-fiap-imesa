package br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioLogin.mapper;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioLogin.dto.ConsultaUsuarioLoginResponse;
import org.springframework.stereotype.Component;

@Component
public class ConsultaUsuarioLoginUseCaseMapperImpl implements ConsultaUsuarioLoginUseCaseMapper{

    public ConsultaUsuarioLoginResponse toResponse(UsuarioDomain usuario){

        ConsultaUsuarioLoginResponse consultaUsuarioLoginResponse = new ConsultaUsuarioLoginResponse();
        consultaUsuarioLoginResponse.setId(usuario.getId());
        consultaUsuarioLoginResponse.setNome(usuario.getNome());
        consultaUsuarioLoginResponse.setEmail(usuario.getEmail());
        consultaUsuarioLoginResponse.setLogin(usuario.getLogin());
        consultaUsuarioLoginResponse.setTipoUsuario(String.valueOf(usuario.getTipoUsuario()));

        return consultaUsuarioLoginResponse;

    }
}
