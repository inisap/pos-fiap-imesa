package br.com.fiap.imesa.adapter.inbound.rest;

import br.com.fiap.imesa.adapter.inbound.rest.dto.*;
import br.com.fiap.imesa.adapter.inbound.rest.mapper.*;
import br.com.fiap.imesa.adapter.inbound.rest.presenter.*;
import br.com.fiap.imesa.application.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/v1/users")
public class UsuariosController {

   private final CriarUsuarioUseCase criarUsuarioUseCase;
   private final ConsultaUsuarioPorLoginUseCase consultaUsuarioPorLoginUseCase;
   private final ConsultaUsuarioPorIdUseCase consultaUsuarioPorIdUseCase;
   private final AtualizaDadosUsuarioUseCase atualizaDadosUsuarioUseCase;
   private final AtualizaSenhaUsuarioUseCase atualizaSenhaUsuarioUseCase;
   private final AutenticacaoUsuarioUseCase autenticacaoUsuarioUseCase;
   private final DeletarUsuarioUseCase deletarUsuarioUseCase;
    public UsuariosController(CriarUsuarioUseCase criarUsuarioUseCase,
                              ConsultaUsuarioPorLoginUseCase consultaUsuarioPorLoginUseCase,
                              ConsultaUsuarioPorIdUseCase consultaUsuarioPorIdUseCase,
                              AtualizaDadosUsuarioUseCase atualizaDadosUsuarioUseCase,
                              AtualizaSenhaUsuarioUseCase atualizaSenhaUsuarioUseCase,
                              AutenticacaoUsuarioUseCase autenticacaoUsuarioUseCase,
                              DeletarUsuarioUseCase deletarUsuarioUseCase){
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.consultaUsuarioPorLoginUseCase = consultaUsuarioPorLoginUseCase;
        this.consultaUsuarioPorIdUseCase = consultaUsuarioPorIdUseCase;
        this.atualizaDadosUsuarioUseCase = atualizaDadosUsuarioUseCase;
        this.atualizaSenhaUsuarioUseCase = atualizaSenhaUsuarioUseCase;
        this.autenticacaoUsuarioUseCase = autenticacaoUsuarioUseCase;
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
    }

    @Operation(description = "Endpoint responsavel por Criar um novo Usuario")
    @PostMapping
    public ResponseEntity<UsuarioDtoResponse> criaUsuario(
            @RequestBody @Valid UsuarioDtoRequest usuarioDtoRequest
    ) {
        //convertendo o dto para command
        var usuarioCommand = CriaUsuarioRequestMapper.dtoToCommand(usuarioDtoRequest);

        //chamando o usecase passando o command
        var usuarioCriado = criarUsuarioUseCase.run(usuarioCommand);

        URI location = URI.create("/users/" + usuarioCriado.getId());

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.created(location).body(CriarUsuarioPresenter.toDto(usuarioCriado));
    }

    @Operation(description = "Endpoint responsavel por Consultar o Id do usuario baseado em seu login")
    @GetMapping
    public ResponseEntity<UsuarioDtoResponse> consultaIdUsuarioPorLogin(
            @Parameter(description = "Login do Usuario para consulta de dados", required = true, in = ParameterIn.QUERY)
            @RequestParam String login
    ) {
        //por ser simples não necessario um command
        //chamando o usecase passando o domain
        var usuarioDomainRetorno = consultaUsuarioPorLoginUseCase.run(login);

        //convertendo o domain para dto para retornar para o chamador
        var usuarioDtoResponse = ConsultarUsuarioLoginPresenter.toDto(usuarioDomainRetorno);

        return ResponseEntity.ok(usuarioDtoResponse);
    }

    @Operation(description = "Endpoint responsavel por Consultar os dados de um Usuarios baseado em seu Id")
    @GetMapping("/{userId}")
    public ResponseEntity<UsuarioDtoResponse> consultaUsuario(
            @PathVariable(value = "userId", required = true) Long userId) {

        //por ser simples não necessario um command
        //chamando o usecase com o parameter
        var usuarioDomainRetorno = consultaUsuarioPorIdUseCase.run(userId);

        //convertendo o domain para dto para retornar para o chamador
        var usuarioDtoResponse = ConsultarUsuarioPorIdPresenter.toDto(usuarioDomainRetorno);

        return ResponseEntity.ok(usuarioDtoResponse);
    }

    @Operation(description = "Endpoint responsavel por atualizar os dados de um Usuario")
    @PatchMapping("/{userId}")
    public ResponseEntity<UsuarioDtoResponse> atualizaDadosUsuario(
            @PathVariable("userId") Long userId,
            @RequestBody AtualizarUsuarioDtoRequest atualizarUsuarioRequest
    ) {

        //convertendo o dto para command
        var atualizaUsuarioCommand = AtualizaUsuarioRequestMapper.dtoToCommand(userId, atualizarUsuarioRequest);

        //chamando o usecase passando o command
        var usuarioDomainRetorno = atualizaDadosUsuarioUseCase.run(atualizaUsuarioCommand);

        //mapeia para o dto reduzindo campos, responsabilidade da controller, utilizando o presenter
        var usuarioAtualizaDto = AtualizarUsuarioPresenter.toDto(usuarioDomainRetorno);

        return ResponseEntity.ok(usuarioAtualizaDto);
    }

    @Operation(description = "Endpoint responsavel por atualizar a senha de um Usuario")
    @PutMapping("/{userId}/senha")
    public ResponseEntity<?> atualizaSenhaUsuario(
            @PathVariable("userId") Long userId,
            @RequestBody @Valid AtualizarSenhaUsuarioRequest atualizarSenhaUsuarioRequest
    ) {

        var atualizaSenhaUsuarioCommand = AtualizaSenhaUsuarioRequestMapper
                .dtoToCommand(userId, atualizarSenhaUsuarioRequest);
        //convertendo o dto para uma acao command antes de enviar para o usecase
        atualizaSenhaUsuarioUseCase.run(atualizaSenhaUsuarioCommand);

        return ResponseEntity.ok("Senha alterada com sucesso");
    }

    @Operation(description = "Endpoint que valida o Login e senha de um Usuario")
    @PostMapping("/auth/logins")
    public ResponseEntity<LoginDtoResponse> validaLogin(
            @RequestBody LoginDtoRequest loginDtoRequest
    ) {
        //convertendo o dto para o command
        var loginUsuarioDomain = ValidarLoginUsuarioRequestMapper.dtoToCommand(loginDtoRequest);

        //chamando o usecase passando o domain
        var loginValido = autenticacaoUsuarioUseCase.run(loginUsuarioDomain);

        //mapeia para o dto reduzindo campos responsabilidade da controller, utilizando o presenter
        var loginValidoDto = AutenticacaoUsuarioPresenter.toDto(loginValido);

        return ResponseEntity.ok(loginValidoDto);

    }

    @Operation(description = "Endpoint responsavel por Deletar um usuario e seu endereco com base no Id do usuario")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deletaUsuario(
            @PathVariable("userId") Long userId
    ) {
        //passando o parametro puro devido a simplicidade, nao sendo necessario converter para um command
        //chamando o usecase passando o domain
        deletarUsuarioUseCase.run(userId);

        return ResponseEntity.noContent().build();
    }

}
