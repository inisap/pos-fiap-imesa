package br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioId.mapper;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioId.dto.ConsultaUsuarioIdEnderecoResponse;
import br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioId.dto.ConsultaUsuarioIdResponse;
import org.springframework.stereotype.Component;

@Component
public class ConsultaUsuarioIdUseCaseMapperImpl implements ConsultaUsuarioIdUseCaseMapper{


    @Override
    public ConsultaUsuarioIdResponse toResponse(UsuarioDomain usuario){

        ConsultaUsuarioIdResponse consultaUsuarioIdResponse = new ConsultaUsuarioIdResponse();
        consultaUsuarioIdResponse.setId(usuario.getId());
        consultaUsuarioIdResponse.setNome(usuario.getNome());
        consultaUsuarioIdResponse.setEmail(usuario.getEmail());
        consultaUsuarioIdResponse.setLogin(usuario.getLogin());
        consultaUsuarioIdResponse.setDataUltimaAlteracao(usuario.getDataAlteracao());
        consultaUsuarioIdResponse.setTipoUsuario(String.valueOf(usuario.getTipoUsuario()));

        ConsultaUsuarioIdEnderecoResponse consultaUsuarioIdEnderecoResponse = new ConsultaUsuarioIdEnderecoResponse();
        consultaUsuarioIdEnderecoResponse.setCep(usuario.getEndereco().getCep());
        consultaUsuarioIdEnderecoResponse.setLogradouro(usuario.getEndereco().getLogradouro());
        consultaUsuarioIdEnderecoResponse.setNumero(usuario.getEndereco().getNumero());
        consultaUsuarioIdEnderecoResponse.setComplemento(usuario.getEndereco().getComplemento());
        consultaUsuarioIdEnderecoResponse.setBairro(usuario.getEndereco().getBairro());
        consultaUsuarioIdEnderecoResponse.setCidade(usuario.getEndereco().getCidade());
        consultaUsuarioIdEnderecoResponse.setEstado(usuario.getEndereco().getEstado());

        consultaUsuarioIdResponse.setEndereco(consultaUsuarioIdEnderecoResponse);

        return consultaUsuarioIdResponse;

    }
}
