package br.com.fiap.imesa.domain.entities.horarioFuncionamento;

import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Builder
@Getter
@Setter
public class HorarioFuncionamento {

    private Long id;
    private Restaurante restauranteId;
    private Integer diaSemana;
    private LocalTime horaAbertura;
    private LocalTime horaFechamento;
    private Boolean flagDiaAberto;

}
