package br.com.fiap.imesa.adapter.inbound.rest.controller;

import br.com.fiap.imesa.adapter.inbound.rest.dto.EnderecoDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.EnderecoDtoResponse;
import br.com.fiap.imesa.adapter.inbound.rest.mapper.EnderecoRequestMapper;
import br.com.fiap.imesa.adapter.inbound.rest.presenter.EnderecoPresenter;
import br.com.fiap.imesa.application.usecases.ConsultaEnderecoUsuarioPorIdUsuarioUseCase;
import br.com.fiap.imesa.application.usecases.SalvaEnderecoUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/users")
public class EnderecosController {

   private final SalvaEnderecoUsuarioUseCase salvaEnderecoUsuarioUseCase;
   private final ConsultaEnderecoUsuarioPorIdUsuarioUseCase consultaEnderecoUsuarioPorIdUsuarioUseCase;
    public EnderecosController(SalvaEnderecoUsuarioUseCase salvaEnderecoUsuarioUseCase,
                               ConsultaEnderecoUsuarioPorIdUsuarioUseCase consultaEnderecoUsuarioPorIdUsuarioUseCase){
        this.salvaEnderecoUsuarioUseCase = salvaEnderecoUsuarioUseCase;
        this.consultaEnderecoUsuarioPorIdUsuarioUseCase = consultaEnderecoUsuarioPorIdUsuarioUseCase;
    }

    @Operation(description = "Endpoint responsavel por Criar um Endereco associando a um Usuario")
    @PutMapping("/{userId}/enderecos")
    public ResponseEntity<EnderecoDtoResponse> criaAtualizaEnderecoAssociadoUsuario(
            @PathVariable("userId") Long userId,
            @RequestBody @Valid EnderecoDtoRequest enderecoDtoRequest
    ) {

        //convertendo o dto para command antes de enviar para o usecase
        var enderecoCommand = EnderecoRequestMapper.dtoToCommand(userId, enderecoDtoRequest);

        //chamando o usecase passando o domain
        var enderecoCriado = salvaEnderecoUsuarioUseCase.run(enderecoCommand);

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.ok(EnderecoPresenter.toDto(enderecoCriado));

    }

    @Operation(description = "Endpoint responsavel por consultar o endereco associando a um Usuario")
    @GetMapping("/{userId}/enderecos")
    public ResponseEntity<EnderecoDtoResponse> consultarEnderecoAssociadoUsuario(
            @PathVariable("userId") Long userId
    ) {

        var endereco = consultaEnderecoUsuarioPorIdUsuarioUseCase.run(userId);

        return ResponseEntity.ok(EnderecoPresenter.toDto(endereco));
    }

}
