package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AtualizaSenhaUsuarioCommand {

    private Long idUsuario;
    private String senhaAntiga;
    private String senhaNova;
    private String confirmacaoSenhaNova;
}
