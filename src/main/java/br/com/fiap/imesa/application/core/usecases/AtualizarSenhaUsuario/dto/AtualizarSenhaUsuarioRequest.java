package br.com.fiap.imesa.application.core.usecases.AtualizarSenhaUsuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AtualizarSenhaUsuarioRequest {

    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String senhaAntiga;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
    private String senhaNova;
    @NotNull(message = "é obrigatório")
    @NotBlank(message = "não pode ser vazio")
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
