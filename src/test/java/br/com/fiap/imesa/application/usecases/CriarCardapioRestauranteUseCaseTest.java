package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.LoginNaoEncontradoException;
import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.CriarCardapioCommand;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.ICardapioRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CriarCardapioRestauranteUseCaseTest {

    @Mock
    private ICardapioRestauranteRepository cardapioRestauranteRepository;
    @Mock
    private IRestauranteRepository consultaRestauranteRepository;

    @InjectMocks
    private CriarCardapioRestauranteUseCase useCase;


    @Test
    void deveCriarCardapioComSucesso(){
        //arrange
        var command = CriarCardapioCommand.builder()
                .idRestaurante(1L)
                .descricaoCardapio("cardapio italiano")
                .build();


        var restaurante = Restaurante.builder()
                        .id(1L)
                        .nome("Casa da Nova")
                        .build();


        when(consultaRestauranteRepository.consultaPorId(command.getIdRestaurante()))
                .thenReturn(Optional.of(restaurante));

        when(cardapioRestauranteRepository.salvar(any(Cardapio.class))).thenAnswer(i -> i.getArgument(0));

        //Act
        var resultado = useCase.run(command);

        //assert
        assertNotNull(resultado);
        verify(consultaRestauranteRepository).consultaPorId(command.getIdRestaurante());
        verify(cardapioRestauranteRepository).salvar(any(Cardapio.class));
    }

    @Test
    void deveLancarExcecaoDeRestauranteNaoEncontrado() {

        //arrange
        var command = CriarCardapioCommand.builder()
                .idRestaurante(1L)
                .descricaoCardapio("cardapio italiano")
                .build();


        var restaurante = Restaurante.builder()
                .id(1L)
                .nome("Casa da Nova")
                .build();

        when(consultaRestauranteRepository.consultaPorId(command.getIdRestaurante()))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(RestauranteNaoEncontradoException.class, () -> useCase.run(command));

        //assert
        verify(consultaRestauranteRepository).consultaPorId(command.getIdRestaurante());
        verify(cardapioRestauranteRepository, never()).salvar(any());
    }
}
