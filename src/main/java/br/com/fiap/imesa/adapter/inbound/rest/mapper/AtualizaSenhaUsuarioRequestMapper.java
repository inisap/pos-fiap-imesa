package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.AtualizarSenhaUsuarioRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.AtualizarUsuarioDtoRequest;
import br.com.fiap.imesa.application.usecases.command.AtualizaSenhaUsuarioCommand;
import br.com.fiap.imesa.application.usecases.command.AtualizarUsuarioCommand;

public class AtualizaSenhaUsuarioRequestMapper {

    public static AtualizaSenhaUsuarioCommand dtoToCommand(Long idUsuario, AtualizarSenhaUsuarioRequest atualizarSenhaUsuarioRequest){
        return new AtualizaSenhaUsuarioCommand(
                idUsuario,
                atualizarSenhaUsuarioRequest.getSenhaAntiga(),
                atualizarSenhaUsuarioRequest.getSenhaNova(),
                atualizarSenhaUsuarioRequest.getConfirmacaoSenhaNova()
        );
    }
}
