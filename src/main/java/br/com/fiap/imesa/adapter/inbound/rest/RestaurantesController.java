package br.com.fiap.imesa.adapter.inbound.rest;

import br.com.fiap.imesa.adapter.inbound.rest.dto.RestauranteDtoRequest;
import br.com.fiap.imesa.adapter.inbound.rest.dto.RestauranteDtoResponse;
import br.com.fiap.imesa.adapter.inbound.rest.mapper.ConsultarRestaurantesRequestMapper;
import br.com.fiap.imesa.adapter.inbound.rest.mapper.CriaRestauranteRequestMapper;
import br.com.fiap.imesa.adapter.inbound.rest.presenter.ConsultarRestaurantePresenter;
import br.com.fiap.imesa.adapter.inbound.rest.presenter.CriarRestaurantePresenter;
import br.com.fiap.imesa.application.usecases.ConsultaRestauranteUseCase;
import br.com.fiap.imesa.application.usecases.SalvaRestauranteUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/restaurantes")
public class RestaurantesController {

   private final SalvaRestauranteUsuarioUseCase salvaRestauranteUsuarioUseCase;
   private final ConsultaRestauranteUseCase consultaRestauranteUseCase;
    public RestaurantesController(SalvaRestauranteUsuarioUseCase salvaRestauranteUsuarioUseCase,
                                  ConsultaRestauranteUseCase consultaRestauranteUseCase){
        this.salvaRestauranteUsuarioUseCase = salvaRestauranteUsuarioUseCase;
        this.consultaRestauranteUseCase = consultaRestauranteUseCase;
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

    @Operation(description = "Endpoint responsavel por Consultar Restaurantes")
    @GetMapping
    public ResponseEntity<List<RestauranteDtoResponse>> consutlarRestaurante(
            @Parameter(description = "tipoCozinha para consulta de restaurantes pelo tipo de cozinha", required = false, in = ParameterIn.QUERY)
            @RequestParam(required = false) Integer tipoCozinha,
            @Parameter(description = "nomeRestaurante para busca de restaurantes pelo nome", required = false, in = ParameterIn.QUERY)
            @RequestParam(required = false) String nomeRestaurante
    ) {

        var filtrosCommand = ConsultarRestaurantesRequestMapper.toCommand(tipoCozinha, nomeRestaurante);

        //chamando o usecase passando o domain
        var restaurantesList = consultaRestauranteUseCase.run(filtrosCommand);

        //convertendo o domain para dto para retornar para o chamador
        return ResponseEntity.ok(ConsultarRestaurantePresenter.toDto(restaurantesList));
    }

}
