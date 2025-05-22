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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
                           DeletarUsuarioPortIn deletarUsuarioPortIn){
        this.validaLoginPortIn = validaLoginPortIn;
        this.atualizarSenhaUsuarioPortIn = atualizarSenhaUsuarioPortIn;
        this.consultarUsuarioPorIdPortIn = consultarUsuarioPorIdPortIn;
        this.consultarUsuarioPorLoginPortIn = consultarUsuarioPorLoginPortIn;
        this.criarUsuarioPortIn = criarUsuarioPortIn;
        this.atualizarUsuarioPortIn = atualizarUsuarioPortIn;
        this.deletarUsuarioPortIn = deletarUsuarioPortIn;
    }

    @Operation(description = "Endpoint que valida o Login e senha de um Usuario")
    @ApiResponse(responseCode = "200", description = "Login validado com sucesso!")
    @PostMapping("/auth/logins")
    public ResponseEntity<LoginResponse> validaLogin(
            @RequestBody LoginRequest loginRequest
    ){
        var loginValido = validaLoginPortIn.validaLogin(loginRequest);

        return ResponseEntity.ok(loginValido);

    }

    @Operation(description = "Endpoint responsavel por atualizar a senha de um Usuario")
    @ApiResponse(responseCode = "200", description = "Senha Alterada com sucesso")
    @PutMapping("/{userId}/senha")
    public ResponseEntity<?> atualizaSenhaUsuario(
            @PathVariable("userId") Long userId,
            @RequestBody AtualizarSenhaUsuarioRequest atualizarUsuarioRequest
    ){
        atualizarSenhaUsuarioPortIn.atualizar(userId, atualizarUsuarioRequest);
        return ResponseEntity.ok("Senha alterada com sucesso");
    }

    @Operation(description = "Endpoint responsavel por Consultar o Id do usuario baseado em seu login")
    @ApiResponse(responseCode = "200", description = "Retorna os dados de usuario")
    @GetMapping
    public ResponseEntity<ConsultaUsuarioLoginResponse> consultaIdUsuarioPorLogin(
            @Parameter(description = "Login do Usuario para consulta de dados", required = true, in = ParameterIn.QUERY)
            @RequestParam String login
    ){
        var usuario = consultarUsuarioPorLoginPortIn.consutar(login);

        return ResponseEntity.ok(usuario);
    }

    @Operation(description = "Endpoint responsavel por Consultar os dados de um Usuarios baseado em seu Id")
    @ApiResponse(responseCode = "200", description = "Retorna os dados de usuario")
    @GetMapping("/{userId}")
    public ResponseEntity<ConsultaUsuarioIdResponse> consultaUsuario(
            @PathVariable(value = "userId",required = true) Long userId){
        var usuario = consultarUsuarioPorIdPortIn.consultar(userId);

        return ResponseEntity.ok(usuario);
    }

    @Operation(description = "Endpoint responsavel por Criar um novo Usuario")
    @ApiResponse(responseCode = "201", description = "Novo usuario criado com sucesso")
    @PostMapping
    public ResponseEntity<CriarUsuarioResponse> criaUsuario(
            @RequestBody CriarUsuarioRequest criarUsuarioRequest
    ){

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
    ){
        var usuarioAtualiza = atualizarUsuarioPortIn.atualizar(userId, atualizarUsuarioRequest);
        return ResponseEntity.ok(usuarioAtualiza);
    }

    @Operation(description = "Endpoint responsavel por Deletar um usuario")
    @ApiResponse(responseCode = "204")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> criaEnderecoUsuario(
            @PathVariable("userId") Long userId
    ){
        deletarUsuarioPortIn.deletarUsuario(userId);
        return ResponseEntity.noContent().build();
    }

}
