package br.com.fiap.imesa.adapter.inbound.rest.mapper;

import br.com.fiap.imesa.adapter.inbound.rest.dto.AtualizarSenhaUsuarioRequest;
import br.com.fiap.imesa.application.usecases.command.AtualizaSenhaUsuarioCommand;

public class AtualizaSenhaUsuarioRequestMapper {

    public static AtualizaSenhaUsuarioCommand dtoToCommand(Long idUsuario, AtualizarSenhaUsuarioRequest atualizarSenhaUsuarioRequest){
        return AtualizaSenhaUsuarioCommand.builder()
                .idUsuario(idUsuario)
                .senhaAntiga(atualizarSenhaUsuarioRequest.getSenhaAntiga())
                .senhaNova(atualizarSenhaUsuarioRequest.getSenhaNova())
                .confirmacaoSenhaNova(atualizarSenhaUsuarioRequest.getConfirmacaoSenhaNova())
                .build();
    }
}
