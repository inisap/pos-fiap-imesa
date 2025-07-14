package br.com.fiap.imesa.adapter.inbound.rest.dto;

import jakarta.validation.Valid;
import lombok.Getter;

import java.util.List;

@Getter
public class HorarioFuncionamentoDtoRequest {

    @Valid
    private List<HorarioFuncionamentoDiarioDtoRequest> horariosFuncionamento;

}
