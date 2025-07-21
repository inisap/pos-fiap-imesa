package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.usecases.command.ConsultarRestaurantesCommand;
import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.IItemCardapioRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultaRestauranteUseCaseTest {

    @Mock
    private IRestauranteRepository consultaRestauranteRepository;

    @InjectMocks
    private ConsultaRestauranteUseCase useCase;


    @Test
    void deveConsultarResturantesUtilizandoFiltrosDePesquisaComSucesso(){
        //arrange
        var command = ConsultarRestaurantesCommand.builder()
                .codigoTipoCozinha(1)
                .nome("Casa Italiana")
                .build();

        var restaurante = Restaurante.builder()
                        .id(1L)
                        .nome("Casa Italiana")
                        .build();


        when(consultaRestauranteRepository.consultarComFiltros(Mockito.any(Restaurante.class)))
                .thenReturn(Collections.singletonList(restaurante));

        //Act
        var resultado = useCase.run(command);

        //assert
        assertNotNull(resultado);
        verify(consultaRestauranteRepository).consultarComFiltros(Mockito.any(Restaurante.class));
    }

    @Test
    void deveConsultarResturantesUtilizandoFiltroTipoCozinhaDePesquisaComSucesso(){
        //arrange
        var command = ConsultarRestaurantesCommand.builder()
                .codigoTipoCozinha(1)
                .build();

        var restaurante = Restaurante.builder()
                .id(1L)
                .build();


        when(consultaRestauranteRepository.consultarComFiltros(Mockito.any(Restaurante.class)))
                .thenReturn(Collections.singletonList(restaurante));

        //Act
        var resultado = useCase.run(command);

        //assert
        assertNotNull(resultado);
        verify(consultaRestauranteRepository).consultarComFiltros(Mockito.any(Restaurante.class));
    }

    @Test
    void deveConsultarResturantesUtilizandoFiltroNomeRestaurantePesquisaComSucesso(){
        //arrange
        var command = ConsultarRestaurantesCommand.builder()
                .nome("Casa Italiana")
                .build();

        var restaurante = Restaurante.builder()
                .nome("Casa Italiana")
                .build();


        when(consultaRestauranteRepository.consultarComFiltros(Mockito.any(Restaurante.class)))
                .thenReturn(Collections.singletonList(restaurante));

        //Act
        var resultado = useCase.run(command);

        //assert
        assertNotNull(resultado);
        verify(consultaRestauranteRepository).consultarComFiltros(Mockito.any(Restaurante.class));
    }

    @Test
    void deveConsultarResturantesSemUtilizarFiltrosComSucesso(){
        //arrange
        ConsultarRestaurantesCommand command = ConsultarRestaurantesCommand.builder().build();

        var restaurante = Restaurante.builder()
                .nome("Casa Italiana")
                .build();

        when(consultaRestauranteRepository.consultarTodos())
                .thenReturn(Collections.singletonList(restaurante));

        //Act
        var resultado = useCase.run(command);

        //assert
        assertNotNull(resultado);
        verify(consultaRestauranteRepository).consultarTodos();
    }
}
