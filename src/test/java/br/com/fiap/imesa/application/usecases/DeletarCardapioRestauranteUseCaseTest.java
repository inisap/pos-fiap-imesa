package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.CardapioNaoExisteParaRestauranteException;
import br.com.fiap.imesa.application.exception.CombinacaoCardarpioERestauranteNaoExisteException;
import br.com.fiap.imesa.application.usecases.command.DeletarCardapioCommand;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.gateway.ICardapioRestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletarCardapioRestauranteUseCaseTest {

    @Mock
    private ICardapioRestauranteRepository cardapioRestauranteRepository;

    @InjectMocks
    private DeletaCardapioRestauranteUseCase useCase;


    @Test
    void deveDeletarCardapioComSucesso(){
        //arrange

        var command = DeletarCardapioCommand.builder()
                .idCardapio(1L)
                .build();

        var cardapio = Cardapio.builder()
                .codigoCardapio(1L)
                .descricaoCardapio("descricaoCardapio")
                        .build();

        when(cardapioRestauranteRepository.consultarPorIdCardapioEIdRestaurante(any()))
                .thenReturn(Optional.of(cardapio));

        //Act
        useCase.run(command);

        //assert
        verify(cardapioRestauranteRepository).consultarPorIdCardapioEIdRestaurante(any());
        verify(cardapioRestauranteRepository).deletar(any());
    }

    @Test
    void deveLancarExcecaoQuandoCardapioNaoExisteParaRestaurante() {

        //arrange
        var command = DeletarCardapioCommand.builder()
                .idCardapio(1L)
                .idRestaurante(1L)
                .build();

        when(cardapioRestauranteRepository.consultarPorIdCardapioEIdRestaurante(any()))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(CombinacaoCardarpioERestauranteNaoExisteException.class, () -> useCase.run(command));

        //assert
        verify(cardapioRestauranteRepository).consultarPorIdCardapioEIdRestaurante(any());
        verify(cardapioRestauranteRepository, never()).deletar(any());
    }
}
