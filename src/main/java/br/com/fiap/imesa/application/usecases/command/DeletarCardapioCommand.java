package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeletarCardapioCommand {

    private Long idCardapio;
    private Long idRestaurante;

}
