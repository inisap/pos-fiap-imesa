package br.com.fiap.imesa.adapters.in.controller;

import br.com.fiap.imesa.application.core.usecases.AtualizarSenhaUsuario.dto.AtualizarSenhaUsuarioRequest;
import br.com.fiap.imesa.application.core.usecases.AtualizarUsuario.dto.AtualizarUsuarioRequest;
import br.com.fiap.imesa.application.core.usecases.AtualizarUsuario.dto.AtualizarUsuarioResponse;
import br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioId.dto.ConsultaUsuarioIdResponse;
import br.com.fiap.imesa.application.core.usecases.ConsultaUsuarioLogin.dto.ConsultaUsuarioLoginResponse;
import br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto.CriarUsuarioRequest;
import br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto.CriarUsuarioResponse;
import br.com.fiap.imesa.application.core.usecases.ValidadorLogin.dto.LoginRequest;
import br.com.fiap.imesa.application.core.usecases.ValidadorLogin.dto.LoginResponse;
import br.com.fiap.imesa.application.ports.in.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static br.com.fiap.imesa.adapters.in.docs.retornosExemplos.ConsultaIdUsuarioPorLoginExemplo.RETORNO_NOT_FOUND_CONSULTA_USUARIO_POR_LOGIN;
import static br.com.fiap.imesa.adapters.in.docs.retornosExemplos.ConsultaIdUsuarioPorLoginExemplo.RETORNO_SUCESSO_CONSULTA_USUARIO_POR_LOGIN;
import static br.com.fiap.imesa.adapters.in.docs.retornosExemplos.ConsultaUsuarioExemplo.RETORNO_NOT_FOUND_CONSULTA_USUARIO_POR_ID;
import static br.com.fiap.imesa.adapters.in.docs.retornosExemplos.ConsultaUsuarioExemplo.RETORNO_SUCESSO_CONSULTA_USUARIO_POR_ID;
import static br.com.fiap.imesa.adapters.in.docs.retornosExemplos.CriarUsuarioExemplo.RETORNO_CRIACAO_USUARIO_SUCESSO;
import static br.com.fiap.imesa.adapters.in.docs.retornosExemplos.DeletaUsuarioExemplo.RETORNO_NOT_FOUND_NA_EXCLUSAO;
import static br.com.fiap.imesa.adapters.in.docs.retornosExemplos.ValidaLoginExemplo.RETORNO_SUCESSO;
import static br.com.fiap.imesa.adapters.in.docs.retornosExemplos.ValidaLoginExemplo.RETORNO_USUARIO_SENHA_INVALIDO;

@Slf4j
@RestController
@RequestMapping("/v1/users")
public class UsersController {

    private final ValidaLoginPortIn validaLoginPortIn;
    private final AtualizarSenhaUsuarioPortIn atualizarSenhaUsuarioPortIn;
    private final ConsultarUsuarioPorIdPortIn consultarUsuarioPorIdPortIn;
    private final ConsultarUsuarioPorLoginPortIn consultarUsuarioPorLoginPortIn;
    private final CriarUsuarioPortIn criarUsuarioPortIn;
    private final AtualizarUsuarioPortIn atualizarUsuarioPortIn;
    private final DeletarUsuarioPortIn deletarUsuarioPortIn;

    public UsersController(ValidaLoginPortIn validaLoginPortIn,
                           AtualizarSenhaUsuarioPortIn atualizarSenhaUsuarioPortIn,
                           ConsultarUsuarioPorIdPortIn consultarUsuarioPorIdPortIn,
                           ConsultarUsuarioPorLoginPortIn consultarUsuarioPorLoginPortIn,
                           CriarUsuarioPortIn criarUsuarioPortIn,
                           AtualizarUsuarioPortIn atualizarUsuarioPortIn,
                           DeletarUsuarioPortIn deletarUsuarioPortIn) {
        this.validaLoginPortIn = validaLoginPortIn;
        this.atualizarSenhaUsuarioPortIn = atualizarSenhaUsuarioPortIn;
        this.consultarUsuarioPorIdPortIn = consultarUsuarioPorIdPortIn;
        this.consultarUsuarioPorLoginPortIn = consultarUsuarioPorLoginPortIn;
        this.criarUsuarioPortIn = criarUsuarioPortIn;
        this.atualizarUsuarioPortIn = atualizarUsuarioPortIn;
        this.deletarUsuarioPortIn = deletarUsuarioPortIn;
    }

