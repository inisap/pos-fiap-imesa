package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletaRestauranteUseCaseTest {

    @Mock
    private IRestauranteRepository restauranteRepository;

    @InjectMocks
    private DeletaRestauranteUseCase useCase;


    @Test
    void deveDeletarRestauranteComSucesso(){
        //arrange

        Long idUsuario = 1L;

        var tipoCozinha = TipoCozinha.builder()
                .id(1)
                .nome("Italiana")
                .build();

        var usuario = Usuario.builder()
                .id(1L)
                .build();

        var restauranteDomain = Restaurante.builder()
                .id(1L)
                .nome("nome")
                .tipoCozinha(tipoCozinha)
                .usuarioProprietario(usuario)
                .build();

        when(restauranteRepository.consultaPorId(any()))
                .thenReturn(Optional.of(restauranteDomain));

        //Act
        useCase.run(idUsuario);

        //assert
        verify(restauranteRepository).consultaPorId(any());
        verify(restauranteRepository).deletar(any());
    }

    @Test
    void deveLancarExcecaoDevidoUsuarioNaoExiste() {
        //arrange
        Long idUsuario = 1L;

        when(restauranteRepository.consultaPorId(any()))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(RestauranteNaoEncontradoException.class, () -> useCase.run(idUsuario));

        //assert
        verify(restauranteRepository).consultaPorId(any());
        verify(restauranteRepository, never()).deletar(any());
    }
}
