package br.com.fiap.imesa.application.core.usecases.AtualizarSenhaUsuario.dto;

public class AtualizarSenhaUsuarioRequest {

    private String senhaAntiga;
    private String senhaNova;
    private String confirmacaoSenhaNova;

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public void setSenhaAntiga(String senhaAntiga) {
        this.senhaAntiga = senhaAntiga;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }

    public String getConfirmacaoSenhaNova() {
        return confirmacaoSenhaNova;
    }

    public void setConfirmacaoSenhaNOva(String confirmacaoSenhaNOva) {
        this.confirmacaoSenhaNova = confirmacaoSenhaNOva;
    }
}
