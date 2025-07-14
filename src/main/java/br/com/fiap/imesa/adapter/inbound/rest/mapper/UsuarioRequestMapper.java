package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.AtualizarUsuarioDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.LoginDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.UsuarioDtoRequest;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;

public class UsuarioRequestMapper {

    //Utilizado para construir o dominio a partir de um DTO
    public static Usuario criarUsuarioToDomain(UsuarioDtoRequest usuarioDtoRequest){

        var tipoUsuario = TipoUsuario.builder()
                .id(usuarioDtoRequest.getCodigoTipoUsuario())
                .build();

        return
                Usuario.builder()
                        .nome(usuarioDtoRequest.getNome())
                        .email(usuarioDtoRequest.getEmail())
                        .login(usuarioDtoRequest.getLogin())
                        .tipoUsuario(tipoUsuario)
                        .build();
    }

    public static Usuario consultarPorLoginToDomain(String login){

        return
                Usuario.builder()
                        .login(login)
                        .build();
    }

    public static Usuario consultarPorIdToDomain(Long id){

        return
                Usuario.builder()
                        .id(id)
                        .build();
    }

    public static Usuario atualizarUsuarioToDomain(Long userId, AtualizarUsuarioDtoRequest atualizarUsuarioRequest){

        return
                Usuario.builder()
                        .id(userId)
                        .nome(atualizarUsuarioRequest.getNome())
                        .email(atualizarUsuarioRequest.getEmail())
                        .login(atualizarUsuarioRequest.getLogin())
                        .build();
    }

    public static Usuario loginUsuarioToDomain(LoginDtoRequest loginDtoRequest){

        return
                Usuario.builder()
                        .login(loginDtoRequest.getLogin())
                        .password(loginDtoRequest.getPassword())
                        .build();
    }

    public static Usuario deleteToDomain(Long id){

        return
                Usuario.builder()
                        .id(id)
                        .build();
    }
}
