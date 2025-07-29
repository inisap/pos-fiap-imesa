package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.CombinacaoItemCardapioEIdCardarpioNaoExisteException;
import br.com.fiap.imesa.application.exception.TipoUsuarioJaExisteException;
import br.com.fiap.imesa.application.usecases.command.CriarTipoCozinhaCommand;
import br.com.fiap.imesa.application.usecases.command.CriarTipoUsuarioCommand;
import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.gateway.ITipoUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CriaTipoUsuarioUseCaseTest {

    @Mock
    private ITipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private CriaTipoUsuarioUseCase useCase;

    @Test
    void deveCriarTipoUsuarioComSucesso(){
        //arrange

        var command = CriarTipoUsuarioCommand.builder()
                        .nome("DONO_RESTAURANTE")
                        .build();

        when(tipoUsuarioRepository.consultarPorNome(eq(command.getNome())))
                .thenReturn(Optional.empty());

        when(tipoUsuarioRepository.salvar(any(TipoUsuario.class))).thenAnswer(i -> i.getArgument(0));

        //Act
        var resultado = useCase.run(command);

        //assert
        assertNotNull(resultado);
        verify(tipoUsuarioRepository).consultarPorNome(command.getNome());
        verify(tipoUsuarioRepository).salvar(any(TipoUsuario.class));
    }

    @Test
    void deveLancarExceptionDevidoTipoJaCriado(){
        //arrange

        var command = CriarTipoUsuarioCommand.builder()
                .nome("DONO_RESTAURANTE")
                .build();

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
