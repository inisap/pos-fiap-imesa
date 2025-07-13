package br.com.fiap.imesa.application.mapper;

import br.com.fiap.imesa.application.usecases.command.CriarUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;

public class CriarUsuarioCommandMapper {

    public static Usuario commandToDomain(CriarUsuarioCommand criarUsuarioCommand){
        return Usuario.builder()
                .nome(criarUsuarioCommand.getNome())
                .email(criarUsuarioCommand.getEmail())
                .login(criarUsuarioCommand.getLogin())
                .password(criarUsuarioCommand.getPassword())
                .tipoUsuario(criarUsuarioCommand.getTipoUsuario())
                .build();
    }
}
