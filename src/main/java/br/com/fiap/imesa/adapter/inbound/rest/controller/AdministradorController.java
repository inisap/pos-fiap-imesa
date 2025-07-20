package br.com.fiap.imesa.adapter.inbound.rest.controller;

import br.com.fiap.imesa.adapter.inbound.rest.dto.TipoCozinhaDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.TipoCozinhaDtoResponse;
import br.com.fiap.imesa.adapter.inbound.rest.dto.TipoUsuarioDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.TipoUsuarioDtoResponse;
import br.com.fiap.imesa.adapter.inbound.rest.mapper.AtualizaTipoCozinhaRequestMapper;
import br.com.fiap.imesa.adapter.inbound.rest.mapper.AtualizaTipoUsuarioRequestMapper;
import br.com.fiap.imesa.adapter.inbound.rest.mapper.CriaTipoCozinhaRequestMapper;
import br.com.fiap.imesa.adapter.inbound.rest.mapper.CriaTipoUsuarioRequestMapper;
import br.com.fiap.imesa.adapter.inbound.rest.presenter.ConsultarTodosTiposCozinhaPresenter;
import br.com.fiap.imesa.adapter.inbound.rest.presenter.ConsultarTodosTiposUsuarioPresenter;
import br.com.fiap.imesa.adapter.inbound.rest.presenter.TipoCozinhaPresenter;
import br.com.fiap.imesa.adapter.inbound.rest.presenter.TipoUsuarioPresenter;
import br.com.fiap.imesa.application.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/adm")
public class AdministradorController {

   private final CriaTipoUsuarioUseCase criaTipoUsuarioUseCase;
   private final AtualizaTipoUsuarioUseCase atualizaTipoUsuarioUseCase;
   private final DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase;
   private final ConsultaTiposUsuarioUseCase consultaTiposUsuarioUseCase;
   private final CriaTipoCozinhaUseCase criaTipoCozinhaUseCase;
   private final AtualizaTipoCozinhaUseCase atualizaTipoCozinhaUseCase;
   private final DeletarTipoCozinhaUseCase deletarTipoCozinhaUseCase;
   private final ConsultaTiposCozinhaUseCase consultaTiposCozinhaUseCase;
    public AdministradorController(CriaTipoUsuarioUseCase criaTipoUsuarioUseCase,
                                   AtualizaTipoUsuarioUseCase atualizaTipoUsuarioUseCase,
                                   DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase,
                                   ConsultaTiposUsuarioUseCase consultaTiposUsuarioUseCase,
                                   CriaTipoCozinhaUseCase criaTipoCozinhaUseCase,
                                   AtualizaTipoCozinhaUseCase atualizaTipoCozinhaUseCase,
                                   DeletarTipoCozinhaUseCase deletarTipoCozinhaUseCase,
                                   ConsultaTiposCozinhaUseCase consultaTiposCozinhaUseCase){
        this.criaTipoUsuarioUseCase = criaTipoUsuarioUseCase;
        this.atualizaTipoUsuarioUseCase = atualizaTipoUsuarioUseCase;
        this.deletarTipoUsuarioUseCase = deletarTipoUsuarioUseCase;
        this.consultaTiposUsuarioUseCase = consultaTiposUsuarioUseCase;
        this.criaTipoCozinhaUseCase = criaTipoCozinhaUseCase;
        this.atualizaTipoCozinhaUseCase = atualizaTipoCozinhaUseCase;
        this.deletarTipoCozinhaUseCase = deletarTipoCozinhaUseCase;
        this.consultaTiposCozinhaUseCase = consultaTiposCozinhaUseCase;
    }

    @Operation(description = "Endpoint responsavel por Criar tipos de Usuario")
    @PostMapping("/tipos-usuarios")
    public ResponseEntity<TipoUsuarioDtoResponse> criaTipoDeUsuario(
            @RequestBody @Valid TipoUsuarioDtoRequest tipoUsuarioDtoRequest
    ) {

        //convertendo o dto para command antes de enviar para o usecase
        var criarTipoUsuarioCommand = CriaTipoUsuarioRequestMapper.dtoToCommand(tipoUsuarioDtoRequest);

        //chamando o usecase passando o domain
        var tipoUsuarioCriado = criaTipoUsuarioUseCase.run(criarTipoUsuarioCommand);

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.ok(TipoUsuarioPresenter.toDto(tipoUsuarioCriado));

    }

