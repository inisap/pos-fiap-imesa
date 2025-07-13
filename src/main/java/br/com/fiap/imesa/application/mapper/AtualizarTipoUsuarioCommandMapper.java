package br.com.fiap.imesa.application.mapper;

import br.com.fiap.imesa.application.usecases.command.AtualizarTipoUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;

public class AtualizarTipoUsuarioCommandMapper {

    public static TipoUsuario commandToDomain(AtualizarTipoUsuarioCommand command){
        return TipoUsuario.builder()
                .id(command.getId())
                .nome(command.getNome())
                .build();
    }
}
