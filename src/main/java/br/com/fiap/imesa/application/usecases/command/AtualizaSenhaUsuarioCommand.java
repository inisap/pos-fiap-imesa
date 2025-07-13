package br.com.fiap.imesa.application.usecases.command;

public class AtualizaSenhaUsuarioCommand {

    private Long idUsuario;
    private String senhaAntiga;
    private String senhaNova;
    private String confirmacaoSenhaNova;

    public AtualizaSenhaUsuarioCommand(Long idUsuario, String senhaAntiga, String senhaNova, String confirmacaoSenhaNova) {
        this.idUsuario = idUsuario;
        this.senhaAntiga = senhaAntiga;
        this.senhaNova = senhaNova;
        this.confirmacaoSenhaNova = confirmacaoSenhaNova;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public String getConfirmacaoSenhaNova() {
        return confirmacaoSenhaNova;
    }
}