    @Operation(description = "Endpoint responsavel por Atualizar tipos de Usuario")
    @PutMapping("/tipos-usuarios/{idTipoUsuario}")
    public ResponseEntity<TipoUsuarioDtoResponse> atualizaTipoDeUsuario(
            @PathVariable ("idTipoUsuario") Integer idTipoUsuario,
            @RequestBody @Valid TipoUsuarioDtoRequest tipoUsuarioDtoRequest
    ) {

        //convertendo o dto para command antes de enviar para o usecase
        var atualizaTipoUsuarioCommand = AtualizaTipoUsuarioRequestMapper.dtoToCommand(idTipoUsuario, tipoUsuarioDtoRequest);

        //chamando o usecase passando o domain
        var tipoUsuarioCriado = atualizaTipoUsuarioUseCase.run(atualizaTipoUsuarioCommand);

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.ok(TipoUsuarioPresenter.toDto(tipoUsuarioCriado));
    }

    @Operation(description = "Endpoint responsavel por Deletar um tipo de usuario com base em seu id")
    @DeleteMapping("/tipos-usuarios/{idTipoUsuario}")
    public ResponseEntity<Void> deletaTipoUsuario(
            @PathVariable("idTipoUsuario") Integer idTipoUsuario
    ) {
        //passando o parametro puro devido a simplicidade, nao sendo necessario converter para um command
        //chamando o usecase passando o domain
        deletarTipoUsuarioUseCase.run(idTipoUsuario);

        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Endpoint responsavel por Consultar todos os tipos usuario")
    @GetMapping("/tipos-usuarios")
    public ResponseEntity<List<TipoUsuarioDtoResponse>> consultaTodosTiposDeUsuarios() {

        //por ser simples não necessario um command
        //chamando o usecase com o parameter
        var listTiposUsuariosDomain = consultaTiposUsuarioUseCase.run();

        //convertendo o domain para dto para retornar para o chamador
        var usuarioDtoResponse = ConsultarTodosTiposUsuarioPresenter.toDto(listTiposUsuariosDomain);

        return ResponseEntity.ok(usuarioDtoResponse);
    }

    //abaixo cadastros tipos de cozinhas
    @Operation(description = "Endpoint responsavel por Criar tipos de Cozinhas")
    @PostMapping("/tipos-cozinhas")
    public ResponseEntity<TipoCozinhaDtoResponse> criaTipoDeCozinha(
            @RequestBody @Valid TipoCozinhaDtoRequest tipoCozinhaDtoRequest
    ) {

        //convertendo o dto para command antes de enviar para o usecase
        var criarTipoCozinhaCommand = CriaTipoCozinhaRequestMapper.dtoToCommand(tipoCozinhaDtoRequest);

        //chamando o usecase passando o domain
        var tipoCozinhaCriada = criaTipoCozinhaUseCase.run(criarTipoCozinhaCommand);

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.ok(TipoCozinhaPresenter.toDto(tipoCozinhaCriada));

    }

    @Operation(description = "Endpoint responsavel por Atualizar tipos de Cozinhas")
    @PutMapping("/tipos-cozinhas/{idTipoCozinha}")
    public ResponseEntity<TipoCozinhaDtoResponse> atualizaTipoDeCozinha(
            @PathVariable ("idTipoCozinha") Integer idTipoCozinha,
            @RequestBody @Valid TipoCozinhaDtoRequest tipoCozinhaDtoRequest
    ) {

        //convertendo o dto para command antes de enviar para o usecase
        var atualizaTipoCozinhaCommand = AtualizaTipoCozinhaRequestMapper.dtoToCommand(idTipoCozinha, tipoCozinhaDtoRequest);

        //chamando o usecase passando o domain
        var tipoCozinhaAtualizada = atualizaTipoCozinhaUseCase.run(atualizaTipoCozinhaCommand);

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.ok(TipoCozinhaPresenter.toDto(tipoCozinhaAtualizada));
    }

    @Operation(description = "Endpoint responsavel por Deletar um tipo de cozinha com base em seu id")
    @DeleteMapping("/tipos-cozinhas/{idTipoCozinha}")
    public ResponseEntity<Void> deletaTipoCozinha(
            @PathVariable("idTipoCozinha") Integer idTipoCozinha
    ) {
        //passando o parametro puro devido a simplicidade, nao sendo necessario converter para um command
        //chamando o usecase passando o domain
        deletarTipoCozinhaUseCase.run(idTipoCozinha);

        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Endpoint responsavel por Consultar todos os tipos cozinha")
    @GetMapping("/tipos-cozinhas")
    public ResponseEntity<List<TipoCozinhaDtoResponse>> consultaTodosTiposDeCozinha() {

        //por ser simples não necessario um command
        //chamando o usecase com o parameter
        var listTiposCozinhaDomain = consultaTiposCozinhaUseCase.run();

        //convertendo o domain para dto para retornar para o chamador
        var tipoCozinhaDtoResponse = ConsultarTodosTiposCozinhaPresenter.toDto(listTiposCozinhaDomain);

        return ResponseEntity.ok(tipoCozinhaDtoResponse);
    }

}
