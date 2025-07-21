package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.AlteracaoSenhaDivergenteException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.AtualizaSenhaUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizaSenhaUsuarioUseCaseTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private AtualizaSenhaUsuarioUseCase atualizaSenhaUsuarioUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarSenhaComSucesso() {
        // Arrange
        var command =
                AtualizaSenhaUsuarioCommand.builder()
                        .idUsuario(1L)
                        .senhaAntiga("senhaAntiga")
                        .senhaNova("novaSenha")
                        .confirmacaoSenhaNova("novaSenha")
                        .build();

        var usuario = Usuario.builder()
                .id(1L)
                .password("senhaAntiga")
                .build();

        when(usuarioRepository.consultarPorIdUsuario(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.atualizar(any(Usuario.class))).thenReturn(usuario);

        // Act
        Usuario resultado = atualizaSenhaUsuarioUseCase.run(command);

        // Assert
        assertEquals("novaSenha", resultado.getPassword());
        verify(usuarioRepository).consultarPorIdUsuario(1L);
        verify(usuarioRepository).atualizar(usuario);
    }

    @Test
    void deveLancarExcecao_UsuarioNaoEncontrado() {
        // Arrange
        var command =
                AtualizaSenhaUsuarioCommand.builder()
                        .idUsuario(1L)
                        .senhaAntiga("senhaAntiga")
                        .senhaNova("senhaNova")
                        .confirmacaoSenhaNova("senhaNova")
                        .build();

        when(usuarioRepository.consultarPorIdUsuario(999L)).thenReturn(Optional.empty());

        // Act & Assert
        UsuarioNaoEncontradoException ex = assertThrows(UsuarioNaoEncontradoException.class,
                () -> atualizaSenhaUsuarioUseCase.run(command));

        assertEquals(String.format("Id de Usuario não encontrado: [%s]",command.getIdUsuario()), ex.getMessage());
        verify(usuarioRepository).consultarPorIdUsuario(1L);
    }

    @Test
    void deveLancarExcecao_SenhaNovaDiferenteConfirmacao() {
        // Arrange
        var command =
                AtualizaSenhaUsuarioCommand.builder()
                        .idUsuario(1L)
                        .senhaAntiga("senhaAntiga")
                        .senhaNova("senhaNova")
                        .confirmacaoSenhaNova("senhaNovaDiferente")
                        .build();

        var usuario = Usuario.builder()
                .id(1L)
                .password("senhaAntiga")
                .build();

        when(usuarioRepository.consultarPorIdUsuario(1L)).thenReturn(Optional.of(usuario));

        // Act & Assert
        AlteracaoSenhaDivergenteException ex = assertThrows(AlteracaoSenhaDivergenteException.class,
                () -> atualizaSenhaUsuarioUseCase.run(command));

        assertEquals("As senha nova não coincide com a confirmação de senha", ex.getMessage());
    }

    @Test
    void deveLancarExcecao_SenhaAntigaIncorreta() {
        // Arrange
        var command = AtualizaSenhaUsuarioCommand.builder()
                .idUsuario(1L)
                .senhaAntiga("senhaErrada")
                .senhaNova("senhaNova")
                .confirmacaoSenhaNova("senhaNova")
                .build();

        Usuario usuario = Usuario.builder()
                .id(1L)
                .password("senhaCorreta")
                .build();

        when(usuarioRepository.consultarPorIdUsuario(1L)).thenReturn(Optional.of(usuario));

        // Act & Assert
        AlteracaoSenhaDivergenteException ex = assertThrows(AlteracaoSenhaDivergenteException.class,
                () -> atualizaSenhaUsuarioUseCase.run(command));

        assertEquals("A senha antiga não confere", ex.getMessage());
    }
}
