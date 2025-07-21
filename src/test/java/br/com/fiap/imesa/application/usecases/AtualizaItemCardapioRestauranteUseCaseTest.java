package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.CardapioNaoEncontradoException;
import br.com.fiap.imesa.application.exception.CombinacaoItemCardapioEIdCardarpioNaoExisteException;
import br.com.fiap.imesa.application.usecases.command.AtualizarItemCardapioCommand;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.imesa.domain.gateway.ICardapioRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.IItemCardapioRestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizaItemCardapioRestauranteUseCaseTest {

    @Mock
    private IItemCardapioRestauranteRepository itemRepo;
    @Mock
    private ICardapioRestauranteRepository cardapioRepo;
    @InjectMocks
    private AtualizaItemCardapioRestauranteUseCase useCase;

    @Test
    void deveAtualizarItemCardapioComSucesso() {
        // Arrange
        var command = AtualizarItemCardapioCommand.builder()
                .idCardapio(1L)
                .idCardapio(1L)
                .nomePrato("Nome Atualizado")
                .descricaoPrato("descriacao do prato")
                .preco(100.00)
                .disponivelApenasLocal(true)
                .linkImagemPrato("link imagem")
                .build();

        var cardapio = Cardapio.builder()
                .codigoCardapio(1L)
                .build();

        var item = ItemCardapio.builder()
                .idItemCardapio(1L)
                .cardapio(cardapio)
                .nome("Nome Atualizado")
                .descricao("descriacao do prato")
                .preco(100.00)
                .diponivelApenasLocalmente(true)
                .linkImagemPrato("link imagem")
                .build();

        when(itemRepo.consultarPorIdItemCardapioEIdCardapio(any())).thenReturn(Optional.of(item));
        when(cardapioRepo.consultar(1L)).thenReturn(Optional.of(mock(br.com.fiap.imesa.domain.entities.cardapio.Cardapio.class)));
        when(itemRepo.salvar(any())).thenReturn(item);

        // Act
        ItemCardapio result = useCase.run(command);

        // Assert
        assertNotNull(result);
        assertEquals("Nome Atualizado", result.getNome());
        verify(itemRepo).consultarPorIdItemCardapioEIdCardapio(any());
        verify(cardapioRepo).consultar(1L);
        verify(itemRepo).salvar(any());
    }

    @Test
    void deveLancarExcecaoSeItemCardapioNaoExistir() {
        // Arrange
        var command = AtualizarItemCardapioCommand.builder()
                .idItem(1L)
                .idCardapio(2L)
                .nomePrato("Nome do Prato")
                .descricaoPrato("Descricao do Prato")
                .preco(100.00)
                .disponivelApenasLocal(true)
                .linkImagemPrato("link imagem")
                .build();

        when(itemRepo.consultarPorIdItemCardapioEIdCardapio(any())).thenReturn(Optional.empty());

        // Act & Assert
        CombinacaoItemCardapioEIdCardarpioNaoExisteException ex = assertThrows(
                CombinacaoItemCardapioEIdCardarpioNaoExisteException.class,
                () -> useCase.run(command)
        );

        assertEquals(String.format("Este Item: [%s] nao pertence a este Cardapio id [%s]",command.getIdItem(), command.getIdCardapio()), ex.getMessage());
        verify(itemRepo).consultarPorIdItemCardapioEIdCardapio(any());
        verify(cardapioRepo, never()).consultar(anyLong());
    }

    @Test
    void deveLancarExcecaoSeCardapioNaoExistir() {
        // Arrange
        var command = AtualizarItemCardapioCommand.builder()
                .idItem(1L)
                .idCardapio(2L)
                .nomePrato("Nome do Prato")
                .descricaoPrato("Descricao do Prato")
                .preco(100.00)
                .disponivelApenasLocal(true)
                .linkImagemPrato("link imagem")
                .build();

        var cardapio = Cardapio.builder()
                .codigoCardapio(2L)
                .build();

        var item = ItemCardapio.builder()
                .idItemCardapio(1L)
                .cardapio(cardapio)
                .nome("Nome Atualizado")
                .descricao("descriacao do prato")
                .preco(100.00)
                .diponivelApenasLocalmente(true)
                .linkImagemPrato("link imagem")
                .build();

        when(itemRepo.consultarPorIdItemCardapioEIdCardapio(any())).thenReturn(Optional.of(item));
        when(cardapioRepo.consultar(2L)).thenReturn(Optional.empty());

        // Act & Assert
        CardapioNaoEncontradoException ex = assertThrows(
                CardapioNaoEncontradoException.class,
                () -> useCase.run(command)
        );

        assertEquals(String.format("Id do Cardapio não encontrado: [%s]",command.getIdCardapio()), ex.getMessage());
        verify(cardapioRepo).consultar(2L);
    }
}
