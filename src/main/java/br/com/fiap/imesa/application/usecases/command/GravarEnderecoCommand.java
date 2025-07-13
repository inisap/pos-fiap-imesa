package br.com.fiap.imesa.application.usecases.command;

import br.com.fiap.imesa.adapter.inbound.rest.dto.EnderecoDtoRequest;

public class GravarEnderecoCommand {

    private Long usuarioId;

    private EnderecoDtoRequest enderecoDtoRequest;

    public GravarEnderecoCommand(Long usuarioId, EnderecoDtoRequest enderecoDtoRequest) {
        this.usuarioId = usuarioId;
        this.enderecoDtoRequest = enderecoDtoRequest;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public EnderecoDtoRequest getEnderecoDtoRequest() {
        return enderecoDtoRequest;
    }

    public void setEnderecoDtoRequest(EnderecoDtoRequest enderecoDtoRequest) {
        this.enderecoDtoRequest = enderecoDtoRequest;
    }
}
