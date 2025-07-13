package br.com.fiap.imesa.adapter.inbound.rest;

import br.com.fiap.imesa.adapter.inbound.rest.dto.RestauranteDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.RestauranteDtoResponse;
import br.com.fiap.imesa.adapter.inbound.rest.mapper.CriaRestauranteRequestMapper;
import br.com.fiap.imesa.adapter.inbound.rest.presenter.CriarRestaurantePresenter;
import br.com.fiap.imesa.application.usecases.SalvaRestauranteUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/restaurantes")
public class RestaurantesController {

   private final SalvaRestauranteUsuarioUseCase salvaRestauranteUsuarioUseCase;
    public RestaurantesController(SalvaRestauranteUsuarioUseCase salvaRestauranteUsuarioUseCase){
        this.salvaRestauranteUsuarioUseCase = salvaRestauranteUsuarioUseCase;
    }

    @Operation(description = "Endpoint responsavel por Criar um Restaurante")
    @PostMapping
    public ResponseEntity<RestauranteDtoResponse> criaRestaurante(
            @RequestBody @Valid RestauranteDtoRequest restauranteDtoRequest
    ) {

        //convertendo o dto para command antes de enviar para o usecase
        var restauranteCommand = CriaRestauranteRequestMapper.dtoToCommand(restauranteDtoRequest);

        //chamando o usecase passando o domain
        var restauranteCriado = salvaRestauranteUsuarioUseCase.run(restauranteCommand);

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.ok(CriarRestaurantePresenter.toDto(restauranteCriado));
    }

}
