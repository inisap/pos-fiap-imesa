package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CriarCardapioCommand {

    private String descricaoCardapio;
    private Long idRestaurante;

}
