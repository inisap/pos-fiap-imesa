package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.TipoCozinhaNaoEncontradoException;
import br.com.fiap.imesa.application.exception.TipoUsuarioJaExisteException;
import br.com.fiap.imesa.application.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.mapper.AtualizarTipoUsuarioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.AtualizarTipoCozinhaCommand;
import br.com.fiap.imesa.application.usecases.command.AtualizarTipoUsuarioCommand;
import br.com.fiap.imesa.application.usecases.command.CriarTipoUsuarioCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.ITipoUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AtualizaTipoUsuarioUseCaseTest {

    @Mock
    private ITipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private AtualizaTipoUsuarioUseCase useCase;


    @Test
    void deveAtualizarTipoUsuarioComSucesso(){
        //arrange
        var command = AtualizarTipoUsuarioCommand.builder()
                .id(1)
                .nome("nome antigo")
                .build();

        var tipoExistente = TipoUsuario.builder()
                        .id(1)
                        .nome("nome existente")
                        .build();

        when(tipoUsuarioRepository.consultarPorIdTipoUsuario(command.getId()))
                .thenReturn(Optional.of(tipoExistente));

        when(tipoUsuarioRepository.salvar(Mockito.any(TipoUsuario.class))).thenAnswer(i -> i.getArgument(0));

        //Act
        var resultado = useCase.run(command);

        //assert
        assertNotNull(resultado);
        assertEquals(command.getId(), resultado.getId());
        assertEquals(command.getNome(), resultado.getNome());

        verify(tipoUsuarioRepository).consultarPorIdTipoUsuario(1);
        verify(tipoUsuarioRepository).salvar(any(TipoUsuario.class));

    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoExiste() {
        var command = AtualizarTipoUsuarioCommand.builder()
                .id(1)
                .nome("nome")
                .build();

        when(tipoUsuarioRepository.consultarPorIdTipoUsuario(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> useCase.run(command));

        verify(tipoUsuarioRepository).consultarPorIdTipoUsuario(1);
        verify(tipoUsuarioRepository, never()).salvar(any());

    }

    @Test
    void deveLancarExceptionDevidoTipoJaCriado(){
        //arrange

        var command = AtualizarTipoUsuarioCommand.builder()
                .id(1)
                .nome("DONO_RESTAURANTE")
                .build();

        var tipoExistente = TipoUsuario.builder()
                .id(1)
                .nome("nome existente")
                .build();

        when(tipoUsuarioRepository.consultarPorIdTipoUsuario(command.getId()))
                .thenReturn(Optional.of(tipoExistente));

        when(tipoUsuarioRepository.consultarPorNome(eq(command.getNome())))
                .thenReturn(Optional.of(TipoUsuario.builder().build()));


        //Act
        TipoUsuarioJaExisteException ex = assertThrows(
                TipoUsuarioJaExisteException.class,
                () -> useCase.run(command)
        );

        //assert
        assertEquals(String.format("Tipo Usuario ja cadastrado: [%s]",command.getNome()), ex.getMessage());
        verify(tipoUsuarioRepository).consultarPorNome(command.getNome());
        verify(tipoUsuarioRepository, never()).salvar(any(TipoUsuario.class));
    }
}
