package br.com.fiap.imesa.application.usecases.command;

import br.com.fiap.imesa.adapter.inbound.rest.dto.EnderecoDtoRequest;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GravarEnderecoCommand {

    private Long usuarioId;

    private EnderecoDtoRequest enderecoDtoRequest;
}
