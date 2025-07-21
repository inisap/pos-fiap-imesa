package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.CombinacaoItemCardapioEIdCardarpioNaoExisteException;
import br.com.fiap.imesa.application.exception.TipoCozinhaNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.DeletarItemCardapioCommand;
import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.IItemCardapioRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletarTipoCozinhaUseCaseTest {

    @Mock
    private ITipoCozinhaRepository tipoCozinhaRepository;

    @InjectMocks
    private DeletarTipoCozinhaUseCase useCase;


    @Test
    void deveDeletarTipoCozinhaComSucesso(){
        //arrange

        Integer idTipoCozinha = 1;

        var tipoCozinha = TipoCozinha.builder()
                .id(1)
                .nome("Cozinha Italiana")
                .build();

        when(tipoCozinhaRepository.consultarPorIdTipoCozinha(any()))
                .thenReturn(Optional.of(tipoCozinha));

        //Act
        useCase.run(idTipoCozinha);

        //assert
        verify(tipoCozinhaRepository).consultarPorIdTipoCozinha(any());
        verify(tipoCozinhaRepository).deletar(any());
    }

    @Test
    void deveLancarExcecaoQuandotemCardapioEIdCardapioNaoExiste() {
        //arrange
        Integer idTipoCozinha = 1;

        var tipoCozinha = TipoCozinha.builder()
                .id(1)
                .nome("Cozinha Italiana")
                .build();

        when(tipoCozinhaRepository.consultarPorIdTipoCozinha(any()))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(TipoCozinhaNaoEncontradoException.class, () -> useCase.run(idTipoCozinha));

        //assert
        verify(tipoCozinhaRepository).consultarPorIdTipoCozinha(any());
        verify(tipoCozinhaRepository, never()).deletar(any());
    }
}
