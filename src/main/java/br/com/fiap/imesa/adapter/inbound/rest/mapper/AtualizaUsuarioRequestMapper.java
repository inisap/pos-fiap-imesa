package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.AtualizarUsuarioDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.UsuarioDtoRequest;
import br.com.fiap.imesa.application.usecases.command.AtualizarUsuarioCommand;
import br.com.fiap.imesa.application.usecases.command.CriarUsuarioCommand;

public class AtualizaUsuarioRequestMapper {

    public static AtualizarUsuarioCommand dtoToCommand(Long idUsuario, AtualizarUsuarioDtoRequest atualizarUsuarioRequest){
        return new AtualizarUsuarioCommand(
                idUsuario,
                atualizarUsuarioRequest.getNome(),
                atualizarUsuarioRequest.getEmail(),
                atualizarUsuarioRequest.getLogin()
        );
    }
}