    @Operation(description = "Endpoint que valida o Login e senha de um Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno Sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = RETORNO_SUCESSO)
                            })),
            @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = RETORNO_USUARIO_SENHA_INVALIDO)
                            }))})
    @PostMapping("/auth/logins")
    public ResponseEntity<LoginResponse> validaLogin(
            @RequestBody LoginRequest loginRequest
    ) {
        var loginValido = validaLoginPortIn.validaLogin(loginRequest);

        return ResponseEntity.ok(loginValido);

    }

    @Operation(description = "Endpoint responsavel por atualizar a senha de um Usuario")
    @ApiResponse(responseCode = "200", description = "Senha Alterada com sucesso")
    @PutMapping("/{userId}/senha")
    public ResponseEntity<?> atualizaSenhaUsuario(
            @PathVariable("userId") Long userId,
            @RequestBody @Valid AtualizarSenhaUsuarioRequest atualizarUsuarioRequest
    ) {
        atualizarSenhaUsuarioPortIn.atualizar(userId, atualizarUsuarioRequest);
        return ResponseEntity.ok("Senha alterada com sucesso");
    }

    @Operation(description = "Endpoint responsavel por Consultar o Id do usuario baseado em seu login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os dados de usuario",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = RETORNO_SUCESSO_CONSULTA_USUARIO_POR_LOGIN)
                            })),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = RETORNO_NOT_FOUND_CONSULTA_USUARIO_POR_LOGIN)
                            }))})
    @GetMapping
    public ResponseEntity<ConsultaUsuarioLoginResponse> consultaIdUsuarioPorLogin(
            @Parameter(description = "Login do Usuario para consulta de dados", required = true, in = ParameterIn.QUERY)
            @RequestParam String login
    ) {
        var usuario = consultarUsuarioPorLoginPortIn.consutar(login);

        return ResponseEntity.ok(usuario);
    }

    @Operation(description = "Endpoint responsavel por Consultar os dados de um Usuarios baseado em seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os dados de usuario",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = RETORNO_SUCESSO_CONSULTA_USUARIO_POR_ID)
                            })),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = RETORNO_NOT_FOUND_CONSULTA_USUARIO_POR_ID)
                            }))})
    @GetMapping("/{userId}")
    public ResponseEntity<ConsultaUsuarioIdResponse> consultaUsuario(
            @PathVariable(value = "userId", required = true) Long userId) {
        var usuario = consultarUsuarioPorIdPortIn.consultar(userId);

        return ResponseEntity.ok(usuario);
    }

    @Operation(description = "Endpoint responsavel por Criar um novo Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo usuario criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = RETORNO_CRIACAO_USUARIO_SUCESSO)
                            })),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = RETORNO_NOT_FOUND_CONSULTA_USUARIO_POR_ID)
                            }))})
    @PostMapping
    public ResponseEntity<CriarUsuarioResponse> criaUsuario(
            @RequestBody @Valid CriarUsuarioRequest criarUsuarioRequest
    ) {

        var usuarioCriado = criarUsuarioPortIn.criar(criarUsuarioRequest);

        URI location = URI.create("/users/" + usuarioCriado.getId());
        return ResponseEntity.created(location).body(usuarioCriado);
    }

    @Operation(description = "Endpoint responsavel por atualizar os dados de um Usuario")
    @ApiResponse(responseCode = "200", description = "Dados do usuario Atualizado com sucesso")
    @PatchMapping("/{userId}")
    public ResponseEntity<AtualizarUsuarioResponse> atualizaDadosUsuario(
            @PathVariable("userId") Long userId,
            @RequestBody AtualizarUsuarioRequest atualizarUsuarioRequest
    ) {
        var usuarioAtualiza = atualizarUsuarioPortIn.atualizar(userId, atualizarUsuarioRequest);
        return ResponseEntity.ok(usuarioAtualiza);
    }

    @Operation(description = "Endpoint responsavel por Deletar um usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario Removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = RETORNO_NOT_FOUND_NA_EXCLUSAO)
                            }))})
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deletaUsuario(
            @PathVariable("userId") Long userId
    ) {
        deletarUsuarioPortIn.deletarUsuario(userId);
        return ResponseEntity.noContent().build();
    }

}
