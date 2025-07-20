package br.com.fiap.imesa.adapter.inbound.rest.controller;

import br.com.fiap.imesa.adapter.inbound.rest.dto.AtualizarItemCardapioDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.CardapioDtoResponse;
import br.com.fiap.imesa.adapter.inbound.rest.dto.CriarItemCardapioDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.ItemCardapioDtoResponse;
import br.com.fiap.imesa.adapter.inbound.rest.mapper.CardapioRequestMapper;
import br.com.fiap.imesa.adapter.inbound.rest.mapper.ItensCardapioRequestMapper;
import br.com.fiap.imesa.adapter.inbound.rest.presenter.CardapioPresenter;
import br.com.fiap.imesa.adapter.inbound.rest.presenter.ItemCardapioPresenter;
import br.com.fiap.imesa.application.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/itensCardapio")
public class ItensCardapiosController {

   private final CriarItemCardapioRestauranteUseCase criarItemCardapioRestauranteUseCase;
   private final AtualizaItemCardapioRestauranteUseCase atualizaItemCardapioRestauranteUseCase;
   private final ConsultaItensCardapioRestauranteUseCase consultaItensCardapioRestauranteUseCase;
   private final DeletaItemCardapioRestauranteUseCase deletaItemCardapioRestauranteUseCase;
    public ItensCardapiosController(CriarItemCardapioRestauranteUseCase criarItemCardapioRestauranteUseCase,
                                    AtualizaItemCardapioRestauranteUseCase atualizaItemCardapioRestauranteUseCase,
                                    ConsultaItensCardapioRestauranteUseCase consultaItensCardapioRestauranteUseCase,
                                    DeletaItemCardapioRestauranteUseCase deletaItemCardapioRestauranteUseCase){
        this.criarItemCardapioRestauranteUseCase = criarItemCardapioRestauranteUseCase;
        this.atualizaItemCardapioRestauranteUseCase = atualizaItemCardapioRestauranteUseCase;
        this.consultaItensCardapioRestauranteUseCase = consultaItensCardapioRestauranteUseCase;
        this.deletaItemCardapioRestauranteUseCase = deletaItemCardapioRestauranteUseCase;
    }

    @Operation(description = "Endpoint responsavel por Criar um item para um cardápio")
    @PostMapping()
    public ResponseEntity<ItemCardapioDtoResponse> criaItemParaCardapioDeUmRestaurante(
            @RequestBody @Valid CriarItemCardapioDtoRequest criarItemCardapioDtoRequest
    ) {

        //convertendo o dto para command antes de enviar para o usecase
        var criarItemCardapioCommand = ItensCardapioRequestMapper.dtoToCommandCriar(criarItemCardapioDtoRequest);

        //chamando o usecase passando o domain
        var cardapio = criarItemCardapioRestauranteUseCase.run(criarItemCardapioCommand);

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.ok(ItemCardapioPresenter.toDto(cardapio));

    }

    @Operation(description = "Endpoint responsavel por Atualizar um item para um cardápio")
    @PutMapping("/{idItemCardapio}/cardapios/{idCardapio}")
    public ResponseEntity<ItemCardapioDtoResponse> atualizaItemDoCardapioDoRestaurante(
            @PathVariable("idItemCardapio") Long idItemCardapio,
            @PathVariable("idCardapio") Long idCardapio,
            @RequestBody @Valid AtualizarItemCardapioDtoRequest atualizarCardapioDtoRequest
    ) {

        //convertendo o dto para command antes de enviar para o usecase
        //convertendo o dto para command antes de enviar para o usecase
        var atualizarItemCardapioCommand = ItensCardapioRequestMapper.dtoToCommandAtualizar(
                atualizarCardapioDtoRequest, idItemCardapio, idCardapio);


        //chamando o usecase passando o domain
        var itemCardapio = atualizaItemCardapioRestauranteUseCase.run(atualizarItemCardapioCommand);

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.ok(ItemCardapioPresenter.toDto(itemCardapio));

    }

    @Operation(description = "Endpoint responsavel por consultar os itens de um cardapio")
    @GetMapping()
    public ResponseEntity<Page<ItemCardapioDtoResponse>> listarItensDeUmCardapio(
            @RequestParam("idCardapio") Long idCardapio,
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {

        var itemCardapioPage = consultaItensCardapioRestauranteUseCase.run(idCardapio, pageable);

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.ok(ItemCardapioPresenter.toDto(itemCardapioPage));
    }

    @Operation(description = "Endpoint responsavel por Deleter um item do cardápio de um restaurante")
    @DeleteMapping("/{idItemCardapio}/cardapios/{idCardapio}")
    public ResponseEntity<Void> deletaCardapioRestaurante(
            @PathVariable("idItemCardapio") Long idItemCardapio,
            @PathVariable("idCardapio") Long idCardapio
    ) {
        //convertendo o dto para command antes de enviar para o usecase
        var deletarItemCardapioCommand = ItensCardapioRequestMapper
                .dtoToCommandADeletar(idItemCardapio, idCardapio);

        //chamando o usecase passando o domain
        deletaItemCardapioRestauranteUseCase.run(deletarItemCardapioCommand);

        return ResponseEntity.noContent().build();
    }

}
