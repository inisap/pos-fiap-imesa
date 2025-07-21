package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.CardapioNaoExisteParaRestauranteException;
import br.com.fiap.imesa.application.exception.LoginInvalidoException;
import br.com.fiap.imesa.application.usecases.command.ValidarSenhaUsuarioCommand;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.ICardapioRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultaCardapioRestauranteUseCaseTest {

    @Mock
    private ICardapioRestauranteRepository cardapioRestauranteRepository;

    @InjectMocks
    private ConsultaCardapioRestauranteUseCase useCase;


    @Test
    void deveConsultarComSucesso(){
        //arrange
        Long idRestaurante =  1L;

        var cardapio = Cardapio.builder()
                .codigoCardapio(1L)
                .descricaoCardapio("descricaoCardapio")
                        .build();

        when(cardapioRestauranteRepository.consultar(idRestaurante))
                .thenReturn(Optional.of(cardapio));

        //Act
        var resultado = useCase.run(idRestaurante);

        //assert
        assertNotNull(resultado);
        verify(cardapioRestauranteRepository).consultar(idRestaurante);
    }

    @Test
    void deveLancarExcecaoQuandoCardapioNaoExisteParaRestaurante() {

        //arrange
        Long idRestaurante =  1L;

        //Act
        assertThrows(CardapioNaoExisteParaRestauranteException.class, () -> useCase.run(idRestaurante));

        //assert
        verify(cardapioRestauranteRepository).consultar(idRestaurante);
    }
}
