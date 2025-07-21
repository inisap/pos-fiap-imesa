package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.CardapioNaoEncontradoException;
import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.AtualizarCardapioCommand;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.ICardapioRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AtualizaCardapioRestauranteUseCaseTest {

    @Mock
    private IRestauranteRepository restauranteRepository;
    @Mock
    private ICardapioRestauranteRepository cardapioRestauranteRepository;

    @InjectMocks
    AtualizaCardapioRestauranteUseCase atualizaCardapioRestauranteUseCase;

    private AtualizarCardapioCommand command;

    @BeforeEach
    void setUp() {
        command = AtualizarCardapioCommand.builder()
                .idCardapio(1L)
                .descricaoCardapio("Cardapio de Comida Italiana")
                .idRestaurante(1L)
                .build();
    }

    @Test
    void deveAlturalizarOcArdapioComSucesso(){

        //arrange
        when(restauranteRepository.consultaPorId(any()))
                .thenReturn(Optional.of(Restaurante.builder().build()));

        when(cardapioRestauranteRepository.consultar(any()))
                .thenReturn(Optional.of(Cardapio.builder().build()));

        when(cardapioRestauranteRepository.salvar(any(Cardapio.class)))
                .thenReturn(Cardapio.builder().build());

        //act
        var retorno= atualizaCardapioRestauranteUseCase.run(command);

        //assert
        assertNotNull(retorno);
        verify(restauranteRepository).consultaPorId(command.getIdRestaurante());
        verify(cardapioRestauranteRepository).consultar(command.getIdCardapio());
        verify(cardapioRestauranteRepository).salvar(any(Cardapio.class));
    }


    @Test
    void deveLancarExcecaoParaRestauranteNaoEncontrado(){
        //arrange
        when(restauranteRepository.consultaPorId(command.getIdRestaurante()))
                .thenReturn(Optional.empty());

        //act
        RestauranteNaoEncontradoException ex = assertThrows(RestauranteNaoEncontradoException.class, () -> {
            atualizaCardapioRestauranteUseCase.run(command);
        });

        //assert
        assertEquals(String.format("Id do Restaurante não encontrado: [%s]",command.getIdRestaurante()), ex.getMessage());
        verify(restauranteRepository).consultaPorId(command.getIdRestaurante());
        verifyNoInteractions(cardapioRestauranteRepository);
    }

    @Test
    void deveLancarExcecaoParaCardapioNaoEncontrado(){
        //arrrange
        when(restauranteRepository.consultaPorId(command.getIdRestaurante()))
                .thenReturn(Optional.of(Restaurante.builder().build()));

        when(cardapioRestauranteRepository.consultar(command.getIdCardapio()))
                .thenReturn(Optional.empty());

        //act
        CardapioNaoEncontradoException ex = assertThrows(CardapioNaoEncontradoException.class, () -> {
            atualizaCardapioRestauranteUseCase.run(command);
        });

        //assert
        assertEquals(String.format("Id do Cardapio não encontrado: [%s]",command.getIdCardapio()), ex.getMessage());
        verify(restauranteRepository).consultaPorId(command.getIdRestaurante());
        verify(cardapioRestauranteRepository).consultar(command.getIdCardapio());

    }


}
