package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConsultarRestaurantesCommand {

    private String nome;
    private Integer codigoTipoCozinha;
}
