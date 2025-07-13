package br.com.fiap.imesa.adapter.inbound.rest.presenter;

import br.com.fiap.imesa.adapter.inbound.rest.dto.LoginDtoResponse;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;

public class AutenticacaoUsuarioPresenter {

    //utilizado apenas para construir o DTo de saida
    public static LoginDtoResponse toDto(Usuario usuario){

       return   LoginDtoResponse.builder()
               .nome(usuario.getNome())
               .login(usuario.getLogin())
               .tipo(usuario.getTipoUsuario())
               .build();
    }
}
