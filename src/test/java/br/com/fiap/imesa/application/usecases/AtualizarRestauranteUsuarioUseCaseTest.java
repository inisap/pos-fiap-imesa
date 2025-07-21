package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.application.exception.TipoCozinhaNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.AtualizarRestauranteCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarRestauranteUsuarioUseCaseTest {

    @Mock
    private ITipoCozinhaRepository tipoCozinhaRepository;

    @Mock
    private IRestauranteRepository restauranteRepository;

    @InjectMocks
    private AtualizarRestauranteUsuarioUseCase atualizarRestauranteUsuarioUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarRestauranteComSucesso() {
        // Arrange
        var command = AtualizarRestauranteCommand.builder()
                .nome("Novo Nome")
                .idRestaurante(1L)
                .tipoCozinha(2)
                .build();

        var restaurante = Restaurante.builder()
                .id(1L)
                .nome("Antigo Nome")
                .build();

        var tipoCozinha = TipoCozinha.builder()
                .id(2)
                .nome("Italiana")
                .build();

        when(restauranteRepository.consultaPorId(1L)).thenReturn(Optional.of(restaurante));
        when(tipoCozinhaRepository.consultarPorIdTipoCozinha(2)).thenReturn(Optional.of(tipoCozinha));
        when(restauranteRepository.atualizar(any(Restaurante.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Restaurante resultado = atualizarRestauranteUsuarioUseCase.run(command);

        // Assert
        assertNotNull(resultado);
        assertEquals("Novo Nome", resultado.getNome());
        assertEquals(tipoCozinha, resultado.getTipoCozinha());

        verify(restauranteRepository).consultaPorId(1L);
        verify(tipoCozinhaRepository).consultarPorIdTipoCozinha(2);
        verify(restauranteRepository).atualizar(restaurante);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        // Arrange
        var command = AtualizarRestauranteCommand.builder()
                .tipoCozinha(2)
                .idRestaurante(1L)
                .nome("Nome")
                .build();

        when(restauranteRepository.consultaPorId(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RestauranteNaoEncontradoException.class, () -> {
            atualizarRestauranteUsuarioUseCase.run(command);
        });

        verify(restauranteRepository).consultaPorId(1L);
        verify(tipoCozinhaRepository, never()).consultarPorIdTipoCozinha(any());
    }

    @Test
    void deveLancarExcecaoQuandoTipoCozinhaNaoEncontrado() {
        // Arrange
        var command = AtualizarRestauranteCommand.builder()
                .tipoCozinha(2)
                .idRestaurante(1L)
                .nome("Nome")
                .build();

        var restaurante = Restaurante.builder()
                .id(1L)
                .nome("Antigo Nome")
                .build();

        when(restauranteRepository.consultaPorId(1L)).thenReturn(Optional.of(restaurante));
        when(tipoCozinhaRepository.consultarPorIdTipoCozinha(2)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TipoCozinhaNaoEncontradoException.class, () -> {
            atualizarRestauranteUsuarioUseCase.run(command);
        });

        verify(restauranteRepository).consultaPorId(1L);
        verify(tipoCozinhaRepository).consultarPorIdTipoCozinha(2);
        verify(restauranteRepository, never()).atualizar(any());
    }
}
