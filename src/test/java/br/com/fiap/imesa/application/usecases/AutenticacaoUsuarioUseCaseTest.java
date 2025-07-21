package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.LoginInvalidoException;
import br.com.fiap.imesa.application.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.AtualizarTipoUsuarioCommand;
import br.com.fiap.imesa.application.usecases.command.ValidarSenhaUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.ITipoUsuarioRepository;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AutenticacaoUsuarioUseCaseTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private AutenticacaoUsuarioUseCase useCase;


    @Test
    void deveAutenticarComSucesso(){
        //arrange
        var command = ValidarSenhaUsuarioCommand.builder()
                .login("login")
                .password("1234")
                .build();

        var usuario = Usuario.builder()
                        .nome("usuario")
                        .password("1234")
                        .login("login")
                        .build();

        when(usuarioRepository.consultarPorLogin(command.getLogin()))
                .thenReturn(Optional.of(usuario));

        //Act
        var resultado = useCase.run(command);

        //assert
        assertNotNull(resultado);
        assertEquals(command.getLogin(), resultado.getLogin());
        assertEquals(command.getPassword(), resultado.getPassword());

        verify(usuarioRepository).consultarPorLogin(command.getLogin());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExiste() {

        //arrange
        var command = ValidarSenhaUsuarioCommand.builder()
                .login("login")
                .password("1234")
                .build();

        when(usuarioRepository.consultarPorLogin(command.getLogin()))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(LoginInvalidoException.class, () -> useCase.run(command));

        //assert
        verify(usuarioRepository).consultarPorLogin(command.getLogin());
    }

    @Test
    void deveLancarExcecaoQuandoSenhaInvalida() {

        //arrange
        var command = ValidarSenhaUsuarioCommand.builder()
                .login("login")
                .password("senhaInformada")
                .build();

        var usuario = Usuario.builder()
                .nome("usuario")
                .password("senhaDiferente")
                .login("login")
                .build();

        when(usuarioRepository.consultarPorLogin(command.getLogin()))
                .thenReturn(Optional.of(usuario));

        //Act
        assertThrows(LoginInvalidoException.class, () -> useCase.run(command));

        //assert
        verify(usuarioRepository).consultarPorLogin(command.getLogin());
    }
}
