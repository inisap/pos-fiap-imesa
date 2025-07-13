package br.com.fiap.imesa.application.mapper;

import br.com.fiap.imesa.application.usecases.command.CriarTipoUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;

public class CriarTipoUsuarioCommandMapper {

    public static TipoUsuario commandToDomain(CriarTipoUsuarioCommand command){
        return TipoUsuario.builder()
                .id(null)
                .nome(command.getNome())
                .build();
    }
}
