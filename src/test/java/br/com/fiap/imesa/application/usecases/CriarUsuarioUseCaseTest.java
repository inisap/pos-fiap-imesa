package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.DuplicacaoEmailJaCadastradoException;
import br.com.fiap.imesa.application.exception.DuplicacaoLoginJaCadastradoException;
import br.com.fiap.imesa.application.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.usecases.command.CriarUsuarioCommand;
import br.com.fiap.imesa.domain.entities.cardapio.Cardapio;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.ITipoUsuarioRepository;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
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
public class CriarUsuarioUseCaseTest {

    @Mock
    private IUsuarioRepository UsuarioRepository;
    @Mock
    private ITipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private CriarUsuarioUseCase useCase;


    @Test
    void deveCriarUsuarioComSucesso(){
        //arrange

        var command = CriarUsuarioCommand.builder()
                .login("login")
                .email("email")
                .codigoTipoUsuario(1)
                .build();

        var tipoUsuario = TipoUsuario.builder()
                        .id(1)
                        .nome("Dono de Restaurante")
                        .build();


        when(UsuarioRepository.consultarPorEmail(command.getEmail()))
                .thenReturn(Optional.empty());

        when(UsuarioRepository.consultarPorLogin(command.getLogin()))
                .thenReturn(Optional.empty());

        when(tipoUsuarioRepository.consultarPorIdTipoUsuario(command.getCodigoTipoUsuario()))
                .thenReturn(Optional.of(tipoUsuario));

        when(UsuarioRepository.criar(any(Usuario.class))).thenAnswer(i -> i.getArgument(0));

        //Act
        var resultado = useCase.run(command);

        //assert
        assertNotNull(resultado);
        verify(UsuarioRepository).consultarPorEmail(command.getEmail());
        verify(UsuarioRepository).consultarPorLogin(command.getLogin());
        verify(tipoUsuarioRepository).consultarPorIdTipoUsuario(command.getCodigoTipoUsuario());
        verify(UsuarioRepository).criar(any(Usuario.class));

    }

    @Test
    void deveLancarExcecaoDeEmailJaCadastradoParaOutroUsuario() {

        //arrange

        var command = CriarUsuarioCommand.builder()
                .login("login")
                .email("email")
                .codigoTipoUsuario(1)
                .build();

        var tipoUsuario = TipoUsuario.builder()
                .id(1)
                .nome("Dono de Restaurante")
                .build();

        var usuario = Usuario.builder()
                .id(1L)
                .nome("nome")
                .build();


        when(UsuarioRepository.consultarPorEmail(command.getEmail()))
                .thenReturn(Optional.of(usuario));

        //Act
        assertThrows(DuplicacaoEmailJaCadastradoException.class, () -> useCase.run(command));

        //assert
        verify(UsuarioRepository).consultarPorEmail(command.getEmail());
        verify(UsuarioRepository, never()).consultarPorLogin(command.getLogin());
        verify(tipoUsuarioRepository, never()).consultarPorIdTipoUsuario(command.getCodigoTipoUsuario());
        verify(UsuarioRepository, never()).criar(any(Usuario.class));

    }

    @Test
    void deveLancarExcecaoDeLoginJaCadastradoParaOutroUsuario() {

        //arrange

        var command = CriarUsuarioCommand.builder()
                .login("login")
                .email("email")
                .codigoTipoUsuario(1)
                .build();

        var tipoUsuario = TipoUsuario.builder()
                .id(1)
                .nome("Dono de Restaurante")
                .build();

        var usuario = Usuario.builder()
                .id(1L)
                .nome("nome")
                .build();


        when(UsuarioRepository.consultarPorEmail(command.getEmail()))
                .thenReturn(Optional.empty());

        when(UsuarioRepository.consultarPorLogin(command.getLogin()))
                .thenReturn(Optional.of(usuario));

        //Act
        assertThrows(DuplicacaoLoginJaCadastradoException.class, () -> useCase.run(command));

        //assert
        verify(UsuarioRepository).consultarPorEmail(command.getEmail());
        verify(UsuarioRepository).consultarPorLogin(command.getLogin());
        verify(tipoUsuarioRepository, never()).consultarPorIdTipoUsuario(command.getCodigoTipoUsuario());
        verify(UsuarioRepository, never()).criar(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoDeTipoUsuarioNaoEncontrado() {

        //arrange

        var command = CriarUsuarioCommand.builder()
                .login("login")
                .email("email")
                .codigoTipoUsuario(1)
                .build();

        var tipoUsuario = TipoUsuario.builder()
                .id(1)
                .nome("Dono de Restaurante")
                .build();

        var usuario = Usuario.builder()
                .id(1L)
                .nome("nome")
                .build();


        when(UsuarioRepository.consultarPorEmail(command.getEmail()))
                .thenReturn(Optional.empty());

        when(UsuarioRepository.consultarPorLogin(command.getLogin()))
                .thenReturn(Optional.empty());

        when(tipoUsuarioRepository.consultarPorIdTipoUsuario(command.getCodigoTipoUsuario()))
                .thenReturn(Optional.empty());

        //Act
        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> useCase.run(command));

        //assert
        verify(UsuarioRepository).consultarPorEmail(command.getEmail());
        verify(UsuarioRepository).consultarPorLogin(command.getLogin());
        verify(tipoUsuarioRepository).consultarPorIdTipoUsuario(command.getCodigoTipoUsuario());
        verify(UsuarioRepository, never()).criar(any(Usuario.class));
    }
}
