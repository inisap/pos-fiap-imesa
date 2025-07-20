package br.com.fiap.imesa.adapter.inbound.rest.controller;

import br.com.fiap.imesa.adapter.inbound.rest.dto.HorarioFuncionamentoDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.HorarioFuncionamentoDtoResponse;
import br.com.fiap.imesa.adapter.inbound.rest.mapper.HorarioFuncionamentoRequestMapper;
import br.com.fiap.imesa.adapter.inbound.rest.presenter.EnderecoPresenter;
import br.com.fiap.imesa.adapter.inbound.rest.presenter.HorarioFuncionamentoPresenter;
import br.com.fiap.imesa.application.usecases.ConsultaHorarioRestauranteUseCase;
import br.com.fiap.imesa.application.usecases.SalvaHorarioRestauranteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/restaurantes")
public class HorariosFuncionamentoController {

   private final SalvaHorarioRestauranteUseCase salvaHorarioRestauranteUseCase;
   private final ConsultaHorarioRestauranteUseCase consultaHorarioRestauranteUseCase;
    public HorariosFuncionamentoController(SalvaHorarioRestauranteUseCase salvaHorarioRestauranteUseCase,
                                           ConsultaHorarioRestauranteUseCase consultaHorarioRestauranteUseCase){
        this.salvaHorarioRestauranteUseCase = salvaHorarioRestauranteUseCase;
        this.consultaHorarioRestauranteUseCase = consultaHorarioRestauranteUseCase;
    }

    @Operation(description = "Endpoint responsavel por criar horarios de funcionamento para um restaurante")
    @PutMapping("/{restauranteId}/horarios-funcionamentos")
    public ResponseEntity<HorarioFuncionamentoDtoResponse> criaAtualizaHorarioRestaurante(
            @PathVariable("restauranteId") Long restauranteId,
            @RequestBody @Valid HorarioFuncionamentoDtoRequest horarioFuncionamentoDtoRequest
    ) {

        //convertendo o dto para command antes de enviar para o usecase
        var horarioFuncionamentoCommand = HorarioFuncionamentoRequestMapper.dtoToCommand(restauranteId, horarioFuncionamentoDtoRequest);

        //chamando o usecase passando o domain
        var horarioFuncionamentos = salvaHorarioRestauranteUseCase.run(horarioFuncionamentoCommand);

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.ok(HorarioFuncionamentoPresenter.toDto(horarioFuncionamentos));

    }

    @Operation(description = "Endpoint responsavel por consultar o horario de funcionamento de um restaurante")
    @GetMapping("/{restauranteId}/horarios-funcionamentos")
    public ResponseEntity<HorarioFuncionamentoDtoResponse> consultarHorarioRestaurante(
            @PathVariable("restauranteId") Long restauranteId
    ) {

        var horarioRestaurante = consultaHorarioRestauranteUseCase.run(restauranteId);

        return ResponseEntity.ok(HorarioFuncionamentoPresenter.toDto(horarioRestaurante));
    }

}
