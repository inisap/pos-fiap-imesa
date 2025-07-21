package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.CombinacaoCardarpioERestauranteNaoExisteException;
import br.com.fiap.imesa.application.exception.CombinacaoItemCardapioEIdCardarpioNaoExisteException;
import br.com.fiap.imesa.application.usecases.command.DeletarCardapioCommand;
import br.com.fiap.imesa.application.usecases.command.DeletarItemCardapioCommand;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.imesa.domain.gateway.ICardapioRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IItemCardapioRestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletarItemCardapioRestauranteUseCaseTest {

    @Mock
    private IItemCardapioRestauranteRepository itemCardapioRestauranteRepository;

    @InjectMocks
    private DeletaItemCardapioRestauranteUseCase useCase;


    @Test
    void deveDeletarItemCardapioComSucesso(){
        //arrange

        var command = DeletarItemCardapioCommand.builder()
                .idCardapio(1L)
                .idItemCardapio(1L)
                .build();

        var cardapio = ItemCardapio.builder()
                .idItemCardapio(1L)
                .nome("nome do item")
                        .build();

        when(itemCardapioRestauranteRepository.consultarPorIdItemCardapioEIdCardapio(any()))
                .thenReturn(Optional.of(cardapio));

        //Act
        useCase.run(command);

        //assert
        verify(itemCardapioRestauranteRepository).consultarPorIdItemCardapioEIdCardapio(any());
        verify(itemCardapioRestauranteRepository).deletar(any());
    }

    @Test
    void deveLancarExcecaoQuandotemCardapioEIdCardapioNaoExiste() {

        //arrange
        var command = DeletarItemCardapioCommand.builder()
                .idCardapio(1L)
                .idItemCardapio(1L)
                .build();

        when(itemCardapioRestauranteRepository.consultarPorIdItemCardapioEIdCardapio(any()))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(CombinacaoItemCardapioEIdCardarpioNaoExisteException.class, () -> useCase.run(command));

        //assert
        verify(itemCardapioRestauranteRepository).consultarPorIdItemCardapioEIdCardapio(any());
        verify(itemCardapioRestauranteRepository, never()).deletar(any());
    }
}
