package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.TipoCozinhaNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.AtualizarTipoCozinhaCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizaTipoCozinhaUseCaseTest {

    @Mock
    private ITipoCozinhaRepository tipoCozinhaRepository;
    @InjectMocks
    private AtualizaTipoCozinhaUseCase useCase;

    @Test
    void deveAtualizarTipoCozinhaQuandoExistente() {
        // Arrange
        var command = AtualizarTipoCozinhaCommand.builder()
                .id(1)
                .nome("Nova descrição")
                .build();

        var tipoExistente = TipoCozinha.builder()
                .id(1)
                .nome("Antiga descrição")
                .build();

        when(tipoCozinhaRepository.consultarPorIdTipoCozinha(1)).thenReturn(Optional.of(tipoExistente));
        when(tipoCozinhaRepository.salvar(Mockito.any(TipoCozinha.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        TipoCozinha resultado = useCase.run(command);

        // Assert
        assertNotNull(resultado);
        assertEquals(command.getId(), resultado.getId());
        assertEquals(command.getNome(), resultado.getNome());

        verify(tipoCozinhaRepository).consultarPorIdTipoCozinha(1);
        verify(tipoCozinhaRepository).salvar(any(TipoCozinha.class));
    }

    @Test
    void deveLancarExcecaoQuandoTipoCozinhaNaoExiste() {
        // Arrange
        var command = AtualizarTipoCozinhaCommand.builder()
                .id(1)
                .nome("Nova descrição")
                .build();

        when(tipoCozinhaRepository.consultarPorIdTipoCozinha(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TipoCozinhaNaoEncontradoException.class, () -> useCase.run(command));

        verify(tipoCozinhaRepository).consultarPorIdTipoCozinha(1);
        verify(tipoCozinhaRepository, never()).salvar(any());
    }
}
