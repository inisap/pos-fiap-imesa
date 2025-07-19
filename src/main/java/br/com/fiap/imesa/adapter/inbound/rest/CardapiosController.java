package br.com.fiap.imesa.adapter.inbound.rest;

import br.com.fiap.imesa.adapter.inbound.rest.dto.AtualizarCardapioDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.CardapioDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.CardapioDtoResponse;
import br.com.fiap.imesa.adapter.inbound.rest.mapper.CardapioRequestMapper;
import br.com.fiap.imesa.adapter.inbound.rest.presenter.CardapioPresenter;
import br.com.fiap.imesa.application.usecases.AtualizaCardapioRestauranteUseCase;
import br.com.fiap.imesa.application.usecases.ConsultaCardapioRestauranteUseCase;
import br.com.fiap.imesa.application.usecases.CriarCardapioRestauranteUseCase;
import br.com.fiap.imesa.application.usecases.DeletaCardapioRestauranteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/cardapios")
public class CardapiosController {

   private final CriarCardapioRestauranteUseCase criarCardapioRestauranteUseCase;
   private final AtualizaCardapioRestauranteUseCase atualizaCardapioRestauranteUseCase;
   private final ConsultaCardapioRestauranteUseCase consultaCardapioRestauranteUseCase;
   private final DeletaCardapioRestauranteUseCase deletaCardapioRestauranteUseCase;
    public CardapiosController(
            CriarCardapioRestauranteUseCase criarCardapioRestauranteUseCase,
                               AtualizaCardapioRestauranteUseCase atualizaCardapioRestauranteUseCase,
                               ConsultaCardapioRestauranteUseCase consultaCardapioRestauranteUseCase,
                               DeletaCardapioRestauranteUseCase deletaCardapioRestauranteUseCase){
        this.criarCardapioRestauranteUseCase = criarCardapioRestauranteUseCase;
        this.atualizaCardapioRestauranteUseCase = atualizaCardapioRestauranteUseCase;
        this.consultaCardapioRestauranteUseCase = consultaCardapioRestauranteUseCase;
        this.deletaCardapioRestauranteUseCase = deletaCardapioRestauranteUseCase;
    }

    @Operation(description = "Endpoint responsavel por criar um cardapio para um restaurante")
    @PostMapping()
    public ResponseEntity<CardapioDtoResponse> criaCardapioParaUmRestaurante(
            @RequestBody @Valid CardapioDtoRequest cardapioDtoRequest
    ) {

        //convertendo o dto para command antes de enviar para o usecase
        var criarCardapioCommand = CardapioRequestMapper.dtoToCommandCriar(cardapioDtoRequest);

        //chamando o usecase passando o domain
        var cardapio = criarCardapioRestauranteUseCase.run(criarCardapioCommand);

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.ok(CardapioPresenter.toDto(cardapio));

    }

    @Operation(description = "Endpoint responsavel por atualizar um cardapio para um restaurante")
    @PutMapping("/{cardapioId}/restaurantes/{restauranteId}")
    public ResponseEntity<CardapioDtoResponse> atualizaCardapioDoRestaurante(
            @PathVariable("cardapioId") Long cardapioId,
            @PathVariable("restauranteId") Long restauranteId,
            @RequestBody @Valid AtualizarCardapioDtoRequest atualizarCardapioDtoRequest
    ) {

        //convertendo o dto para command antes de enviar para o usecase
        var criarCardapioCommand = CardapioRequestMapper
                .dtoToCommandAtualizar(atualizarCardapioDtoRequest, cardapioId, restauranteId);

        //chamando o usecase passando o domain
        var cardapio = atualizaCardapioRestauranteUseCase.run(criarCardapioCommand);

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.ok(CardapioPresenter.toDto(cardapio));

    }

    @Operation(description = "Endpoint responsavel por consultar o cardapio de um restaurante")
    @GetMapping()
    public ResponseEntity<CardapioDtoResponse> consultarHorarioRestaurante(
            @Parameter(description = "idRestaurante para consulta de cardapio", required = true, in = ParameterIn.QUERY)
            @RequestParam(required = true) Long idRestaurante
    ) {

        //chamando o usecase passando o domain
        var cardapio = consultaCardapioRestauranteUseCase.run(idRestaurante);

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.ok(CardapioPresenter.toDto(cardapio));
    }

    @Operation(description = "Endpoint responsavel por Deletar um cardapio de um restaurante")
    @DeleteMapping("/{idCardapio}/restaurantes/{idRestaurante}")
    public ResponseEntity<Void> deletaCardapioRestaurante(
            @PathVariable("idCardapio") Long idCardapio,
            @PathVariable("idRestaurante") Long idRestaurante
    ) {
        //convertendo o dto para command antes de enviar para o usecase
        var deletarCardapioCommand = CardapioRequestMapper
                .dtoToCommandADeletar(idCardapio, idRestaurante);

        //chamando o usecase passando o domain
        deletaCardapioRestauranteUseCase.run(deletarCardapioCommand);

        return ResponseEntity.noContent().build();
    }

}
