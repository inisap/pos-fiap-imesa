package br.com.fiap.imesa.adapter.inbound.rest.presenter;

import br.com.fiap.imesa.adapter.inbound.rest.dto.UsuarioDtoResponse;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;

public class ConsultarUsuarioPorIdPresenter {

    //utilizado apenas para construir o DTo de saida
    public static UsuarioDtoResponse toDto(Usuario usuario){

        return UsuarioDtoResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .login(usuario.getLogin())
                .tipoUsuario(usuario.getTipoUsuario())
                .build();

    }
}
