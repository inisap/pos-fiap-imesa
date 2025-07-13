package br.com.fiap.imesa.application.usecases.command;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GravarRestauranteCommand {

    private String nome;
    private Integer tipoCozinha;
    private List<HorarioFuncionamentoCommand> horarioFuncionamento;
    private Long usuarioId;
}
