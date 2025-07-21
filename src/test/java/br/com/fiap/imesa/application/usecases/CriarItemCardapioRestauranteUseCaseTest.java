package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.CardapioNaoEncontradoException;
import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.CriarCardapioCommand;
import br.com.fiap.imesa.application.usecases.command.CriarItemCardapioCommand;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.ICardapioRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IItemCardapioRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CriarItemCardapioRestauranteUseCaseTest {

    @Mock
    private ICardapioRestauranteRepository cardapioRestauranteRepository;
    @Mock
    private IItemCardapioRestauranteRepository itemCardapioRestauranteRepository;

    @InjectMocks
    private CriarItemCardapioRestauranteUseCase useCase;


    @Test
    void deveCriarCardapioComSucesso(){
        //arrange
        var command = CriarItemCardapioCommand.builder()
                .idCardapio(1L)
                .descricaoPrato("prato italiano")
                .disponivelApenasLocal(true)
                .build();


        var cardapio = Cardapio.builder()
                        .codigoCardapio(1L)
                        .descricaoCardapio("Cardapio de Masas")
                        .build();


        when(cardapioRestauranteRepository.consultar(command.getIdCardapio()))
                .thenReturn(Optional.of(cardapio));

        when(itemCardapioRestauranteRepository.salvar(any(ItemCardapio.class))).thenAnswer(i -> i.getArgument(0));

        //Act
        var resultado = useCase.run(command);

        //assert
        assertNotNull(resultado);
        verify(cardapioRestauranteRepository).consultar(command.getIdCardapio());
        verify(itemCardapioRestauranteRepository).salvar(any(ItemCardapio.class));
    }

    @Test
    void deveLancarExcecaoDeRestauranteNaoEncontrado() {

        //arrange
        var command = CriarItemCardapioCommand.builder()
                .idCardapio(1L)
                .descricaoPrato("prato italiano")
                .build();


        var cardapio = Cardapio.builder()
                .codigoCardapio(1L)
                .descricaoCardapio("Cardapio de Masas")
                .build();

        when(cardapioRestauranteRepository.consultar(command.getIdCardapio()))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(CardapioNaoEncontradoException.class, () -> useCase.run(command));

        //assert
        verify(cardapioRestauranteRepository).consultar(command.getIdCardapio());
        verify(itemCardapioRestauranteRepository, never()).salvar(any());
    }
}
